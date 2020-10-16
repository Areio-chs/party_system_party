package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Activist;

import java.util.*;

/**
 * activist业务逻辑层
 */
public interface ActivistService {


    public List<Activist> findAll();


    public PageResult<Activist> findPage(int page, int size);


    public List<Activist> findList(Map<String,Object> searchMap);


    public PageResult<Activist> findPage(Map<String,Object> searchMap,int page, int size);


    public Activist findById(Integer id);

    public void add(Activist activist);


    public void update(Activist activist);


    public void delete(Integer id);

}
