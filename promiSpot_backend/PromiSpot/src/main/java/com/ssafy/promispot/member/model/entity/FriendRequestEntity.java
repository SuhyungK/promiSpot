package com.ssafy.promispot.member.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "FriendRequestEntity : ģ�� ��û ����", description = "ģ�� ��û ������ ��Ÿ����. ������ ������ ��� ģ���� �ȴ�.")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FriendRequestEntity {
	
	@ApiModelProperty(value = "ģ����û�Ϸù�ȣ")
	private int friendRequestSeq;
	
	@ApiModelProperty(value = "ģ����û�� ��û�� ȸ���Ϸù�ȣ")
	private int memberSeq;
	
	@ApiModelProperty(value = "ģ����û�� ���� ȸ���Ϸù�ȣ")
	private int friendRequestMember;
	
	@ApiModelProperty(value = "��û���� [ 0:��û ���� 1:���� ���� ]")
	private int friendRequestIsAgree;
	
	

	
	public FriendRequestEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public FriendRequestEntity(int friendRequestSeq, int memberSeq, int friendRequestMember, int friendRequestIsAgree) {
		super();
		this.friendRequestSeq = friendRequestSeq;
		this.memberSeq = memberSeq;
		this.friendRequestMember = friendRequestMember;
		this.friendRequestIsAgree = friendRequestIsAgree;
	}



	public int getFriendRequestSeq() {
		return friendRequestSeq;
	}

	public void setFriendRequestSeq(int friendRequestSeq) {
		this.friendRequestSeq = friendRequestSeq;
	}

	public int getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}

	public int getFriendRequestMember() {
		return friendRequestMember;
	}

	public void setFriendRequestMember(int friendRequestMember) {
		this.friendRequestMember = friendRequestMember;
	}

	public int getFriendRequestIsAgree() {
		return friendRequestIsAgree;
	}

	public void setFriendRequestIsAgree(int friendRequestIsAgree) {
		this.friendRequestIsAgree = friendRequestIsAgree;
	}

	@Override
	public String toString() {
		return "FriendRequestEntity [friendRequestSeq=" + friendRequestSeq + ", memberSeq=" + memberSeq
				+ ", friendRequestMember=" + friendRequestMember + ", friendRequestIsAgree=" + friendRequestIsAgree
				+ "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + friendRequestIsAgree;
		result = prime * result + friendRequestMember;
		result = prime * result + friendRequestSeq;
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
		FriendRequestEntity other = (FriendRequestEntity) obj;
		if (friendRequestIsAgree != other.friendRequestIsAgree)
			return false;
		if (friendRequestMember != other.friendRequestMember)
			return false;
		if (friendRequestSeq != other.friendRequestSeq)
			return false;
		if (memberSeq != other.memberSeq)
			return false;
		return true;
	}
	
	
	
	
	
}//FriendRequestEntity
