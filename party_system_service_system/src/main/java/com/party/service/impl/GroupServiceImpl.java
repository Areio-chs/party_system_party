package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.GroupMapper;
import com.party.dao.PartyMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.General;
import com.party.pojo.system.Group;
import com.party.pojo.system.Party;
import com.party.service.system.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private PartyMapper partyMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Group> findAll() {
        return groupMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Group> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Group> groups = (Page<Group>) groupMapper.selectAll();
        return new PageResult<Group>(groups.getTotal(),groups.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Group> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return groupMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Group> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        List<Group> groupList = groupMapper.selectByExample(example);
        for (Group group : groupList) {
            if (!(group.getPartyId()==null)){
                Party party = partyMapper.selectByPrimaryKey(group.getPartyId());
                group.setPartyName(party.getPartyName());
            }
        }
        Page<Group> groups = (Page<Group>) groupList;
        return new PageResult<Group>(groups.getTotal(),groups.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Group findById(Integer id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param group
     */
    public void add(Group group) {
        groupMapper.insert(group);
    }

    /**
     * 修改
     * @param group
     */
    public void update(Group group) {
        groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        groupMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Group.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 党小组名字
            if(searchMap.get("groupName")!=null && !"".equals(searchMap.get("groupName"))){
                criteria.andLike("groupName","%"+searchMap.get("groupName")+"%");
            }
            // 党小组地址
            if(searchMap.get("groupAddress")!=null && !"".equals(searchMap.get("groupAddress"))){
                criteria.andLike("groupAddress","%"+searchMap.get("groupAddress")+"%");
            }
            // 备注
            if(searchMap.get("groupNote")!=null && !"".equals(searchMap.get("groupNote"))){
                criteria.andLike("groupNote","%"+searchMap.get("groupNote")+"%");
            }

            // 主键id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 党小组电话
            if(searchMap.get("groupPhone")!=null ){
                criteria.andEqualTo("groupPhone",searchMap.get("groupPhone"));
            }
            // 党支部id
            if(searchMap.get("partyId")!=null ){
                criteria.andEqualTo("partyId",searchMap.get("partyId"));
            }
            // 账户id
            if(searchMap.get("accountId")!=null ){
                criteria.andEqualTo("accountId",searchMap.get("accountId"));
            }

        }
        return example;
    }

}
