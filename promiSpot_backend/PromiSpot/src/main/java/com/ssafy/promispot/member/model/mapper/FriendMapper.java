package com.ssafy.promispot.member.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.promispot.member.model.entity.FriendRequestEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;

@Mapper
public interface FriendMapper {
	
	// ģ�� ��û
	public int requestFriend(FriendRequestEntity friendRequestEntity) throws SQLException;
	
	// ģ�� ��û ��� ��ȸ (������ �� ģ�� ��û ����)
	public List<MemberEntity> getRequestFriend(@Param("memberSeq") int memberSeq
			, @Param("order") int order) throws SQLException;
	
	// ģ�� ��û ����
	public int approvalFriend(int friendRequestSeq) throws SQLException;
	
	// ģ�� ��û ����
	public int rejectFriend(int friendRequestSeq) throws SQLException;
	
	// ģ�� ���� ��ȸ
	public MemberEntity findFriend(String memberId) throws SQLException;
	
	// ģ�� ��� ��ȸ
	public List<MemberEntity> findFriendList(int memberSeq) throws SQLException;

}//FriendMapper
