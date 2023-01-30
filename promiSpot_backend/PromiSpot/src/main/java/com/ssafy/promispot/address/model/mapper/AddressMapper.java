package com.ssafy.promispot.address.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.promispot.address.model.entity.AddressEntity;

@Mapper
public interface AddressMapper {
	
	// ȸ�� �ּ� ���
	public int addAddress(AddressEntity addressEntity) throws SQLException;
	
	// ȸ�� �ּ� ��ȸ
	public AddressEntity getAddress(int addressSeq) throws SQLException;
	
	// ȸ�� �ּ� ����
	public int modifyAddress(AddressEntity addressEntity) throws SQLException;
	
	// ȸ�� �ּ� ����
	public int removeAddress(int addressSeq) throws SQLException;
	
	// ȸ�� �ּҵ� ��ȸ
	public List<AddressEntity> getAddressList(int memberSeq) throws SQLException;

}//AddressMapper
