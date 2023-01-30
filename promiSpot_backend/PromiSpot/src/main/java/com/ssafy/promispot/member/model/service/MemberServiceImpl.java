package com.ssafy.promispot.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.promispot.member.model.entity.FileEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;
import com.ssafy.promispot.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private FileHandler fileHandler;
	
	@Autowired
	private MemberMapper memberMapper;

	/* ȸ�� �α��� */
	@Override
	public MemberEntity loginMember(MemberEntity memberEntity) throws Exception {
		if(memberEntity.getMemberId() == null || memberEntity.getMemberPass() == null) {
			return null;
		}
		return memberMapper.loginMember(memberEntity);
	}//loginMember

	/* ȸ�� ���� */
	@Override
	public boolean registMember(MemberEntity memberEntity) throws Exception {
		if(memberEntity.getMemberId() == null || memberEntity.getMemberPass() == null) {
			throw new Exception();
		}
		return memberMapper.registMember(memberEntity) == 1;
	}//registMember

	/* ȸ�� ���� ���� */
	@Override
	public boolean modifyMember(MemberEntity memberEntity) throws Exception {
//		if(memberEntity.getMemberId() == null || memberEntity.getMemberPass() == null) {
//			throw new Exception();
//		}
		return memberMapper.modifyMember(memberEntity) == 1;
	}//modifyMember

	/* ȸ�� Ż�� */
	@Override
	public boolean removeMember(int memberSeq) throws Exception {
		return memberMapper.removeMember(memberSeq) == 1;
	}//removeMember

	/* ȸ�� ��ȸ */
	@Override
	public MemberEntity findMember(int memberSeq) throws Exception {		
		return memberMapper.findMember(memberSeq);
	}//findMember

	/* ȸ�� ��� ��ȸ */
	@Override
	public List<MemberEntity> findMemberList() throws Exception {
		return memberMapper.findMemberList();
	}//findMemberList
	
	@Override
	public void saveRefreshToken(String memberId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(memberId, refreshToken);		
	}//saveRefreshToken

	@Override
	public Object getRefreshToken(String memberId) throws Exception {
		return memberMapper.getRefreshToken(memberId);
	}//getRefreshToken

	@Override
	public void deleteRefreshToken(String memberId) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(memberId);
	}//deleRefreshToken

	/* ȸ�� ������ �̹��� ���� */
	@Override
	public FileEntity saveFile(MultipartFile multipartFile) throws Exception {
		return fileHandler.saveFile(multipartFile);		
	}//saveFile


}//MemberServiceImpl
