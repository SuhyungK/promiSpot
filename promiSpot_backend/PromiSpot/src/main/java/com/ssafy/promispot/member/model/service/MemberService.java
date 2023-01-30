package com.ssafy.promispot.member.model.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.promispot.member.model.entity.FileEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;

public interface MemberService {
	
	// �α���
	public MemberEntity loginMember(MemberEntity memberEntity) throws Exception;
	
	// ȸ������
	public boolean registMember(MemberEntity memberEntity) throws Exception;
	
	// ȸ������
	public boolean modifyMember(MemberEntity memberEntity) throws Exception;
	
	// ȸ��Ż��
	public boolean removeMember(int memberSeq) throws Exception;	
	
	// ȸ��������ȸ
	public MemberEntity findMember(int memberSeq) throws Exception;
	
	// ȸ���� ��ȸ
	public List<MemberEntity> findMemberList() throws Exception;
	
	// ����������ū ����
	public void saveRefreshToken(String memberId, String refreshToken) throws Exception;
	
	// ����������ū ��������
	public Object getRefreshToken(String memberId) throws Exception;
	
	// �������� ��ū ����
	public void deleteRefreshToken(String memberId) throws Exception;
	
	// ȸ�� ������ �̹��� ����
	public FileEntity saveFile(MultipartFile multipartFile) throws Exception;


}//MemberService
