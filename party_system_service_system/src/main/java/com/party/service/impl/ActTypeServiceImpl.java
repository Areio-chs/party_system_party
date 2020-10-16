package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.ActTypeMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.ActType;
import com.party.service.system.ActTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ActTypeServiceImpl implements ActTypeService {

    @Autowired
    private ActTypeMapper actTypeMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<ActType> findAll() {
        return actTypeMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<ActType> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<ActType> actTypes = (Page<ActType>) actTypeMapper.selectAll();
        return new PageResult<ActType>(actTypes.getTotal(),actTypes.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<ActType> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return actTypeMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<ActType> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<ActType> actTypes = (Page<ActType>) actTypeMapper.selectByExample(example);
        return new PageResult<ActType>(actTypes.getTotal(),actTypes.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public ActType findById(Integer id) {
        return actTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param actType
     */
    public void add(ActType actType) {
        actTypeMapper.insert(actType);
    }

    /**
     * 修改
     * @param actType
     */
    public void update(ActType actType) {
        actTypeMapper.updateByPrimaryKeySelective(actType);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        actTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(ActType.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 活动类型的名称
            if(searchMap.get("typeName")!=null && !"".equals(searchMap.get("typeName"))){
                criteria.andLike("typeName","%"+searchMap.get("typeName")+"%");
            }

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}
