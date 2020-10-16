package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.FileTypeMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.FileType;
import com.party.service.system.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FileTypeServiceImpl implements FileTypeService {

    @Autowired
    private FileTypeMapper fileTypeMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<FileType> findAll() {
        return fileTypeMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<FileType> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<FileType> fileTypes = (Page<FileType>) fileTypeMapper.selectAll();
        return new PageResult<FileType>(fileTypes.getTotal(),fileTypes.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<FileType> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return fileTypeMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<FileType> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<FileType> fileTypes = (Page<FileType>) fileTypeMapper.selectByExample(example);
        return new PageResult<FileType>(fileTypes.getTotal(),fileTypes.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public FileType findById(Integer id) {
        return fileTypeMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param fileType
     */
    public void add(FileType fileType) {
        fileTypeMapper.insert(fileType);
    }

    /**
     * 修改
     * @param fileType
     */
    public void update(FileType fileType) {
        fileTypeMapper.updateByPrimaryKeySelective(fileType);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        fileTypeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(FileType.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // type_name
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
