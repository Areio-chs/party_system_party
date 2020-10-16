package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.BaseUser;
import com.party.service.system.BaseUserService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/baseUser")
public class BaseUserController {

    @Reference
    private BaseUserService baseUserService;

    @GetMapping("/findAll")
    public List<BaseUser> findAll(){
        return baseUserService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<BaseUser> findPage(int page, int size){
        return baseUserService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<BaseUser> findList(@RequestBody Map<String,Object> searchMap){
        return baseUserService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<BaseUser> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  baseUserService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public BaseUser findById(Integer id){
        return baseUserService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody BaseUser baseUser){
        baseUserService.add(baseUser);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody BaseUser baseUser){
        baseUserService.update(baseUser);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        baseUserService.delete(id);
        return new Result();
    }

}
