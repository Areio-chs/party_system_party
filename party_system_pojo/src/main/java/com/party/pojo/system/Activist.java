package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * activist实体类
 * @author Administrator
 *
 */
@Table(name="tb_activist")
public class Activist implements Serializable{

	@Id
	private Integer id;//id


	

	private Integer userId;//申请表关联id

	private java.util.Date activistTime;//成为积极分子时间

	private String culture1Id;//培养人1从base表查符合的人

	private String culture2Id;//培养人2

	private Integer generalId;//党总支id

	private Integer partyId;//党支部id

	private Integer groupId;//党小组id

	private String status;//1仍为积极分子0为其他

	private Integer leagueMemberId;//团员id

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public java.util.Date getActivistTime() {
		return activistTime;
	}
	public void setActivistTime(java.util.Date activistTime) {
		this.activistTime = activistTime;
	}

	public String getCulture1Id() {
		return culture1Id;
	}
	public void setCulture1Id(String culture1Id) {
		this.culture1Id = culture1Id;
	}

	public String getCulture2Id() {
		return culture2Id;
	}
	public void setCulture2Id(String culture2Id) {
		this.culture2Id = culture2Id;
	}

	public Integer getGeneralId() {
		return generalId;
	}
	public void setGeneralId(Integer generalId) {
		this.generalId = generalId;
	}

	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLeagueMemberId() {
		return leagueMemberId;
	}
	public void setLeagueMemberId(Integer leagueMemberId) {
		this.leagueMemberId = leagueMemberId;
	}


	
}
