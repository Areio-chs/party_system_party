package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.entity.PageResult;
import com.party.entity.Result;
import com.party.pojo.system.FileType;
import com.party.service.system.FileTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/fileType")
public class FileTypeController {

    @Reference
    private FileTypeService fileTypeService;

    @GetMapping("/findAll")
    public List<FileType> findAll(){
        return fileTypeService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<FileType> findPage(int page, int size){
        return fileTypeService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<FileType> findList(@RequestBody Map<String,Object> searchMap){
        return fileTypeService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<FileType> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  fileTypeService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public FileType findById(Integer id){
        return fileTypeService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody FileType fileType){
        fileTypeService.add(fileType);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody FileType fileType){
        fileTypeService.update(fileType);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(Integer id){
        fileTypeService.delete(id);
        return new Result();
    }

}
