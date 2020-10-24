package com.party.service.impl;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.party.PartyUtils;
import com.party.dao.ApplicationMapper;
import com.party.dao.LeagueMemberMapper;
import com.party.entity.PageResult;
import com.party.pojo.system.Application;
import com.party.pojo.system.LeagueMember;
import com.party.service.system.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private LeagueMemberMapper leagueMemberMapper;

    /**
     * 返回全部记录
     * @return
     */
    public List<Application> findAll() {
        return applicationMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageResult<Application> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Application> applications = (Page<Application>) applicationMapper.selectAll();
        return new PageResult<Application>(applications.getTotal(),applications.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Application> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return applicationMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Application> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Application> applications = (Page<Application>) applicationMapper.selectByExample(example);
        return new PageResult<Application>(applications.getTotal(),applications.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Application findById(Integer id) {
        return applicationMapper.selectByPrimaryKey(id);
    }

    /**
     * @Author Areio
     * 新增
     * @param application
     * 0 表示非团员
     * -1表示已存在
     * 1表示成功
     */
    public Integer add(Application application) {
        //先把这个人的身份id拿去团员表查询该人的id
//        Example example=new Example(LeagueMember.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("idCard",application.getIdCard());
//        List<LeagueMember> leagueMember  = leagueMemberMapper.selectByExample(example);
//        if (leagueMember.)
//        leagueMember.get(0);
        LeagueMember leagueMember = new LeagueMember();
        leagueMember.setIdCard(application.getIdCard());
        //1.查出有上面条件的那个人
        List<LeagueMember> leagueMemberList = leagueMemberMapper.select(leagueMember);
        if (!PartyUtils.collectionEffective(leagueMemberList)){
            //说明这个入党申请人不是团员，不给添加
            System.out.println("不是团员");
            return 0;
        }
        //把这个团员的id绑定到申请人表里面
//        application.setLeagueMemberId(leagueMember.getId());
        //2.如果这个人已经是申请人，不可添加
        Application application1 = new Application();
        application1.setIdCard(application.getIdCard());
        List<Application> applicationList = applicationMapper.select(application1);
        if (PartyUtils.collectionEffective(applicationList)){
            return -1;
        }
        applicationMapper.insert(application);
        return 1;
    }

    /**
     * 修改
     * @param application
     */
    public void update(Application application) {
        applicationMapper.updateByPrimaryKeySelective(application);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        applicationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Application.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // name
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // sex
            if(searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))){
                criteria.andLike("sex","%"+searchMap.get("sex")+"%");
            }
            // nation
            if(searchMap.get("nation")!=null && !"".equals(searchMap.get("nation"))){
                criteria.andLike("nation","%"+searchMap.get("nation")+"%");
            }
            // native_place
            if(searchMap.get("nativePlace")!=null && !"".equals(searchMap.get("nativePlace"))){
                criteria.andLike("nativePlace","%"+searchMap.get("nativePlace")+"%");
            }
            // id_card
            if(searchMap.get("idCard")!=null && !"".equals(searchMap.get("idCard"))){
                criteria.andLike("idCard","%"+searchMap.get("idCard")+"%");
            }
            // phone
            if(searchMap.get("phone")!=null && !"".equals(searchMap.get("phone"))){
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
            }
            // class
            if(searchMap.get("classNum")!=null && !"".equals(searchMap.get("classNum"))){
                criteria.andLike("classNum","%"+searchMap.get("classNum")+"%");
            }
            // 1仍为申请人0为其他
            if(searchMap.get("status")!=null && !"".equals(searchMap.get("status"))){
                criteria.andLike("status","%"+searchMap.get("status")+"%");
            }

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // league_member_id
            if(searchMap.get("leagueMemberId")!=null ){
                criteria.andEqualTo("leagueMemberId",searchMap.get("leagueMemberId"));
            }
            // account_id
            if(searchMap.get("accountId")!=null ){
                criteria.andEqualTo("accountId",searchMap.get("accountId"));
            }

        }
        return example;
    }

}
