package com.ssafy.promispot.member.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberEntity : ȸ�� ����", description = "ȸ���� �� ������ ��Ÿ����.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberEntity {
	
	@ApiModelProperty(value = "ȸ���Ϸù�ȣ")
	private int memberSeq;
	
	@ApiModelProperty(value = "ȸ�����̵�")
	private String memberId;
	
	@ApiModelProperty(value = "��й�ȣ")
	private String memberPass;
	
	@ApiModelProperty(value = "�Ʒ�")
	private String memberName;
	
	@ApiModelProperty(value = "�г���")
	private String memberNick;
	
	@ApiModelProperty(value = "�̸���")
	private String memberEmail;
	
	@ApiModelProperty(value = "��ȭ��ȣ")
	private String memberPhoneNum;
	
	@ApiModelProperty(value = "������ �̹��� ���")
	private String memberImgPath;
	
	@ApiModelProperty(value = "������ �̹��� ���� �̸�")
	private String memberImgOriginName;
	
	@ApiModelProperty(value = "������ �̹��� ���� �̸�")
	private String memberImgServerName;
	
	@ApiModelProperty(value = "�� �ּ� Ȱ�� ����")
	private int memberAddressIsAgree;

	@ApiModelProperty(value = "token")
	private String member_token;
	
		
	public MemberEntity() {
		super();
	}

	public MemberEntity(int memberSeq, String memberId, String memberPass, String memberName, String memberNick,
			String memberEmail, String memberPhoneNum, String memberImgPath, String memberImgOriginName,
			String memberImgServerName, int memberAddressIsAgree, String member_token) {
		super();
		this.memberSeq = memberSeq;
		this.memberId = memberId;
		this.memberPass = memberPass;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.memberEmail = memberEmail;
		this.memberPhoneNum = memberPhoneNum;
		this.memberImgPath = memberImgPath;
		this.memberImgOriginName = memberImgOriginName;
		this.memberImgServerName = memberImgServerName;
		this.memberAddressIsAgree = memberAddressIsAgree;
		this.member_token = member_token;
	}		

	public MemberEntity(int memberSeq, String memberPass, String memberName, String memberNick, String memberPhoneNum,
			String memberImgPath, String memberImgOriginName, String memberImgServerName) {
		super();
		this.memberSeq = memberSeq;
		this.memberPass = memberPass;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.memberPhoneNum = memberPhoneNum;
		this.memberImgPath = memberImgPath;
		this.memberImgOriginName = memberImgOriginName;
		this.memberImgServerName = memberImgServerName;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPass() {
		return memberPass;
	}

	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhoneNum() {
		return memberPhoneNum;
	}

	public void setMemberPhoneNum(String memberPhoneNum) {
		this.memberPhoneNum = memberPhoneNum;
	}

	public String getMemberImgPath() {
		return memberImgPath;
	}

	public void setMemberImgPath(String memberImgPath) {
		this.memberImgPath = memberImgPath;
	}

	public String getMemberImgOriginName() {
		return memberImgOriginName;
	}

	public void setMemberImgOriginName(String memberImgOriginName) {
		this.memberImgOriginName = memberImgOriginName;
	}

	public String getMemberImgServerName() {
		return memberImgServerName;
	}

	public void setMemberImgServerName(String memberImgServerName) {
		this.memberImgServerName = memberImgServerName;
	}

	public int getMemberAddressIsAgree() {
		return memberAddressIsAgree;
	}

	public void setMemberAddressIsAgree(int memberAddressIsAgree) {
		this.memberAddressIsAgree = memberAddressIsAgree;
	}

	public String getMember_token() {
		return member_token;
	}

	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}

	@Override
	public String toString() {
		return "MemberEntity [memberSeq=" + memberSeq + ", memberId=" + memberId + ", memberPass=" + memberPass
				+ ", memberName=" + memberName + ", memberNick=" + memberNick + ", memberEmail=" + memberEmail
				+ ", memberPhoneNum=" + memberPhoneNum + ", memberImgPath=" + memberImgPath + ", memberImgOriginName="
				+ memberImgOriginName + ", memberImgServerName=" + memberImgServerName + ", memberAddressIsAgree="
				+ memberAddressIsAgree + ", member_token=" + member_token + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + memberAddressIsAgree;
		result = prime * result + ((memberEmail == null) ? 0 : memberEmail.hashCode());
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());
		result = prime * result + ((memberImgOriginName == null) ? 0 : memberImgOriginName.hashCode());
		result = prime * result + ((memberImgPath == null) ? 0 : memberImgPath.hashCode());
		result = prime * result + ((memberImgServerName == null) ? 0 : memberImgServerName.hashCode());
		result = prime * result + ((memberName == null) ? 0 : memberName.hashCode());
		result = prime * result + ((memberNick == null) ? 0 : memberNick.hashCode());
		result = prime * result + ((memberPass == null) ? 0 : memberPass.hashCode());
		result = prime * result + ((memberPhoneNum == null) ? 0 : memberPhoneNum.hashCode());
		result = prime * result + memberSeq;
		result = prime * result + ((member_token == null) ? 0 : member_token.hashCode());
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
		MemberEntity other = (MemberEntity) obj;
		if (memberAddressIsAgree != other.memberAddressIsAgree)
			return false;
		if (memberEmail == null) {
			if (other.memberEmail != null)
				return false;
		} else if (!memberEmail.equals(other.memberEmail))
			return false;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		if (memberImgOriginName == null) {
			if (other.memberImgOriginName != null)
				return false;
		} else if (!memberImgOriginName.equals(other.memberImgOriginName))
			return false;
		if (memberImgPath == null) {
			if (other.memberImgPath != null)
				return false;
		} else if (!memberImgPath.equals(other.memberImgPath))
			return false;
		if (memberImgServerName == null) {
			if (other.memberImgServerName != null)
				return false;
		} else if (!memberImgServerName.equals(other.memberImgServerName))
			return false;
		if (memberName == null) {
			if (other.memberName != null)
				return false;
		} else if (!memberName.equals(other.memberName))
			return false;
		if (memberNick == null) {
			if (other.memberNick != null)
				return false;
		} else if (!memberNick.equals(other.memberNick))
			return false;
		if (memberPass == null) {
			if (other.memberPass != null)
				return false;
		} else if (!memberPass.equals(other.memberPass))
			return false;
		if (memberPhoneNum == null) {
			if (other.memberPhoneNum != null)
				return false;
		} else if (!memberPhoneNum.equals(other.memberPhoneNum))
			return false;
		if (memberSeq != other.memberSeq)
			return false;
		if (member_token == null) {
			if (other.member_token != null)
				return false;
		} else if (!member_token.equals(other.member_token))
			return false;
		return true;
	}

	
}//MemberEntity
