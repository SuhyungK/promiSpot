package com.ssafy.promispot.vote.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.promispot.vote.model.entity.VoteEntity;
import com.ssafy.promispot.vote.model.entity.VoteMemberEntity;

public interface VoteService {
	
	//��� ��� �ĺ� ���
	public int insertCandidatePlace(VoteEntity voteEntity) throws SQLException;
	
	//��� ��� �ĺ� ��������
	public VoteEntity getCandidatePlace(int voteSeq) throws SQLException;
	
	//��� ��� �ĺ��� �������� - �ϳ��� ��ӿ� ���� ��� ��� �ĺ���
	public List<VoteEntity> getCandidatePlaceList(int promiseSeq) throws SQLException;
	
	//��� ��� �ĺ� ����(��ǥ/��ǥ���)
	public int modifyCandidatePlace(int voteSeq) throws SQLException;
	
	//��� ��� �ĺ� ����
	public int removeCandidatePlace(int voteSeq) throws SQLException;
	
	
	//����ڰ� �� ��ӿ��� �� ��Ҹ� ��ǥ�� ���� �ƴ��� ��ȸ
	public int isVoted(@Param("memberSeq") int memberSeq, @Param("voteSeq") int voteSeq) throws SQLException;
	
	//��� ��� ��ǥ��ư ������ ��ǥ�� ���̺� �߰�
	public int insertVoter(VoteMemberEntity voteMemberEntity) throws SQLException;
	
	//��� ��� ��ǥ�� �����ϸ� ��ǥ�� ���̺��� ����
	public int removeVoter(@Param("memberSeq") int memberSeq, @Param("voteSeq") int voteSeq) throws SQLException;
	

}
