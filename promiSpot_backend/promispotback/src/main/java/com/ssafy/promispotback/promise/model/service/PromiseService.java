package com.ssafy.promispotback.promise.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.promispotback.promise.dto.PromiseMemberModifyLeaderDto;
import com.ssafy.promispotback.promise.model.entity.PromiseDataEntity;
import com.ssafy.promispotback.promise.model.entity.PromiseEntity;

public interface PromiseService {
	
	// 약속 생성
	public int createPromise(PromiseEntity promiseEntity) throws SQLException;
	
	// 약속 조회 
	public PromiseDataEntity getPromise(int promiseSeq) throws SQLException;
	
	// 한 회원이 속한 약속 전부 조회
	public List<PromiseDataEntity> getPromiseList(int memberSeq) throws SQLException;
	
	// 약속 수정 
	public int modifyPromise(PromiseEntity promiseEntity) throws SQLException;

	// 약속장 변경
	int modifyLeader(PromiseMemberModifyLeaderDto promiseMemberModifyLeaderDto) throws SQLException;
	
	// 약속 삭제
	public int removePromise(int promiseSeq) throws SQLException;

	// 약속 투표 완료 토글
	public int modifyVote(int promiseSeq) throws SQLException;

	// 약속 스케쥴 완료 토글
	public int modifySchedule(int promiseSeq) throws SQLException;
	
	

}
