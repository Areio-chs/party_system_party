package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * actType实体类
 * @author Administrator
 *
 */
@Table(name="tb_act_type")
public class ActType implements Serializable{

	@Id
	private Integer id;//id


	

	private String typeName;//活动类型的名称

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	
}
