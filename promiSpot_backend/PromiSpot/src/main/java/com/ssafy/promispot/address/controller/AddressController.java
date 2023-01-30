package com.ssafy.promispot.address.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.promispot.address.model.entity.AddressEntity;
import com.ssafy.promispot.address.model.service.AddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@CrossOrigin
@RestController
@RequestMapping("/address")
@Api("ȸ�� �ּ� ��Ʈ�ѷ�  API")
public class AddressController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired 
	private AddressService addressService;
	
	// ȸ�� �ּ� ���
	@ApiOperation(value = "ȸ�� �ּ� ���", notes = "ȸ�� �ּ� ���. DB�Է� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@PostMapping
	public ResponseEntity<?> addAddress(@RequestBody @ApiParam(value="ȸ�� �ּ�", required=true) 
		AddressEntity addressEntity){
		
		try {
			if(addressService.addAddress(addressEntity)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//addAddress
	
	
	// ȸ�� �ּ� ��ȸ
	@ApiOperation(value = "ȸ�� �ּ� �� ��ȸ", notes = "ȸ�� �ּ� �� ��ȸ. DB ��ȸ �������ο� ���� AddressEntity �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@GetMapping("/{addressSeq}")
	public ResponseEntity<?> getAddress(@RequestBody @ApiParam(value="ȸ�� �ּ� �Ϸù�ȣ", required=true) 
		@PathVariable("addressSeq") int addressSeq){
		
		try {
			AddressEntity address = addressService.getAddress(addressSeq);
			if(address != null) {
				return new ResponseEntity<AddressEntity>(address, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		} 
		
	}//getAddress
	
	
	// ȸ�� �ּ� ����
	@ApiOperation(value = "ȸ�� �ּ� ����", notes = "ȸ�� �ּ� ����. DB ���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@PutMapping
	public ResponseEntity<?> modifyAddress(@RequestBody @ApiParam(value="ȸ�� �ּ�", required=true) 
		AddressEntity addressEntity){
		
		try {
			if(addressService.modifyAddress(addressEntity)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//modifyAddress
	
	
	// ȸ�� �ּ� ����
	@ApiOperation(value = "ȸ�� �ּ� ����", notes = "ȸ�� �ּ� ����. DB ���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@DeleteMapping("/{addressSeq}")
	public ResponseEntity<?> removeAddress(@RequestBody @ApiParam(value="ȸ�� �ּ� �Ϸù�ȣ", required=true) 
		@PathVariable("addressSeq") int addressSeq){
		
		try {
			if(addressService.removeAddress(addressSeq)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//removeAddress

	
	// ȸ�� �ּ� ��� ��ȸ
	@ApiOperation(value = "ȸ�� �ּ� ��� ��ȸ", notes = "ȸ�� �ּ� ��� ��ȸ. DB ��� ��ȸ �������ο� ���� �ּ� ��� �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@GetMapping("/addressList/{memberSeq}")
	public ResponseEntity<?> getAddressList(@RequestBody @ApiParam(value="ȸ�� �Ϸù�ȣ", required=true) 
		@PathVariable("memberSeq") int memberSeq){
		
		try {
			List<AddressEntity> addressList = addressService.getAddressList(memberSeq);
			if(addressList != null) {
				return new ResponseEntity<List<AddressEntity>>(addressList, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//getAddressList

	// ���� ó��
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}//exceptionHandling
		
}//AddressController
