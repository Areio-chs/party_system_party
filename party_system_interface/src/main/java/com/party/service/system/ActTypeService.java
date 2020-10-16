package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.ActType;

import java.util.*;

/**
 * actType业务逻辑层
 */
public interface ActTypeService {


    public List<ActType> findAll();


    public PageResult<ActType> findPage(int page, int size);


    public List<ActType> findList(Map<String,Object> searchMap);


    public PageResult<ActType> findPage(Map<String,Object> searchMap,int page, int size);


    public ActType findById(Integer id);

    public void add(ActType actType);


    public void update(ActType actType);


    public void delete(Integer id);

}
