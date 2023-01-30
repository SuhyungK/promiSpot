package com.ssafy.promispot.address.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AddressEntity : ȸ�� �ּ� ����", description = "ȸ���� �ּҸ� ��Ÿ����.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressEntity {
	
	@ApiModelProperty(value = "�ּ� �Ϸù�ȣ")
	private int addressSeq;
	
	@ApiModelProperty(value = "ȸ�� �Ϸù�ȣ")
	private int memberSeq;
	
	@ApiModelProperty(value = "�ּ�")
	private String addressAddress;
	
	@ApiModelProperty(value = "�ּ�����Ī")
	private String addressNick;
	
	@ApiModelProperty(value = "�浵")
	private String addressX;
	
	@ApiModelProperty(value = "����")
	private String addressY;
	
	@ApiModelProperty(value = "��ǥ �ּ� ����")
	private int addressIsPrimary;

	
	
	public AddressEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AddressEntity(int addressSeq, int memberSeq, String addressAddress, String addressNick, String addressX,
			String addressY, int addressIsPrimary) {
		super();
		this.addressSeq = addressSeq;
		this.memberSeq = memberSeq;
		this.addressAddress = addressAddress;
		this.addressNick = addressNick;
		this.addressX = addressX;
		this.addressY = addressY;
		this.addressIsPrimary = addressIsPrimary;
	}



	public int getAddressSeq() {
		return addressSeq;
	}

	public void setAddressSeq(int addressSeq) {
		this.addressSeq = addressSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getAddressAdress() {
		return addressAddress;
	}

	public void setAddressAdress(String addressAdress) {
		this.addressAddress = addressAdress;
	}

	public String getAddressNick() {
		return addressNick;
	}

	public void setAddressNick(String addressNick) {
		this.addressNick = addressNick;
	}

	public String getAddressX() {
		return addressX;
	}

	public void setAddressX(String addressX) {
		this.addressX = addressX;
	}

	public String getAddressY() {
		return addressY;
	}

	public void setAddressY(String addressY) {
		this.addressY = addressY;
	}

	public int getAddressIsPrimary() {
		return addressIsPrimary;
	}

	public void setAddressIsPrimary(int addressIsPrimary) {
		this.addressIsPrimary = addressIsPrimary;
	}

	@Override
	public String toString() {
		return "AddressEntity [addressSeq=" + addressSeq + ", memberSeq=" + memberSeq + ", addressAdress="
				+ addressAddress + ", addressNick=" + addressNick + ", addressX=" + addressX + ", addressY=" + addressY
				+ ", addressIsPrimary=" + addressIsPrimary + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressAddress == null) ? 0 : addressAddress.hashCode());
		result = prime * result + addressIsPrimary;
		result = prime * result + ((addressNick == null) ? 0 : addressNick.hashCode());
		result = prime * result + addressSeq;
		result = prime * result + ((addressX == null) ? 0 : addressX.hashCode());
		result = prime * result + ((addressY == null) ? 0 : addressY.hashCode());
		result = prime * result + memberSeq;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressEntity other = (AddressEntity) obj;
		if (addressAddress == null) {
			if (other.addressAddress != null)
				return false;
		} else if (!addressAddress.equals(other.addressAddress))
			return false;
		if (addressIsPrimary != other.addressIsPrimary)
			return false;
		if (addressNick == null) {
			if (other.addressNick != null)
				return false;
		} else if (!addressNick.equals(other.addressNick))
			return false;
		if (addressSeq != other.addressSeq)
			return false;
		if (addressX == null) {
			if (other.addressX != null)
				return false;
		} else if (!addressX.equals(other.addressX))
			return false;
		if (addressY == null) {
			if (other.addressY != null)
				return false;
		} else if (!addressY.equals(other.addressY))
			return false;
		if (memberSeq != other.memberSeq)
			return false;
		return true;
	}
	
	

	
}//AddressEntity
