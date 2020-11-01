package com.party.vo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

//使用lombok不需要写getset了
@Data
public class AllActivistVo implements Serializable {
    //属性名最好直接与pojo的一样，则可以实现对拷

    private Integer userId;//申请表关联id

    private java.util.Date activistTime;//成为积极分子时间

    private String cultureName1;//培养人1从base表查符合的人

    private String cultureName2;//培养人2

    private String  generalName;//党总支id

    private String  partyName;//党支部id

    private String  groupName;//党小组id

    private BaseUserVo baseUserVo;
//    private String status;//1仍为积极分子0为其他

}
