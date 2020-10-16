package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Develop;
import com.party.service.system.DevelopService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/develop")
public class DevelopController {

    @Reference
    private DevelopService developService;

    @GetMapping("/findAll")
    public List<Develop> findAll(){
        return developService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Develop> findPage(int page, int size){
        return developService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Develop> findList(@RequestBody Map<String,Object> searchMap){
        return developService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Develop> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  developService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Develop findById(Integer id){
        return developService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Develop develop){
        developService.add(develop);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Develop develop){
        developService.update(develop);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        developService.delete(id);
        return new Result();
    }

}
