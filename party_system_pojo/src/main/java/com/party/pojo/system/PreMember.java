package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * preMember实体类
 * @author Administrator
 *
 */
@Table(name="tb_pre_member")
public class PreMember implements Serializable{

	@Id
	private Integer id;//id


	

	private Integer userId;//申请表关联id

	private java.util.Date preMemberTime;//成为预备党员时间

	private String culture1Id;//培养人1

	private String culture2Id;//培养人2

	private Integer generalId;//账户id

	private Integer partyId;//党支部id

	private Integer groupId;//党小组id

	private String status;//1仍为预备党员0为其他

	private Integer leagueMemberId;//league_member_id

	
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

	public java.util.Date getPreMemberTime() {
		return preMemberTime;
	}
	public void setPreMemberTime(java.util.Date preMemberTime) {
		this.preMemberTime = preMemberTime;
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
