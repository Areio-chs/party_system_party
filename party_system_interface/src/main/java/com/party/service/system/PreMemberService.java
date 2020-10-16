package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.PreMember;

import java.util.*;

/**
 * preMember业务逻辑层
 */
public interface PreMemberService {


    public List<PreMember> findAll();


    public PageResult<PreMember> findPage(int page, int size);


    public List<PreMember> findList(Map<String,Object> searchMap);


    public PageResult<PreMember> findPage(Map<String,Object> searchMap,int page, int size);


    public PreMember findById(Integer id);

    public void add(PreMember preMember);


    public void update(PreMember preMember);


    public void delete(Integer id);

}
