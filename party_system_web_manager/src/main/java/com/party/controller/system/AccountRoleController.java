package com.party.controller.system;

import com.alibaba.dubbo.config.annotation.Reference;
import com.party.service.system.AccountRoleServie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accountRole")
public class AccountRoleController {

    @Reference
    private AccountRoleServie accountRoleServie;
    @GetMapping("findRoleByAccount")
    public List<Integer> findRoleByAccount(Integer accountId){return accountRoleServie.findRoleByAccount(accountId);}
}
