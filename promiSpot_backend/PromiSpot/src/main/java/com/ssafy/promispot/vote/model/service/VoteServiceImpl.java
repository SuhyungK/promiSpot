package com.ssafy.promispot.vote.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.promispot.vote.model.entity.VoteEntity;
import com.ssafy.promispot.vote.model.entity.VoteMemberEntity;
import com.ssafy.promispot.vote.model.mapper.VoteMapper;


@Service
public class VoteServiceImpl implements VoteService{

	@Autowired
	VoteMapper voteMapper;
	
	
	//��� ��� �ĺ� ���
	@Override
	public int insertCandidatePlace(VoteEntity voteEntity) throws SQLException {
		return voteMapper.insertCandidatePlace(voteEntity);
	}

	
	//��� ��� �ĺ� ��������
	@Override
	public VoteEntity getCandidatePlace(int voteSeq) throws SQLException {
		return voteMapper.getCandidatePlace(voteSeq);
	}

	
	//��� ��� �ĺ��� �������� - �ϳ��� ��ӿ� ���� ��� ��� �ĺ���
	@Override
	public List<VoteEntity> getCandidatePlaceList(int promiseSeq) throws SQLException {
		return voteMapper.getCandidatePlaceList(promiseSeq);
	}

	
	//��� ��� �ĺ� ����(��ǥ/��ǥ���)
	@Override
	public int modifyCandidatePlace(int voteSeq) throws SQLException {
		return voteMapper.modifyCandidatePlace(voteSeq);
	}

	
	//��� ��� �ĺ� ����
	@Override
	public int removeCandidatePlace(int voteSeq) throws SQLException {
		return voteMapper.removeCandidatePlace(voteSeq);
	}

	
	//����ڰ� �� ��ӿ��� �� ��Ҹ� ��ǥ�� ���� �ƴ��� ��ȸ
	@Override
	public int isVoted(int memberSeq, int voteSeq) throws SQLException {
		return voteMapper.isVoted(memberSeq, voteSeq);
	}

	//��� ��� ��ǥ��ư ������ ��ǥ�� ���̺� �߰�
	@Override
	public int insertVoter(VoteMemberEntity voteMemberEntity) throws SQLException {
		return voteMapper.insertVoter(voteMemberEntity);
	}

	//��� ��� ��ǥ�� �����ϸ� ��ǥ�� ���̺��� ����
	@Override
	public int removeVoter(int memberSeq, int voteSeq) throws SQLException {
		return voteMapper.removeVoter(memberSeq, voteSeq);
	}

}
