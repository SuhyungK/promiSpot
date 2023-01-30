package com.ssafy.promispot.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.promispot.member.model.entity.FileEntity;
import com.ssafy.promispot.member.model.entity.MemberEntity;
import com.ssafy.promispot.member.model.service.JwtService;
import com.ssafy.promispot.member.model.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController
@RequestMapping("/member")
@Api("ȸ�� ��Ʈ�ѷ�  API")
public class MemberController {
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtService jwtService; 

	@Autowired
	private MemberService memberService;
	
	/* �α��� */
	@ApiOperation(value="�α���", notes="Access-token�� �α��� ��� �޼����� ��ȯ�Ѵ�.", response=Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value="�α��� �� �ʿ��� ȸ�� ����(���̵�, ��й�ȣ)" 
		, required=true) MemberEntity memberEntity){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		try {
			MemberEntity loginMember = memberService.loginMember(memberEntity);
			if(loginMember != null) {
				String accessToken = jwtService.createAccessToken("memberId", loginMember.getMemberId()); // key, data
				String refreshToken = jwtService.createRefreshToken("memberId", loginMember.getMemberId()); // key, data
				memberService.saveRefreshToken(memberEntity.getMemberId(), refreshToken);
				logger.debug("�α��� accessToken ���� : {}", accessToken);
				logger.debug("�α��� refreshToken ���� : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.NO_CONTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
		
	}//login
	
	/* �α׾ƿ� */
	@ApiOperation(value="�α׾ƿ�", notes="�α׾ƿ�. ȸ�� ������ ���� Token ����.", response=Map.class)
	@GetMapping("/logout/{memberId}")
	public ResponseEntity<?> logout(@PathVariable("memberId") String memberId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(memberId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("�α׾ƿ� ����.");
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}//logout
	
	
	/* ȸ������ */
	@ApiOperation(value = "ȸ������ ", notes = "���ο� ȸ�� ������ �Է��Ѵ�. DB�Է� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�. ", response = String.class )
	@PostMapping
	public ResponseEntity<?> registMember(@RequestBody @ApiParam(value="ȸ������", required=true) 
		MemberEntity memberEntity) {
//		System.out.println("ȸ�����Խõ�");
		logger.info("registMember - ȣ��");
		
		try {
			if(memberService.registMember(memberEntity)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}//registMember
	
	
	/* ȸ�� ���� ���� */
	@ApiOperation(value = "ȸ�� ���� ���� ", notes = "ȸ�� ������ �����Ѵ�. �׸��� DB ���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
	@PostMapping("/{memberSeq}")
	public ResponseEntity<?> modifyMember(
				@RequestParam(value="memberSeq",required = false) int memberSeq,
				@RequestParam(value="memberId",required = false) String memberId,
				@RequestParam(value="memberPass",required = false) String memberPass,
				@RequestParam(value="memberName",required = false) String memberName,
				@RequestParam(value="memberNick",required = false) String memberNick,
				@RequestParam(value="memberPhoneNum",required = false) String memberPhoneNum,
				@RequestParam(value="file",required = false) MultipartFile multipartFile
			) {
		
		FileEntity file = new FileEntity();
		// �̹��� ������ �ִٸ�.
		if(!multipartFile.isEmpty()) {
			try {
				file = memberService.saveFile(multipartFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//if
		
		MemberEntity memberEntity = new MemberEntity(memberSeq, memberPass, memberName, memberNick, memberPhoneNum
													, file.getImgPath(), file.getImgOriginName(), file.getImgServerName());
		
		try {
			if(memberService.modifyMember(memberEntity)) {
				System.out.println("memberModify SUCCESS!");
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}//modifyMember
	
//	/* ȸ�� ���� ���� */
//	@ApiOperation(value = "ȸ�� ���� ���� ", notes = "ȸ�� ������ �����Ѵ�. �׸��� DB ���� �������ο� ���� 'success' �Ǵ� 'fail' ���ڿ��� ��ȯ�Ѵ�.", response = String.class)
//	@PutMapping("/{memberSeq}")
//	public ResponseEntity<?> modifyMember(@RequestBody @ApiParam(value="ȸ����������", required = true) 
//	MemberEntity memberEntity) {
//		try {
//			if(memberService.modifyMember(memberEntity)) {
//				System.out.println("memberModify SUCCESS!");
//				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//			}else {
//				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);			
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return exceptionHandling(e);
//		}
//	}//modifyMember

	
	/* ȸ�� Ż�� */
	@ApiOperation(value = "ȸ��Ż�� ", notes = "ȸ��Ż�� 'success' or 'fail' ���ڿ� ��ȯ ", response = String.class ) 
	@DeleteMapping("/{memberSeq}")
	public ResponseEntity<?> removeMember(@RequestBody @ApiParam(value="ȸ��Ż��", required=true) 
	@PathVariable int memberSeq) {
		try {
			if(memberService.removeMember(memberSeq)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}//removeMember

	
	/* ȸ�� ���� ��ȸ */
	@ApiOperation(value = "ȸ�� ���� ��ȸ ", notes = "ȸ�� ���� ��ȸ or 'fail' ���ڿ� ��ȯ ", response = MemberEntity.class) 
	@GetMapping("/{memberSeq}")
	public ResponseEntity<?> findMember(@RequestBody @PathVariable("memberSeq") 
		@ApiParam(value="ȸ���Ϸù�ȣ", required=true) int memberSeq, HttpServletRequest request) {
//		System.out.println("ȸ�� ���� ��ȸ "+memberSeq);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if(jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("��� ���� ��ū");
			MemberEntity member;
			try {
				member = memberService.findMember(memberSeq);
				if(member != null) { // ȸ�� ����
					resultMap.put("memberInfo", member);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}else { // ȸ�� ����
					resultMap.put("message", FAIL);
					status = HttpStatus.NO_CONTENT;			
				}
			} catch (Exception e) { // ����
				logger.error("������ȸ ���� : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			
		} else {
			logger.error("��� �Ұ��� ��ū");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
				
	}//findMember
	
	/* ȸ�� ��� ��ȸ */
	@ApiOperation(value = "ȸ�� ��� ��ȸ ", notes = "ȸ�� ���� ��ȸ or 'fail' ���ڿ� ��ȯ ", response = MemberEntity.class) 
	@GetMapping("/memberList")
	public ResponseEntity<?> findMemberList()  {
		System.out.println("ȸ�� ��� ��ȸ ");
		try {
			List<MemberEntity> memberList = memberService.findMemberList();
			if(memberList != null) {
				return new ResponseEntity<List<MemberEntity>>(memberList, HttpStatus.OK);					
			}else {
				return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);									
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}		
	}//findMemberList
	
	/* ��ū ��߱� */
	@ApiOperation(value="AccessToken ��߱�", notes="����� access token�� ��߱� �޴´�.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberEntity memberEntity, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberEntity : {}", token, memberEntity);
		if(jwtService.checkToken(token)) {
			try {
				if(token.equals(memberService.getRefreshToken(memberEntity.getMemberId()))) {
					String accessToken = jwtService.createAccessToken("memberId", memberEntity.getMemberId());
					logger.debug("token : {}", accessToken);
					logger.debug("���������� access token ��߱�");
					resultMap.put("access-token", accessToken);
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.debug("refresh token�� ��� �Ұ���.");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}//refreshToken
	
	// ���� ó��
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}//exceptionHandling

}//MemberController
