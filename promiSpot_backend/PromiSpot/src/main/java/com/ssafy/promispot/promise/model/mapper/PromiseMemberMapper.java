package com.ssafy.promispot.promise.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.promispot.promise.model.entity.PromiseMemberEntity;

@Mapper
public interface PromiseMemberMapper {
	
	
	// ��� ������ ���
	public int registPromiseMember(PromiseMemberEntity promiseMemberEntity) throws SQLException;
	
	
	// ��� ������ �� �� ��ȸ
	public PromiseMemberEntity getPromiseMember(
			@Param("promiseSeq") int promiseSeq, 
			@Param("memberSeq") int memberSeq) throws SQLException;
	
	
	// ��� ������ ��ü ��ȸ
	public List<PromiseMemberEntity> getPromiseMemberList(int promiseSeq) throws SQLException;
	
	
	// ����� ����
	public int modifyPromiseMemberLeader(
			@Param("promiseSeq") int promiseSeq,
			@Param("memeberSeq") int memberSeq) throws SQLException;
	
	
	// ��� ������ ���� 
	public int removePromiseMember(
			@Param("promiseSeq") int promiseSeq,
			@Param("memberSeq") int memberSeq) throws SQLException;
	
	
}
