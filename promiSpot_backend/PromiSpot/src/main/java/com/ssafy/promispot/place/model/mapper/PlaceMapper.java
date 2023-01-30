package com.ssafy.promispot.place.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.promispot.place.model.entity.PlaceEntity;

@Mapper
public interface PlaceMapper {
	
	// ��� ���
	public int insertPlace(PlaceEntity placeEntity) throws SQLException;
	
	// ��� ��ȸ
	public PlaceEntity getPlace(String placeId) throws SQLException;
	
	// ��� ����
	public int modifyPlace(PlaceEntity placeEntity) throws SQLException;
	
	// ��� ����
	public int removePlace(String placeId) throws SQLException;
	
	
	

}
