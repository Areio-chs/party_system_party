package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.BaseUser;

import java.util.*;

/**
 * baseUser业务逻辑层
 */
public interface BaseUserService {


    public List<BaseUser> findAll();


    public PageResult<BaseUser> findPage(int page, int size);


    public List<BaseUser> findList(Map<String,Object> searchMap);


    public PageResult<BaseUser> findPage(Map<String,Object> searchMap,int page, int size);


    public BaseUser findById(Integer id);

    public void add(BaseUser baseUser);


    public void update(BaseUser baseUser);


    public void delete(Integer id);

}
