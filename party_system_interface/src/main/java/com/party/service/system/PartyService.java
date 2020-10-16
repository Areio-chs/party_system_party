package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Party;

import java.util.*;

/**
 * party业务逻辑层
 */
public interface PartyService {


    public List<Party> findAll();


    public PageResult<Party> findPage(int page, int size);


    public List<Party> findList(Map<String,Object> searchMap);


    public PageResult<Party> findPage(Map<String,Object> searchMap,int page, int size);


    public Party findById(Integer id);

    public void add(Party party);


    public void update(Party party);


    public void delete(Integer id);

}
