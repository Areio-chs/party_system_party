package com.party.service.system;

import java.util.List;

public interface RolePowerService {
    /*根据角色id查询角色对应权限id  一个人有多个角色 一个角色有多个权限*/
    public List<Integer> findPowerByRoleIds(List<String> roleIds);
}
