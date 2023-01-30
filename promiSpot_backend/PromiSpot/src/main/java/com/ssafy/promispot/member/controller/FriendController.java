package com.ssafy.promispot.member.controller;

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

import com.ssafy.promispot.member.model.entity.FriendRequestEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;
import com.ssafy.promispot.member.model.service.FriendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController
@RequestMapping("/friend")
@Api("ģ�� ��Ʈ�ѷ�  API")
public class FriendController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private FriendService friendService;
	
	// ģ�� ��û
	@ApiOperation(value = "ģ�� ��û", notes = "ģ�� ��û. DB�Է� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@PostMapping("/request")
	public ResponseEntity<?> requestFriend(@RequestBody @ApiParam(value="ģ�� ��û", required=true) 
		FriendRequestEntity friendRequestEntity) {	
		
		try {
			if(friendService.requestFriend(friendRequestEntity)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//requestFriend
	
	// ģ�� ��û ��� ��ȸ - ������ �� ģ�� ��û(0), ���� ��û�� ģ�� ����(1)
	@ApiOperation(value = "ģ�� ��û ��� ��ȸ", notes = "order : ������ �� ģ�� ��û(0), ���� ��û�� ģ�� ����(1)", response = String.class)
	@GetMapping("/{memberSeq}/{order}")
	public ResponseEntity<?> getRequestFriend(@RequestBody @ApiParam(value="ȸ���Ϸù�ȣ, ����0/����1", required=true) 
		@PathVariable("memberSeq") int memberSeq, @PathVariable("order") int order){
		
		try {
			List<MemberEntity> memberList = friendService.getRequestFriend(memberSeq, order);
			if(memberList != null) {
				return new ResponseEntity<List<MemberEntity>>(memberList, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}//getRequestFriend
	
	// ģ�� ��û ����
	@ApiOperation(value = "ģ�� ��û ����", notes = "ģ�� ��û ����. DB update �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@PutMapping("request/{friendRequestSeq}")
	public ResponseEntity<?> approvalFriend(@RequestBody @ApiParam(value="ģ�� ��û �Ϸù�ȣ", required=true) 
	@PathVariable("friendRequestSeq") int friendRequestSeq){
		
		try {
			if(friendService.approvalFriend(friendRequestSeq)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//approvalFriend
	
	// ģ�� ��û ����
	@ApiOperation(value = "ģ�� ��û ����", notes = "ģ�� ��û ����. DB update �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class)
	@DeleteMapping("request/{friendRequestSeq}")
	public ResponseEntity<?> rejectFriend(@RequestBody @ApiParam(value="ģ�� ��û �Ϸù�ȣ", required=true) 
		@PathVariable("friendRequestSeq") int friendRequestSeq){
		
		try {
			if(friendService.rejectFriend(friendRequestSeq)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//rejectFriend
	
	// ģ�� ���� ��ȸ (ģ�� �˻�)
	@ApiOperation(value = "ģ�� ���� ��ȸ", notes = "ģ�� ���� ��ȸ (ģ�� �˻�). ���̵� Ȥ�� ��ȭ��ȣ�� �˻��� ģ���� ��ȯ�Ѵ�. ", response = String.class)
	@GetMapping("/{memberInfo}")
	public ResponseEntity<?> findFriend(@RequestBody @ApiParam(value="ģ�� ���̵� Ȥ�� ��ȭ��ȣ", required=true) 
		@PathVariable("memberInfo") String memberInfo){
		
		try {
			MemberEntity member = friendService.findFriend(memberInfo);
			if(member != null) {
				return new ResponseEntity<MemberEntity>(member, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//findFriend
	
	// ģ�� ��� ��ȸ
	@ApiOperation(value = "ģ�� ��� ��ȸ", notes = "ģ�� ��� ��ȸ ���� �α��� �� ������� ģ������� ��ȯ�Ѵ�. ", response = String.class)
	@GetMapping("friends/{memberSeq}")
	public ResponseEntity<?> findFriendList(@RequestBody @ApiParam(value="ȸ�� �Ϸ� ��ȣ", required=true) 
		@PathVariable("memberSeq") int memberSeq){
		
		try {
			List<MemberEntity> memberList = friendService.findFriendList(memberSeq);
			if(memberList != null) {
				return new ResponseEntity<List<MemberEntity>>(memberList, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
		
	}//findFriendList

	// ���� ó��
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}//exceptionHandling
		
}//FriendController
