package com.party.provider;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public class DWebsiteProvider {
    public String selectForAlgorithm(@Param("roleIds") List<String> roleIds){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" SELECT power_id FROM tb_role_power"+" WHERE role_id IN");
        if (roleIds.size()>0){
            stringBuilder.append("(");
            for (int i =0; i <roleIds.size(); i++) {

                if(i>0){

                    stringBuilder.append(",");

                }

                stringBuilder.append("#{roleIds[");

                stringBuilder.append(i);

                stringBuilder.append("]}");

            }

            stringBuilder.append(")");

        }
        return stringBuilder.toString();
    }
}
