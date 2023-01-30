package com.ssafy.promispot.member.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.promispot.member.model.entity.FriendRequestEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;
import com.ssafy.promispot.member.model.mapper.FriendMapper;

@Service
public class FriendServiceImpl implements FriendService {
	
	@Autowired
	private FriendMapper friendMapper;

	// ģ�� ��û
	@Override
	public boolean requestFriend(FriendRequestEntity friendRequestEntity) throws SQLException {
		return friendMapper.requestFriend(friendRequestEntity) == 1;
	}//requestFriend

	// ģ�� ��û ��� ��ȸ - ������ �� ģ�� ��û(received), ���� ��û�� ģ�� ����(sent)
	@Override
	public List<MemberEntity> getRequestFriend(int memberSeq, int order) throws SQLException {
		return friendMapper.getRequestFriend(memberSeq, order);
	}//getRequestFriend

	// ģ�� ��û ����
	@Override
	public boolean approvalFriend(int friendRequestSeq) throws SQLException {
		return friendMapper.approvalFriend(friendRequestSeq) == 1;
	}//approvalFriend

	// ģ�� ��û ����
	@Override
	public boolean rejectFriend(int friendRequestSeq) throws SQLException {
		return friendMapper.rejectFriend(friendRequestSeq) == 1;
	}//rejectFriend

	// ģ�� ���� ��ȸ
	@Override
	public MemberEntity findFriend(String memberId) throws SQLException {
		return friendMapper.findFriend(memberId);
	}//findFriend

	// ģ�� ��� ��ȸ
	@Override
	public List<MemberEntity> findFriendList(int memberSeq) throws SQLException {
		return friendMapper.findFriendList(memberSeq);
	}//findFriendList

}//FriendServiceImpl
