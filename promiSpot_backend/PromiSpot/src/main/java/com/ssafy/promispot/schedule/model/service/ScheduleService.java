package com.ssafy.promispot.schedule.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.promispot.schedule.model.entity.ScheduleEntity;

public interface ScheduleService {
	
	// ������ ����
	public int createSchedule(ScheduleEntity scheduleEntity) throws SQLException;
	
	// ������ ��ȸ
	public ScheduleEntity getSchedule(int scheduleSeq) throws SQLException;
	
	// ��� �ش��ϴ� ������ ���� ��ȸ
	public List<ScheduleEntity> getScheduleList(int promiseSeq) throws SQLException;
	
	// ������ ����
	public int modifySchedule(ScheduleEntity scheduleEntity) throws SQLException;
	
	// ������ ����
	public int removeSchedule(int scheduleSeq) throws SQLException;

}
