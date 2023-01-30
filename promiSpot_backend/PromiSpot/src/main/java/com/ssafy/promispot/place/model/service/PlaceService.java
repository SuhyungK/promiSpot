package com.ssafy.promispot.place.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.promispot.place.model.entity.PlaceEntity;


public interface PlaceService {
	
	// ��ҵ�� 
	public int insertPlace(PlaceEntity placeEntity) throws SQLException;
	
	// ��� ��ȸ
	public PlaceEntity getPlace(String placeId) throws SQLException;
	
	// ��� ����
	public int modifyPlace(PlaceEntity placeEntity) throws SQLException;
	
	// ��� ����
	public int removePlace(String placeId) throws SQLException;

}
