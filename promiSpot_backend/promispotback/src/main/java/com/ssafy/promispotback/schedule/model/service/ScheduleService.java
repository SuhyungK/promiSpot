package com.ssafy.promispotback.schedule.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.promispotback.schedule.model.entity.ScheduleEntity;

public interface ScheduleService {
	
	// 스케줄 생성
	public int createSchedule(ScheduleEntity scheduleEntity) throws SQLException;
	
	// 스케줄 조회
	public ScheduleEntity getSchedule(int scheduleSeq) throws SQLException;
	
	// 약속 해당하는 스케줄 전부 조회
	public List<ScheduleEntity> getScheduleList(int promiseSeq) throws SQLException;
	
	// 스케줄 수정
	public int modifySchedule(ScheduleEntity scheduleEntity) throws SQLException;
	
	// 스케줄 삭제
	public int removeSchedule(int scheduleSeq) throws SQLException;

}
