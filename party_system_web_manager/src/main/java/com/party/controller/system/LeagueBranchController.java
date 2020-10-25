package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.LeagueBranch;
import com.party.service.system.LeagueBranchService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/leagueBranch")
public class LeagueBranchController {

    @Reference
    private LeagueBranchService leagueBranchService;

    @GetMapping("/findAll")
    public List<LeagueBranch> findAll(){
        return leagueBranchService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<LeagueBranch> findPage(int page, int size){
        return leagueBranchService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<LeagueBranch> findList(@RequestBody Map<String,Object> searchMap){
        return leagueBranchService.findList(searchMap);
    }

    //真正执行这个是这个方法，带条件分页查询
    @PostMapping("/findPage")
    public PageResult<LeagueBranch> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  leagueBranchService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public LeagueBranch findById(Integer id){
        return leagueBranchService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody LeagueBranch leagueBranch){
        leagueBranch.setCreateTime(new Date());
        leagueBranchService.add(leagueBranch);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody LeagueBranch leagueBranch){
        leagueBranchService.update(leagueBranch);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        leagueBranchService.delete(id);
        return new Result();
    }

    /*修改团支部对接的党小组/党支部
    * 要考虑到一旦转移了该团支部下的团员都会一起转移
    * 非表单提交都可以用get把？用post好像用写在postman的json都要加上@RequestBody，否则只能写在请求路径上
    * @RequestBody传入了空总是要报错
    * */
    @GetMapping("tissueTransfer")
    public Result tissueTransfer(Integer id,Integer group_id,Integer party_id){
        leagueBranchService.tissueTransfer(id,group_id,party_id);
        return new Result();
    }

}
