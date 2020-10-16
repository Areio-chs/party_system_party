package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Activist;
import com.party.service.system.ActivistService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/activist")
public class ActivistController {

    @Reference
    private ActivistService activistService;

    @GetMapping("/findAll")
    public List<Activist> findAll(){
        return activistService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Activist> findPage(int page, int size){
        return activistService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Activist> findList(@RequestBody Map<String,Object> searchMap){
        return activistService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Activist> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  activistService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Activist findById(Integer id){
        return activistService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Activist activist){
        activistService.add(activist);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Activist activist){
        activistService.update(activist);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        activistService.delete(id);
        return new Result();
    }

}
