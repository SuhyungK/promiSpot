package com.ssafy.promispot.promise.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.promispot.promise.model.entity.PromiseEntity;
import com.ssafy.promispot.promise.model.mapper.PromiseMapper;

@Service
public class PromiseServiceImpl implements PromiseService{

	@Autowired
	PromiseMapper promiseMapper;
	
	// ��� ����
	@Override
	public int createPromise(PromiseEntity promiseEntity) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	// ��� ��ȸ 
	@Override
	public PromiseEntity getPromise(int promiseSeq) throws SQLException {
		return promiseMapper.getPromise(promiseSeq);
	}

	// �� ȸ���� ���� ��� ���� ��ȸ
	@Override
	public List<PromiseEntity> getPromiseList(int memberSeq) throws SQLException {
		return promiseMapper.getPromiseList(memberSeq);
	}

	// ��� ���� 
	@Override
	public int modifyPromise(PromiseEntity promiseEntity) throws SQLException {
		return promiseMapper.modifyPromise(promiseEntity);
	}

	// ��� ����
	@Override
	public int removePromise(int promiseSeq) throws SQLException {
		return promiseMapper.removePromise(promiseSeq);
	}
	
	


}
