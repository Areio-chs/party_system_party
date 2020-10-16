package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.PreMember;
import com.party.service.system.PreMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/preMember")
public class PreMemberController {

    @Reference
    private PreMemberService preMemberService;

    @GetMapping("/findAll")
    public List<PreMember> findAll(){
        return preMemberService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<PreMember> findPage(int page, int size){
        return preMemberService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<PreMember> findList(@RequestBody Map<String,Object> searchMap){
        return preMemberService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<PreMember> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  preMemberService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public PreMember findById(Integer id){
        return preMemberService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody PreMember preMember){
        preMemberService.add(preMember);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody PreMember preMember){
        preMemberService.update(preMember);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        preMemberService.delete(id);
        return new Result();
    }

}
