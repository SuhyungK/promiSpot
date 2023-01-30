package com.ssafy.promispot.member.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.promispot.member.model.entity.MemberEntity;

@Mapper
public interface MemberMapper {
	
	// �α���
	public MemberEntity loginMember(MemberEntity memberEntity) throws SQLException;
	
	// ȸ������
	public int registMember(MemberEntity memberEntity) throws SQLException;
	
	// ȸ������
	public int modifyMember(MemberEntity memberEntity) throws SQLException;
	
	// ȸ��Ż��
	public int removeMember(int memberSeq) throws SQLException;	
	
	// ȸ��������ȸ
	public MemberEntity findMember(int memberSeq) throws SQLException;
	
	// ȸ���� ��ȸ
	public List<MemberEntity> findMemberList() throws SQLException;
	
	// ����������ū ����
	public void saveRefreshToken(@Param("memberId") String memberId, @Param("token") String refreshToken) throws Exception;
	
	// ����������ū ��������
	public Object getRefreshToken(String memberId) throws Exception;
	
	// �������� ��ū ����
	public void deleteRefreshToken(String memberId) throws Exception;

}//MemberMapper
