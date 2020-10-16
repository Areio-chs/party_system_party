package com.party.service.system;
import com.party.entity.PageResult;
import com.party.pojo.system.Member;

import java.util.*;

/**
 * member业务逻辑层
 */
public interface MemberService {


    public List<Member> findAll();


    public PageResult<Member> findPage(int page, int size);


    public List<Member> findList(Map<String,Object> searchMap);


    public PageResult<Member> findPage(Map<String,Object> searchMap,int page, int size);


    public Member findById(Integer id);

    public void add(Member member);


    public void update(Member member);


    public void delete(Integer id);

}
