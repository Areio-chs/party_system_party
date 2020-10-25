package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Party;
import com.party.service.system.PartyService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/party")
public class PartyController {

    @Reference
    private PartyService partyService;

    @GetMapping("/findAll")
    public List<Party> findAll(){
        return partyService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Party> findPage(int page, int size){
        return partyService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Party> findList(@RequestBody Map<String,Object> searchMap){
        return partyService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Party> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  partyService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Party findById(Integer id){
        return partyService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Party party){
        party.setPartyTime(new Date());
        partyService.add(party);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Party party){
        partyService.update(party);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        partyService.delete(id);
        return new Result();
    }

}
