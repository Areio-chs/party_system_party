package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.party.dao.AccountRoleMapper;
import com.party.service.system.AccountRoleServie;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AccountRoleServiceImpl implements AccountRoleServie {
    @Autowired
    private AccountRoleMapper accountRoleMapper;
    @Override
    public List<Integer> findRoleByAccount(Integer accountId) {
        return accountRoleMapper.selectRoleByAccount(accountId);
    }
}
