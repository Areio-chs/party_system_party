package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.GroupMapper;
import com.party.dao.LeagueBranchMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Group;
import com.party.pojo.system.LeagueBranch;
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
            Group group = groupMapper.selectByPrimaryKey(leagueBranch.getGroupId());
            leagueBranch.setGroupName(group.getGroupName());
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
