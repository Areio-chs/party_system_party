package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.DevelopMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Develop;
import com.party.service.system.DevelopService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class DevelopServiceImpl implements DevelopService {

    @Autowired
    private DevelopMapper developMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Develop> findAll() {
        return developMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Develop> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Develop> develops = (Page<Develop>) developMapper.selectAll();
        return new PageResult<Develop>(develops.getTotal(),develops.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Develop> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return developMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Develop> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Develop> develops = (Page<Develop>) developMapper.selectByExample(example);
        return new PageResult<Develop>(develops.getTotal(),develops.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Develop findById(Integer id) {
        return developMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param develop
     */
    public void add(Develop develop) {
        developMapper.insert(develop);
    }

    /**
     * 修改
     * @param develop
     */
    public void update(Develop develop) {
        developMapper.updateByPrimaryKeySelective(develop);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        developMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Develop.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 培养人1
            if(searchMap.get("culture1Id")!=null && !"".equals(searchMap.get("culture1Id"))){
                criteria.andLike("culture1Id","%"+searchMap.get("culture1Id")+"%");
            }
            // 培养人2
            if(searchMap.get("culture2Id")!=null && !"".equals(searchMap.get("culture2Id"))){
                criteria.andLike("culture2Id","%"+searchMap.get("culture2Id")+"%");
            }
            // 1仍为发展对象0为其他
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
            }

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 申请表关联id
            if(searchMap.get("userId")!=null ){
                criteria.andEqualTo("userId",searchMap.get("userId"));
            }
            // 党总支id
            if(searchMap.get("generalId")!=null ){
                criteria.andEqualTo("generalId",searchMap.get("generalId"));
            }
            // 党支部id
            if(searchMap.get("partyId")!=null ){
                criteria.andEqualTo("partyId",searchMap.get("partyId"));
            }
            // 党小组id
            if(searchMap.get("groupId")!=null ){
                criteria.andEqualTo("groupId",searchMap.get("groupId"));
            }
            // league_member_id
            if(searchMap.get("leagueMemberId")!=null ){
                criteria.andEqualTo("leagueMemberId",searchMap.get("leagueMemberId"));
            }

        }
        return example;
    }

}
