package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * leagueMember实体类
 * @author Administrator
 *
 */
@Table(name="tb_league_member")
public class LeagueMember implements Serializable{

	@Id
	private Integer id;//id




	private String name;//name

	private String sex;//sex

	private String nation;//民族

	private String nativePlace;//籍贯

	private java.util.Date birth;//出生年月日

	private String idCard;//身份证

	private String phone;//手机

	private java.util.Date joinTime;//申请入团时间

	private String aducation;//学历

	private String duty;//职务

	private String classNum;//班级

	private Integer leagueBranchId;//团支部id

	private Integer accountId;//account_id


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public java.util.Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(java.util.Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getAducation() {
		return aducation;
	}
	public void setAducation(String aducation) {
		this.aducation = aducation;
	}

	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Integer getLeagueBranchId() {
		return leagueBranchId;
	}
	public void setLeagueBranchId(Integer leagueBranchId) {
		this.leagueBranchId = leagueBranchId;
	}

	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	
}
