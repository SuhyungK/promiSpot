package com.ssafy.promispot.address.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.promispot.address.model.entity.AddressEntity;
import com.ssafy.promispot.address.model.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressMapper addressMapper;

	// ȸ�� �ּ� ���
	@Override
	public boolean addAddress(AddressEntity addressEntity) throws SQLException {		
		return addressMapper.addAddress(addressEntity) == 1;
	}//addAddress

	// ȸ�� �ּ� ��ȸ
	@Override
	public AddressEntity getAddress(int addressSeq) throws SQLException {
		return addressMapper.getAddress(addressSeq);
	}//getAddress

	// ȸ�� �ּ� ����
	@Override
	public boolean modifyAddress(AddressEntity addressEntity) throws SQLException {
		return addressMapper.modifyAddress(addressEntity) == 1;
	}//modifyAddress

	// ȸ�� �ּ� ����
	@Override
	public boolean removeAddress(int addressSeq) throws SQLException {
		return addressMapper.removeAddress(addressSeq) == 1;
	}//removeAddress

	// ȸ�� �ּ� ��� ��ȸ
	@Override
	public List<AddressEntity> getAddressList(int memberSeq) throws SQLException {
		return addressMapper.getAddressList(memberSeq);
	}//getAddressList

}//AddressServiceImpl
