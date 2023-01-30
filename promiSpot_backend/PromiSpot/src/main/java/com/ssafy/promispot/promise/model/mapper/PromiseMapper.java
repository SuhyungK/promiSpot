package com.ssafy.promispot.promise.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.promispot.promise.model.entity.PromiseEntity;

@Mapper
public interface PromiseMapper {
	
	// ��� ����
	public int createPromise(PromiseEntity promiseEntity) throws SQLException;
	
	// ��� ��ȸ 
	public PromiseEntity getPromise(int promiseSeq) throws SQLException;
	
	// �� ȸ���� ���� ��� ���� ��ȸ
	public List<PromiseEntity> getPromiseList(int memberSeq) throws SQLException;
	
	// ��� ���� 
	public int modifyPromise(PromiseEntity promiseEntity) throws SQLException;
	
	// ��� ����
	public int removePromise(int promiseSeq) throws SQLException;
	
	
	

}
