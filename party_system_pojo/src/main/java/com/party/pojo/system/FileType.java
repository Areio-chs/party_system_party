package com.party.pojo.system;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * fileType实体类
 * @author Administrator
 *
 */
@Table(name="tb_file_type")
public class FileType implements Serializable{

	@Id
	private Integer id;//id


	

	private String typeName;//type_name

	
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
