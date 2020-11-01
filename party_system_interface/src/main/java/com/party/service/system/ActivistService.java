package com.party.service.system;
import com.github.pagehelper.PageInfo;
import com.party.entity.PageResult;
import com.party.pojo.system.Activist;
import com.party.vo.ActivistVo;
import com.party.vo.AllActivistVo;

import java.util.*;

/**
 * activist业务逻辑层
 */
public interface ActivistService {


//    public List<AllActivistVo> findAll();


    public PageInfo<AllActivistVo> findPage(int page, int size);


    public List<Activist> findList(Map<String,Object> searchMap);


    public PageInfo<AllActivistVo> findPage(Map<String,Object> queryInfo, int page, int size);


    public Activist findById(Integer id);

    public void add(Activist activist);


    public void update(Activist activist);


    public void delete(Integer id);

    public void upadateCultivate(Integer id, Map<String, String> cultivateMap);

    public List<Activist> findActivistByName(String name) ;
}
