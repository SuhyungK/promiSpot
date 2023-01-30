package com.ssafy.promispot.schedule.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.promispot.schedule.model.entity.ScheduleEntity;
import com.ssafy.promispot.schedule.model.mapper.ScheduleMapper;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	ScheduleMapper scheduleMapper;
	
	// ������ ����
	@Override
	public int createSchedule(ScheduleEntity scheduleEntity) throws SQLException {
		return scheduleMapper.createSchedule(scheduleEntity);
	}

	// ������ ��ȸ
	@Override
	public ScheduleEntity getSchedule(int scheduleSeq) throws SQLException {
		return scheduleMapper.getSchedule(scheduleSeq);
	}
	
	// ��� �ش��ϴ� ������ ���� ��ȸ
	@Override
	public List<ScheduleEntity> getScheduleList(int promiseSeq) throws SQLException {
		return scheduleMapper.getScheduleList(promiseSeq);
	}

	// ������ ����
	@Override
	public int modifySchedule(ScheduleEntity scheduleEntity) throws SQLException {
		return scheduleMapper.modifySchedule(scheduleEntity);
	}

	// ������ ����
	@Override
	public int removeSchedule(int scheduleSeq) throws SQLException {
		return scheduleMapper.removeSchedule(scheduleSeq);
	}



}
