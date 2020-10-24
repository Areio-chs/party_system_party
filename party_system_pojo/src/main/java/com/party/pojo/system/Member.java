package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * member实体类
 * @author Administrator
 *
 */
@Table(name="tb_member")
public class Member implements Serializable{

	@Id
	private Integer id;//id


	

	private Integer userId;//申请表关联id

	private java.util.Date preMemberTime;//成为正式党员时间

	private Integer generalId;//党总支id

	private Integer partyId;//党支部id

	private Integer groupId;//党小组id

	private String status;//1仍为正式党员0为其他

//	private Integer leagueMemberId;//league_member_id

	
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

//	public Integer getLeagueMemberId() {
//		return leagueMemberId;
//	}
//	public void setLeagueMemberId(Integer leagueMemberId) {
//		this.leagueMemberId = leagueMemberId;
//	}


	
}
