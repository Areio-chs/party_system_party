package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.service.system.RolePowerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rolePower")
public class RolePowerController {
    @Reference
    private RolePowerService rolePowerService;
    @PostMapping("findPowerByRoleIds")
    public List<Integer> findPowerByRoleIds(@RequestBody List<String> roleIds){return rolePowerService.findPowerByRoleIds(roleIds);}
}
