package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.ActType;
import com.party.service.system.ActTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/actType")
public class ActTypeController {

    @Reference
    private ActTypeService actTypeService;

    @GetMapping("/findAll")
    public List<ActType> findAll(){
        return actTypeService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<ActType> findPage(int page, int size){
        return actTypeService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<ActType> findList(@RequestBody Map<String,Object> searchMap){
        return actTypeService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<ActType> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  actTypeService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public ActType findById(Integer id){
        return actTypeService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody ActType actType){
        actTypeService.add(actType);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ActType actType){
        actTypeService.update(actType);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        actTypeService.delete(id);
        return new Result();
    }

}
