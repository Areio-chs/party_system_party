package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * party实体类
 * @author Administrator
 *
 */
@Table(name="tb_party")
public class Party implements Serializable{

	@Id
	private Integer id;//主键id


	

	private String partyName;//党支部名字

	private Integer partyPhone;//党支部电话

	private String partyAddress;//党支部地址

	private java.util.Date partyTime;//创建时间

	private String partyNote;//备注

	private Integer generalId;//党总支id

	private Integer accountId;//账户id

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public Integer getPartyPhone() {
		return partyPhone;
	}
	public void setPartyPhone(Integer partyPhone) {
		this.partyPhone = partyPhone;
	}

	public String getPartyAddress() {
		return partyAddress;
	}
	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public java.util.Date getPartyTime() {
		return partyTime;
	}
	public void setPartyTime(java.util.Date partyTime) {
		this.partyTime = partyTime;
	}

	public String getPartyNote() {
		return partyNote;
	}
	public void setPartyNote(String partyNote) {
		this.partyNote = partyNote;
	}

	public Integer getGeneralId() {
		return generalId;
	}
	public void setGeneralId(Integer generalId) {
		this.generalId = generalId;
	}

	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}


	
}
