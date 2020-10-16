package com.party.dao;

import com.party.pojo.system.AccountRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AccountRoleMapper extends Mapper<AccountRole> {
    /*根据账户id查询这个该人所具有的角色id*/
//    SELECT NAME FROM  tb_role  WHERE id IN (
//            SELECT role_id FROM tb_account_role WHERE account_id = 1
//    )
    @Select("SELECT role_id FROM tb_account_role WHERE account_id= #{accountId} ")
    public List<Integer> selectRoleByAccount(@Param("accountId") Integer accountId);

}
