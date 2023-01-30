package com.ssafy.promispot.member.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FileEntity : ȸ�� ������ �̹��� ����", description = "ȸ���� ������ �̹��� ����.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileEntity {
	
	@ApiModelProperty(value = "������ �̹��� ���")
	private String imgPath;
	
	@ApiModelProperty(value = "������ �̹��� ���� �̸�")
	private String imgOriginName;
	
	@ApiModelProperty(value = "������ �̹��� ���� �̸�")
	private String imgServerName;
	
	@ApiModelProperty(value = "������ �̹��� ������")
	private long file_size;

	public FileEntity() {
		super();
	}

	public FileEntity(String imgPath, String imgOriginName, String imgServerName, long file_size) {
		super();
		this.imgPath = imgPath;
		this.imgOriginName = imgOriginName;
		this.imgServerName = imgServerName;
		this.file_size = file_size;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgOriginName() {
		return imgOriginName;
	}

	public void setImgOriginName(String imgOriginName) {
		this.imgOriginName = imgOriginName;
	}

	public String getImgServerName() {
		return imgServerName;
	}

	public void setImgServerName(String imgServerName) {
		this.imgServerName = imgServerName;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	@Override
	public String toString() {
		return "FileEntity [imgPath=" + imgPath + ", imgOriginName=" + imgOriginName + ", imgServerName="
				+ imgServerName + ", file_size=" + file_size + "]";
	}
	
	

}//FileEntity
