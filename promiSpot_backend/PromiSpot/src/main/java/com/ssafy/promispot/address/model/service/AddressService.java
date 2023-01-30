package com.ssafy.promispot.address.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.promispot.address.model.entity.AddressEntity;

public interface AddressService {
	
	// ȸ�� �ּ� ���
	public boolean addAddress(AddressEntity addressEntity) throws SQLException;
	
	// ȸ�� �ּ� ��ȸ
	public AddressEntity getAddress(int addressSeq) throws SQLException;
	
	// ȸ�� �ּ� ����
	public boolean modifyAddress(AddressEntity addressEntity) throws SQLException;
	
	// ȸ�� �ּ� ����
	public boolean removeAddress(int addressSeq) throws SQLException;
	
	// ȸ�� �ּ� ��� ��ȸ
	public List<AddressEntity> getAddressList(int memberSeq) throws SQLException;

}//AddressService
