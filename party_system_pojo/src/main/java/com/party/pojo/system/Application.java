package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * application实体类
 * @author Administrator
 *
 */
@Table(name="tb_application")
public class Application implements Serializable{

	@Id
	private Integer id;//id

	private String name;//name

	private String sex;//sex

	private String nation;//nation

	private String nativePlace;//native_place

	private java.util.Date birth;//birth

	private String idCard;//id_card

	private String phone;//phone

	private java.util.Date applyTime;//申请时间

	private String classNum;//class

	private Integer leagueMemberId;//league_member_id

	private Integer accountId;//account_id

	private String status;//1仍为申请人0为其他

	
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

	public java.util.Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Integer getLeagueMemberId() {
		return leagueMemberId;
	}
	public void setLeagueMemberId(Integer leagueMemberId) {
		this.leagueMemberId = leagueMemberId;
	}

	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


	
}
