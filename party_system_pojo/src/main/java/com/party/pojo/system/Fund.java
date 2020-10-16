package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * fund实体类
 * @author Administrator
 *
 */
@Table(name="tb_fund")
public class Fund implements Serializable{

	@Id
	private Integer id;//id


	

	private String fundName;//缴费内容

	private Double fundMoney;//缴费金额

	private java.util.Date fundTime;//缴费时间

	private String fundType;//缴费类型

	private String fundNote;//备注

	private Integer userId;//外键党员

	private Integer partyId;//外键党支部

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public Double getFundMoney() {
		return fundMoney;
	}
	public void setFundMoney(Double fundMoney) {
		this.fundMoney = fundMoney;
	}

	public java.util.Date getFundTime() {
		return fundTime;
	}
	public void setFundTime(java.util.Date fundTime) {
		this.fundTime = fundTime;
	}

	public String getFundType() {
		return fundType;
	}
	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundNote() {
		return fundNote;
	}
	public void setFundNote(String fundNote) {
		this.fundNote = fundNote;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPartyId() {
		return partyId;
	}
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}


	
}
