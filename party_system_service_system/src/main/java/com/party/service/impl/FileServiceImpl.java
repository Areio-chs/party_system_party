package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.FileMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.File;
import com.party.service.system.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<File> findAll() {
        return fileMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<File> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<File> files = (Page<File>) fileMapper.selectAll();
        return new PageResult<File>(files.getTotal(),files.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<File> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return fileMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<File> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<File> files = (Page<File>) fileMapper.selectByExample(example);
        return new PageResult<File>(files.getTotal(),files.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public File findById(Integer id) {
        return fileMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param file
     */
    public void add(File file) {
        fileMapper.insert(file);
    }

    /**
     * 修改
     * @param file
     */
    public void update(File file) {
        fileMapper.updateByPrimaryKeySelective(file);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        fileMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(File.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 文件名
            if(searchMap.get("fileName")!=null && !"".equals(searchMap.get("fileName"))){
                criteria.andLike("fileName","%"+searchMap.get("fileName")+"%");
            }
            // 上传路径
            if(searchMap.get("filePath")!=null && !"".equals(searchMap.get("filePath"))){
                criteria.andLike("filePath","%"+searchMap.get("filePath")+"%");
            }
            // 备注
            if(searchMap.get("fileNote")!=null && !"".equals(searchMap.get("fileNote"))){
                criteria.andLike("fileNote","%"+searchMap.get("fileNote")+"%");
            }

            // 主键id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 文件类型id 0表示图片1表示文件
            if(searchMap.get("fileTypeId")!=null ){
                criteria.andEqualTo("fileTypeId",searchMap.get("fileTypeId"));
            }
            // user表外键
            if(searchMap.get("userId")!=null ){
                criteria.andEqualTo("userId",searchMap.get("userId"));
            }
            // 1是申请入党的/2是申请积极分子的/3是申请发展对象的
            if(searchMap.get("photoId")!=null ){
                criteria.andEqualTo("photoId",searchMap.get("photoId"));
            }

        }
        return example;
    }

}
