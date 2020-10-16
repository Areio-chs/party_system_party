package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.FileType;

import java.util.*;

/**
 * fileType业务逻辑层
 */
public interface FileTypeService {


    public List<FileType> findAll();


    public PageResult<FileType> findPage(int page, int size);


    public List<FileType> findList(Map<String,Object> searchMap);


    public PageResult<FileType> findPage(Map<String,Object> searchMap,int page, int size);


    public FileType findById(Integer id);

    public void add(FileType fileType);


    public void update(FileType fileType);


    public void delete(Integer id);

}
