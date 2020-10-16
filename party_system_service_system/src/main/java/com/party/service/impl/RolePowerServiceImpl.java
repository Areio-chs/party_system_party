package com.party.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.party.dao.RolePowerMapper;
import com.party.service.system.RolePowerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RolePowerServiceImpl implements RolePowerService {
    @Autowired
    private RolePowerMapper rolePowerMapper;
    @Override
    public List<Integer> findPowerByRoleIds(List<String> roleIds) {
        return rolePowerMapper.selectForAlgorithm(roleIds);
    }
}
