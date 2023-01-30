package com.ssafy.promispot.member.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.promispot.member.model.entity.FriendRequestEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;

public interface FriendService {
	
	// ģ�� ��û
	public boolean requestFriend(FriendRequestEntity friendRequestEntity) throws SQLException;
	
	// ģ�� ��û ��� ��ȸ (������ �� ģ�� ��û ����)
	public List<MemberEntity> getRequestFriend(@Param("memberSeq") int memberSeq
			, @Param("order") int order) throws SQLException;
	
	// ģ�� ��û ����
	public boolean approvalFriend(int friendRequestSeq) throws SQLException;
	
	// ģ�� ��û ����
	public boolean rejectFriend(int friendRequestSeq) throws SQLException;
	
	// ģ�� ���� ��ȸ
	public MemberEntity findFriend(String memberId) throws SQLException;
	
	// ģ�� ��� ��ȸ
	public List<MemberEntity> findFriendList(int memberSeq) throws SQLException;

}//FriendService
