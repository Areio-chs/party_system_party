package com.party.service.impl;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.party.PartyUtils;
import com.party.dao.*;
import com.party.entity.PageResult;
import com.party.pojo.system.*;
import com.party.service.system.ActivistService;
import com.party.vo.ActivistVo;
import com.party.vo.AllActivistVo;
import com.party.vo.BaseUserVo;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivistServiceImpl implements ActivistService {

    @Autowired
    private ActivistMapper activistMapper;
    @Autowired
    private BaseUserMapper baseUserMapper;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private PartyMapper partyMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    LeagueBranchMapper leagueBranchMapper;


    /**
     * 返回全部记录
     * @return
     */


    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    public PageInfo <AllActivistVo> findPage(int page, int size) {
        PageHelper.startPage(page,size);
//        Page<Activist> activists = (Page<Activist>) activistMapper.selectAll();
//        return new PageResult<Activist>(activists.getTotal(),activists.getResult());

        //1.把积极分子表所有信息查出来
        List<Activist> activistList = activistMapper.selectAll();
        //2.根据积极分子表去查找出其再基本信息表的信息
        //2.1先从积极分子表的集合抽取去其user_id集合
        List<Integer> activistUseIdList = activistList.stream()
                .map(e -> e.getUserId())
                .collect(Collectors.toList());
        //2.2查出这些用户id的基本信息
        Example example=new Example(BaseUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",activistUseIdList);
        List<BaseUser> baseUserList = baseUserMapper.selectByExample(example);
        //3.然后把两种信息进行拼接,每个积极分子都是一个vo
        List<AllActivistVo> allActivistVoList = new ArrayList<>();
        for(Activist activist:activistList){
            AllActivistVo allActivistVo = new AllActivistVo();
            //3.1设置积极分子关联得基本id
            allActivistVo.setUserId(activist.getUserId());
            //3.2
            allActivistVo.setActivistTime(activist.getActivistTime());
            //3.3
            Integer culture1Id = activist.getCulture1Id();
            System.out.println("培养人1得id"+culture1Id);
            if (!PartyUtils.isNull(culture1Id)){
                //培养人1不为空，根据培养人id关联查询出其名字
                BaseUser baseUser = new BaseUser();
                baseUser.setId(culture1Id);
                BaseUser baseUser1 = baseUserMapper.selectByPrimaryKey(baseUser);
                allActivistVo.setCultureName1(baseUser1.getName());
            }
            //3.4
            Integer culture2Id = activist.getCulture2Id();
            System.out.println("培养人2得id"+culture2Id);
            if (!PartyUtils.isNull(culture2Id)){
                BaseUser baseUser = new BaseUser();
                baseUser.setId(culture2Id);
                BaseUser baseUser2 = baseUserMapper.selectByPrimaryKey(baseUser);
                allActivistVo.setCultureName2(baseUser2.getName());
            }
            //3.5
            //根据党总部id查询出其党支部名称
            Integer generalId = activist.getGeneralId();
            if (PartyUtils.isNull(generalId)){
//                System.out.println("为空");
                allActivistVo.setGeneralName("空");

            }
            if (!PartyUtils.isNull(generalId)){
                System.out.println("不为空");
                General general = new General();
                general.setId(generalId);
                General general1 = generalMapper.selectByPrimaryKey(general);
                allActivistVo.setGeneralName(general1.getGeneralName());

            }
            //3.6
            //根据党支部id查询出其党支部名称
            Integer partyId = activist.getPartyId();
            if (!PartyUtils.isNull(partyId)){
                Party party = new Party();
                party.setId(partyId);
                Party party1 = partyMapper.selectByPrimaryKey(party);
                allActivistVo.setPartyName(party1.getPartyName());
            }
            //3.7
            //根据党小组id查询出其党小组名称
            Integer groupId = activist.getGroupId();
            if (!PartyUtils.isNull(groupId)){
                Group group = new Group();
                group.setId(groupId);
                Group group1 = groupMapper.selectByPrimaryKey(group);
                allActivistVo.setGroupName(group1.getGroupName());
            }

            for (BaseUser baseUser:baseUserList){
                if (activist.getUserId().equals(baseUser.getId())){
                    BaseUserVo baseUserVo = new BaseUserVo();
                    //查出这个团支部id的名字
                    Integer leagueBranchId= baseUser.getLeagueBranchId();
//                    LeagueBranch leagueBranch = new LeagueBranch();
//                    leagueBranch.setId(leagueBranchId);
                    LeagueBranch leagueBranch = leagueBranchMapper.selectByPrimaryKey(leagueBranchId);
                    baseUserVo.setLeagueBranchName(leagueBranch.getName());
                    //把baseUser复制到baseUSerVO,只要name不一样则不会复制
                    BeanUtils.copyProperties(baseUser,baseUserVo);
                    allActivistVo.setBaseUserVo(baseUserVo);

                }
            }//至此封装完一个vo
            allActivistVoList.add(allActivistVo);
        }

        PageInfo<AllActivistVo> pageInfo = new PageInfo<>(allActivistVoList);
//        Page<AllActivistVo> allActivistVoList1 = (Page<AllActivistVo>) allActivistVoList;
//        return new PageResult<AllActivistVo>(allActivistVoList1.getTotal(),allActivistVoList1.getResult());
        return pageInfo;
    }


    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    public List<Activist> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return activistMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param queryInfo
     * @param page
     * @param size
     * @return
     */
//    public PageResult<Activist> findPage(Map<String, Object> queryInfo, int page, int size) {
//        PageHelper.startPage(page,size);
//        //把一个集合作为
//        Example example = createBaseUSerExample(queryInfo);
//        //由于查询条件是一个积极分子的名字，但是name字段不在积极分子这个表中，因此我要先把传过来的name转化为取base_user表中的积极分子对于这个名字的模糊查询
//        List<BaseUser> baseUsers = baseUserMapper.selectByExample(example);
//        //上面那些基本信息需要再把积极分子独有的字段也查出来
//        //用他们的id去积极分子表查相应数据，封装到新的对象里
//        List<Activist> activistList1 = null;
//        for(BaseUser baseUser:baseUsers){
//            Activist activist = new Activist();
//            activist.setUserId(baseUser.getId());
//            //实际上只有一个，把他们都add到一个集合里面
//            List <Activist> activistList= activistMapper.select(activist);
//            if (PartyUtils.collectionEffective(activistList)){
//                //有才把它加进集合里面
//                Activist activist1 = activistList.get(0);
//                activistList1.add(activist1);
//            }
//        }
////        Page<ActivistVo> activistVos = (Page<ActivistVo>) activistVoList;
//
//        return new PageResult<Activist>(activists.getTotal(),activists.getResult());
//    }
    public PageInfo<AllActivistVo> findPage(Map<String, Object> queryInfo, int page, int size) {
        PageHelper.startPage(page,size);
        //1.把积极分子表所有信息查出来

        List<Activist> activistList = activistMapper.selectAll();
        //2.根据积极分子表去查找出其再基本信息表的信息
        //2.1先从积极分子表的集合抽取去其user_id集合
        List<Integer> activistUseIdList = activistList.stream()
                .map(e -> e.getUserId())
                .collect(Collectors.toList());
        //2.2查出这些用户id的基本信息
        Example example=new Example(BaseUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id",activistUseIdList);

        if (queryInfo!=null){
            if(queryInfo.get("name")!=null && !"".equals(queryInfo.get("name"))){
                criteria.andLike("name","%"+queryInfo.get("name")+"%");
            }
        }

        List<BaseUser> baseUserList = baseUserMapper.selectByExample(example);
        //3.然后把两种信息进行拼接,每个积极分子都是一个vo
        List<AllActivistVo> allActivistVoList = new ArrayList<>();
        for(BaseUser baseUser:baseUserList){//基本信息是带条件查出来的，较少，必须用它来封装积极分子
            AllActivistVo allActivistVo = new AllActivistVo();
            BaseUserVo baseUserVo = new BaseUserVo();
            //查出这个团支部id的名字
            Integer leagueBranchId= baseUser.getLeagueBranchId();
            LeagueBranch leagueBranch = leagueBranchMapper.selectByPrimaryKey(leagueBranchId);
            baseUserVo.setLeagueBranchName(leagueBranch.getName());
            //把baseUser复制到baseUSerVO,只要name不一样则不会复制
            BeanUtils.copyProperties(baseUser,baseUserVo);
            allActivistVo.setBaseUserVo(baseUserVo);
            for (Activist activist:activistList){
                if (baseUser.getId().equals(activist.getUserId())){
                    //3.1设置积极分子关联得基本id
                    allActivistVo.setUserId(activist.getUserId());
                    //3.2
                    allActivistVo.setActivistTime(activist.getActivistTime());
                    //3.3
                    Integer culture1Id = activist.getCulture1Id();
                    System.out.println("培养人1得id"+culture1Id);
                    if (!PartyUtils.isNull(culture1Id)){
                        //培养人1不为空，根据培养人id关联查询出其名字
                        BaseUser baseUser1 = baseUserMapper.selectByPrimaryKey(culture1Id);
                        allActivistVo.setCultureName1(baseUser1.getName());
                    }
                    //3.4
                    Integer culture2Id = activist.getCulture2Id();
                    System.out.println("培养人2得id"+culture2Id);
                    if (!PartyUtils.isNull(culture2Id)){
                        BaseUser baseUser2 = baseUserMapper.selectByPrimaryKey(culture2Id);
                        allActivistVo.setCultureName2(baseUser2.getName());
                    }
                    //3.5
                    //根据党总部id查询出其党支部名称
                    Integer generalId = activist.getGeneralId();
                    if (PartyUtils.isNull(generalId)){
                        allActivistVo.setGeneralName("空");
                    }
                    if (!PartyUtils.isNull(generalId)){
                        System.out.println("不为空");
                        General general = new General();
                        general.setId(generalId);
                        General general1 = generalMapper.selectByPrimaryKey(general);
                        allActivistVo.setGeneralName(general1.getGeneralName());

                    }
                    //3.6
                    //根据党支部id查询出其党支部名称
                    Integer partyId = activist.getPartyId();
                    if (!PartyUtils.isNull(partyId)){
                        Party party = new Party();
                        party.setId(partyId);
                        Party party1 = partyMapper.selectByPrimaryKey(party);
                        allActivistVo.setPartyName(party1.getPartyName());
                    }
                    //3.7
                    //根据党小组id查询出其党小组名称
                    Integer groupId = activist.getGroupId();
                    if (!PartyUtils.isNull(groupId)){
                        Group group = new Group();
                        group.setId(groupId);
                        Group group1 = groupMapper.selectByPrimaryKey(group);
                        allActivistVo.setGroupName(group1.getGroupName());
                    }

                }
            }//至此封装完一个vo
            allActivistVoList.add(allActivistVo);
        }

        PageInfo<AllActivistVo> pageInfo = new PageInfo<>(allActivistVoList);
        return pageInfo;

    }
    //根据培养人id查出其名字

//    public BaseUser cultivateName(String id) {
//        if (!StringUtils.isEmpty(id)) {
//            Integer oneId = Integer.valueOf(id);
//            BaseUser baseUser = new BaseUser();
//            baseUser.setId(oneId);
//            BaseUser baseUser1 = baseUserMapper.selectByPrimaryKey(baseUser);
//            return baseUser1;
//
//        }
//        else {
//            BaseUser baseUser = new BaseUser();
//            baseUser.setId(1);
//            System.out.println("没有这个人");
//            return baseUser;
//        }
//    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Activist findById(Integer id) {
        return activistMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param activist
     */
    public void add(Activist activist) {
        activistMapper.insert(activist);
    }

    /**
     * 修改
     * @param activist
     */
    public void update(Activist activist) {
        activistMapper.updateByPrimaryKeySelective(activist);
    }

    /**
     *  删除
     * @param id
     */
    public void delete(Integer id) {
        activistMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void upadateCultivate(Integer id, Map<String, String> cultivateMap) {
        //把两个培养人的传过来

//        Activist activist = activistMapper.selectByPrimaryKey(id);
//        //cultivate1要与前端绑定到cultivateMap里面的一致
//        //培养人有没有人我都要修改
//        //要不要后端校验？至少需要一个培养人
//        String cultivate1= cultivateMap.get("cultivate1");
//        String cultivate2= cultivateMap.get("cultivate2");
//        activist.setCulture1Id(cultivate1);
//        activist.setCulture2Id(cultivate2);
//        activistMapper.updateByPrimaryKey(activist);
    }

    @Override
    public List<Activist> findActivistByName(String name) {
        return null;
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createBaseUSerExample(Map<String, Object> searchMap){

        Example example=new Example(BaseUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //从baseuser表查出积极分子里名字与这个查询条件模糊的
            //类型0表示是积极分子
            criteria.andEqualTo("type_id",0);
            //从searchMap取的都是前端自定义的名字
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

        }
        return example;
    }


    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Activist.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            //从searchMap取的都是前端自定义的名字
            // 培养人1从base表查符合的人
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 培养人2
            if(searchMap.get("culture2Id")!=null && !"".equals(searchMap.get("culture2Id"))){
                criteria.andLike("culture2Id","%"+searchMap.get("culture2Id")+"%");
            }
            // 1仍为积极分子0为其他
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
            // 团员id
            if(searchMap.get("leagueMemberId")!=null ){
                criteria.andEqualTo("leagueMemberId",searchMap.get("leagueMemberId"));
            }

        }
        return example;
    }

}
