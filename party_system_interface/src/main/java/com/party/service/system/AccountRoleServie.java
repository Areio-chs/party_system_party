package com.party.service.system;

import java.util.List;

public interface AccountRoleServie {
    /*根据账户id查询这个该人所具有的角色id*/
    public List<Integer> findRoleByAccount(Integer accountId);
}
