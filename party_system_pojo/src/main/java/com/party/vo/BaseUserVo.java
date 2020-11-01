package com.party.vo;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class BaseUserVo implements Serializable {
    private Integer id;//id

    private String sid;//学号

    private String name;//姓名

    private String sex;//性别

    //private java.util.Date birth;//生日（年月日）

    private String grade;//年级

    private String classNum;//班级

    private String room;//宿舍号

    //private String iamge;//个人照片

    private String idCard;//身份证

    private String phone;//电话号码

    //private String email;//邮箱

    //private String address;//家庭住址

    //private String identity;//个人身份（学生）

    private String nativePlace;//籍贯

    private String residence;//户籍所在地

    //private String nation;//民族

    //private String duty;//职务（副部长）

   // private String title;//职称

    //private String aducation;//学历

    //private String graInstitution;//毕业院校

    //private java.util.Date workTime;//参加工作时间

    //private java.util.Date joinTime;//入团时间

    private java.util.Date petitionConfirm;//落款时间

    //private java.util.Date firstTalkTime;//首次谈话时间

    //private String rewardPunishInfo;//奖惩信息

    //private Integer accountId;//账户id

    //需要根据这个类型查找出积极分子，再去积极分子表把它另外的信息查找出来
    //也可以查询整个积极分子表，它通过userId来查询这边的信息
    //private String typeId;//0申请人1积极分子2发展对象3预备党员

    //private String integral;//累计积分

   // private String qq;//QQ

   // private String wechat;//微信
    private String LeagueBranchName;
}
