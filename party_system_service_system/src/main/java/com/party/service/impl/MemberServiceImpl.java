package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.dao.MemberMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Member;
import com.party.service.system.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Member> findAll() {
        return memberMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Member> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Member> members = (Page<Member>) memberMapper.selectAll();
        return new PageResult<Member>(members.getTotal(),members.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Member> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return memberMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Member> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Member> members = (Page<Member>) memberMapper.selectByExample(example);
        return new PageResult<Member>(members.getTotal(),members.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Member findById(Integer id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param member
     */
    public void add(Member member) {
        memberMapper.insert(member);
    }

    /**
     * 修改
     * @param member
     */
    public void update(Member member) {
        memberMapper.updateByPrimaryKeySelective(member);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        memberMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Member.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 1仍为正式党员0为其他
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
            }

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 申请表关联id
            if(searchMap.get("userId")!=null ){
                criteria.andEqualTo("userId",searchMap.get("userId"));
            }
            // 党总支id
            if(searchMap.get("generalId")!=null ){
                criteria.andEqualTo("generalId",searchMap.get("generalId"));
            }
            // 党支部id
            if(searchMap.get("partyId")!=null ){
                criteria.andEqualTo("partyId",searchMap.get("partyId"));
            }
            // 党小组id
            if(searchMap.get("groupId")!=null ){
                criteria.andEqualTo("groupId",searchMap.get("groupId"));
            }
            // league_member_id
            if(searchMap.get("leagueMemberId")!=null ){
                criteria.andEqualTo("leagueMemberId",searchMap.get("leagueMemberId"));
            }

        }
        return example;
    }

}
