package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Develop;

import java.util.*;

/**
 * develop业务逻辑层
 */
public interface DevelopService {


    public List<Develop> findAll();


    public PageResult<Develop> findPage(int page, int size);


    public List<Develop> findList(Map<String,Object> searchMap);


    public PageResult<Develop> findPage(Map<String,Object> searchMap,int page, int size);


    public Develop findById(Integer id);

    public void add(Develop develop);


    public void update(Develop develop);


    public void delete(Integer id);

}
