package com.ssafy.promispot.promise.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.promispot.promise.dto.PromiseMemberModifyLeaderDto;
import com.ssafy.promispot.promise.model.entity.PromiseMemberEntity;

public interface PromiseMemberService {

	// ��� ������ ���
	public int registPromiseMember(PromiseMemberEntity promiseMemberEntity) throws SQLException;
	
	
	// ��� ������ �� �� ��ȸ
	public PromiseMemberEntity getPromiseMember(
			 int promiseSeq, 
			 int memberSeq) throws SQLException;
	
	
	// ��� ������ ��ü ��ȸ
	public List<PromiseMemberEntity> getPromiseMemberList(int promiseSeq) throws SQLException;
	
	
	// ����� ����
	public int modifyPromiseMemberLeader(
			PromiseMemberModifyLeaderDto promiseMemberModifyLeaderdto) throws SQLException;
	
	
	// ��� ������ ���� 
	public int removePromiseMember(
			int promiseSeq,
			int memberSeq) throws SQLException;
	
	
}
