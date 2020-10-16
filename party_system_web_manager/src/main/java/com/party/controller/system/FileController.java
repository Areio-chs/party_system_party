package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.File;
import com.party.service.system.FileService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/file")
public class FileController {

    @Reference
    private FileService fileService;

    @GetMapping("/findAll")
    public List<File> findAll(){
        return fileService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<File> findPage(int page, int size){
        return fileService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<File> findList(@RequestBody Map<String,Object> searchMap){
        return fileService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<File> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  fileService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public File findById(Integer id){
        return fileService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody File file){
        fileService.add(file);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody File file){
        fileService.update(file);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        fileService.delete(id);
        return new Result();
    }

}
