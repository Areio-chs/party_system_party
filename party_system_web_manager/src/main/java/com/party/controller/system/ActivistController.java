package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.Activist;
import com.party.service.system.ActivistService;
import com.party.vo.AllActivistVo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/activist")
public class ActivistController {

    @Reference
    private ActivistService activistService;

//    @GetMapping("/findAll")
//    public List<AllActivistVo> findAll(){
//        return activistService.findAll();
//    }

    @GetMapping(value = "/test", produces="application/json;charset=utf-8")
    public List<Activist> findActivistByName(String name){
        return  activistService.findActivistByName(name);
    }

    //目前测试这个
    @GetMapping("/findPage")
    public PageInfo<AllActivistVo> findPage(int page, int size){
        PageInfo<AllActivistVo> allActivistVoList =activistService.findPage(page, size);
//        PageInfo<AllActivistVo> pageInfo=new PageInfo<AllActivistVo>(allActivistVoList);
        return allActivistVoList;
    }
//
//    @PostMapping("/findList")
//    public List<Activist> findList(@RequestBody Map<String,Object> searchMap){
//        return activistService.findList(searchMap);
//    }

    @PostMapping("/findPage")
    public PageInfo<AllActivistVo> findPage(@RequestBody Map<String,Object> queryInfo, int page, int size){
        PageInfo<AllActivistVo> allActivistVoList =activistService.findPage(queryInfo,page,size);
        return allActivistVoList;
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

    //给积极分子更换培养人，可能有一个可能两个，要先判断它原先有几个吗,传入某个人的id，修改其培养人
    @PostMapping("/upadateCultivate")
    public Result upadateCultivate(Integer id,@RequestBody Map<String,String> cultivateMap){
        activistService.upadateCultivate(id,cultivateMap);
        return new Result();
    }


}
