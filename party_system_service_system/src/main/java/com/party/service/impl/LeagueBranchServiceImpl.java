package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.GroupMapper;
import com.party.dao.LeagueBranchMapper;
import com.party.dao.LeagueMemberMapper;
import com.party.dao.PartyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Group;
import com.party.pojo.system.LeagueBranch;
import com.party.pojo.system.LeagueMember;
import com.party.pojo.system.Party;
import com.party.service.system.LeagueBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class LeagueBranchServiceImpl implements LeagueBranchService {

    @Autowired
    private LeagueBranchMapper leagueBranchMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private LeagueMemberMapper leagueMemberMapper;

    @Autowired
    private PartyMapper partyMapper;

    /**
     * 返回全部记录
     *
     * @return
     */
    public List<LeagueBranch> findAll() {
        return leagueBranchMapper.selectAll();
    }

    /**
     * 分页查询
     *
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<LeagueBranch> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Page<LeagueBranch> leagueBranchs = (Page<LeagueBranch>) leagueBranchMapper.selectAll();
        return new PageResult<LeagueBranch>(leagueBranchs.getTotal(), leagueBranchs.getResult());
    }

    /**
     * 条件查询
     *
     * @param searchMap 查询条件
     * @return
     */
    public List<LeagueBranch> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return leagueBranchMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     *
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<LeagueBranch> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        List<LeagueBranch> leagueBranchList = leagueBranchMapper.selectByExample(example);
        for (LeagueBranch leagueBranch : leagueBranchList) {
            if (!(leagueBranch.getGroupId()==null)){
            Group group = groupMapper.selectByPrimaryKey(leagueBranch.getGroupId());
            leagueBranch.setGroupName(group.getGroupName());
            }
        }
        Page<LeagueBranch> leagueBranchs = (Page<LeagueBranch>) leagueBranchList;
        return new PageResult<LeagueBranch>(leagueBranchs.getTotal(), leagueBranchs.getResult());
    }

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    public LeagueBranch findById(Integer id) {
        return leagueBranchMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     *
     * @param leagueBranch
     */
    public void add(LeagueBranch leagueBranch) {
        leagueBranchMapper.insert(leagueBranch);
    }

    /**
     * 修改
     *
     * @param leagueBranch
     */
    public void update(LeagueBranch leagueBranch) {
        leagueBranchMapper.updateByPrimaryKeySelective(leagueBranch);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Integer id) {
        leagueBranchMapper.deleteByPrimaryKey(id);
    }

    //两个只能选择一个或者两个都不选择
    @Override
    public void tissueTransfer(Integer id, Integer group_id, Integer party_id) {
        System.out.println("there");
        System.out.println(id);
        System.out.println(group_id);
        System.out.println(party_id);
        if (group_id != null) {
            //先把这个党支部的党员全部转到这个党小组里面，再把党支部对接到该党小组
            //1.找出该团支部的所有党员
//            Example example = new Example(LeagueMember.class);
//            Example.Criteria criteria = example.createCriteria();
//            criteria.andEqualTo("leagueBranchId",id);
//            List<LeagueMember> leagueMemberList = leagueMemberMapper.selectByExample(example);
//            for (LeagueMember leagueMember:leagueMemberList){
//
//            }

            //查询新对接的党小组属于哪个党支部
            LeagueBranch leagueBranch = new LeagueBranch();
            //1.查询出新对接的党小组
            Group group = groupMapper.selectByPrimaryKey(group_id);
            leagueBranch.setId(id);
            leagueBranch.setGroupId(group_id);
            leagueBranch.setPartyId(group.getPartyId());
            leagueBranchMapper.updateByPrimaryKeySelective(leagueBranch);


        } else if (party_id != null) {
            //说明该党支部没有小组，团支部直接对接党支部，这时候设置党小组id为空
            LeagueBranch leagueBranch = new LeagueBranch();
            leagueBranch.setId(id);
            leagueBranch.setPartyId(party_id);
            //当党支部没有党小组时，默认给他分配第0小组
            leagueBranch.setGroupId(0);
            leagueBranchMapper.updateByPrimaryKeySelective(leagueBranch);
        }
    }

    /**
     * 构建查询条件
     *
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(LeagueBranch.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // name
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // address
            if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                criteria.andLike("address", "%" + searchMap.get("address") + "%");
            }
            // note
            if (searchMap.get("note") != null && !"".equals(searchMap.get("note"))) {
                criteria.andLike("note", "%" + searchMap.get("note") + "%");
            }

            // id
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // phone
            if (searchMap.get("phone") != null) {
                criteria.andEqualTo("phone", searchMap.get("phone"));
            }
            // group_id
            if (searchMap.get("groupId") != null) {
                criteria.andEqualTo("groupId", searchMap.get("groupId"));
            }
            if (searchMap.get("groupName") != null) {
                criteria.andEqualTo("groupName", searchMap.get("groupName"));
            }
            // account_id
            if (searchMap.get("accountId") != null) {
                criteria.andEqualTo("accountId", searchMap.get("accountId"));
            }

        }
        return example;
    }

}
