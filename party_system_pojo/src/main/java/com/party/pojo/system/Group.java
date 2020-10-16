package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * group实体类
 * @author Administrator
 *
 */
@Table(name="tb_group")
public class Group implements Serializable{

	@Id
	private Integer id;//主键id


	

	private String groupName;//党小组名字

	private Integer groupPhone;//党小组电话

	private String groupAddress;//党小组地址

	private java.util.Date groupTime;//创建时间

	private String groupNote;//备注

	private Integer partyId;//党支部id

	private Integer accountId;//账户id

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupPhone() {
		return groupPhone;
	}
	public void setGroupPhone(Integer groupPhone) {
		this.groupPhone = groupPhone;
	}

	public String getGroupAddress() {
		return groupAddress;
	}
	public void setGroupAddress(String groupAddress) {
		this.groupAddress = groupAddress;
	}

	public java.util.Date getGroupTime() {
		return groupTime;
	}
	public void setGroupTime(java.util.Date groupTime) {
		this.groupTime = groupTime;
	}

	public String getGroupNote() {
		return groupNote;
	}
	public void setGroupNote(String groupNote) {
		this.groupNote = groupNote;
	}

	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	
}
