package com.party.dao;

import com.party.pojo.system.Activist;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ActivistMapper extends Mapper<Activist> {
    /**
     * 查询出名字为模糊查询结果的积极分子
     */
    @Select("SELECT * FROM tb_activist WHERE user_id IN(" +
            "SELECT id FROM tb_base_user WHERE NAME LIKE concat('%',#{name},'%'))")
    public List<Activist> selectActivistByName(@Param("name") String name) ;
}
