package com.ssafy.promispot.vote.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "VoteMemberEntity : ��ǥ��", description = "��ǥ�ڸ� ��Ÿ���� Entity�Դϴ�")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteMemberEntity {
	@ApiModelProperty(value = "ȸ���Ϸù�ȣ")
	private int memberSeq;
	@ApiModelProperty(value = "�������ĺ��Ϸù�ȣ")
	private int voteSeq;
	
	
	public VoteMemberEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VoteMemberEntity(int memberSeq, int voteSeq) {
		super();
		this.memberSeq = memberSeq;
		this.voteSeq = voteSeq;
	}


	public int getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		this.memberSeq = memberSeq;
	}
	public int getVoteSeq() {
		return voteSeq;
	}
	public void setVoteSeq(int voteSeq) {
		this.voteSeq = voteSeq;
	}
	
	
	
	@Override
	public String toString() {
		return "VoteMemberEntity [memberSeq=" + memberSeq + ", voteSeq=" + voteSeq + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + memberSeq;
		result = prime * result + voteSeq;
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
		VoteMemberEntity other = (VoteMemberEntity) obj;
		if (memberSeq != other.memberSeq)
			return false;
		if (voteSeq != other.voteSeq)
			return false;
		return true;
	}
	
	
}
