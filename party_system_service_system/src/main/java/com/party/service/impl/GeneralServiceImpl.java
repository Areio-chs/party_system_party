package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.GeneralMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.General;
import com.party.service.system.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    private GeneralMapper generalMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<General> findAll() {
        return generalMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<General> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<General> generals = (Page<General>) generalMapper.selectAll();
        return new PageResult<General>(generals.getTotal(),generals.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<General> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return generalMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<General> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<General> generals = (Page<General>) generalMapper.selectByExample(example);
        return new PageResult<General>(generals.getTotal(),generals.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public General findById(Integer id) {
        return generalMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param general
     */
    public void add(General general) {
        generalMapper.insert(general);
    }

    /**
     * 修改
     * @param general
     */
    public void update(General general) {
        generalMapper.updateByPrimaryKeySelective(general);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        generalMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(General.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 党总支名字
            if(searchMap.get("generalName")!=null && !"".equals(searchMap.get("generalName"))){
                criteria.andLike("generalName","%"+searchMap.get("generalName")+"%");
            }
            // 党总支地址
            if(searchMap.get("generalAddress")!=null && !"".equals(searchMap.get("generalAddress"))){
                criteria.andLike("generalAddress","%"+searchMap.get("generalAddress")+"%");
            }
            // 备注
            if(searchMap.get("generalNote")!=null && !"".equals(searchMap.get("generalNote"))){
                criteria.andLike("generalNote","%"+searchMap.get("generalNote")+"%");
            }

            // 主键id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 党总支电话
            if(searchMap.get("generalPhone")!=null ){
                criteria.andEqualTo("generalPhone",searchMap.get("generalPhone"));
            }
            // 账户id
            if(searchMap.get("accountId")!=null ){
                criteria.andEqualTo("accountId",searchMap.get("accountId"));
            }

        }
        return example;
    }

}
