package com.party.dao;

import com.party.pojo.system.RolePower;
import com.party.provider.DWebsiteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RolePowerMapper extends Mapper<RolePower> {
//    @Select("SELECT power_id FROM tb_role_power WHERE role_id IN(#{roleIds})")
//    public List<Integer> selectPowerByRole(@Param("roleIds") List<Integer> roleIds);
    @SelectProvider(type = DWebsiteProvider.class,method="selectForAlgorithm")
    public List<Integer> selectForAlgorithm(@Param("roleIds") List<String> roleIds);
}
