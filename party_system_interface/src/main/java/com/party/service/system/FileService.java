package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.File;

import java.util.*;

/**
 * file业务逻辑层
 */
public interface FileService {


    public List<File> findAll();


    public PageResult<File> findPage(int page, int size);


    public List<File> findList(Map<String,Object> searchMap);


    public PageResult<File> findPage(Map<String,Object> searchMap,int page, int size);


    public File findById(Integer id);

    public void add(File file);


    public void update(File file);


    public void delete(Integer id);

}
