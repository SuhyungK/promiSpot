package com.ssafy.promispot.vote.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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

import com.ssafy.promispot.promise.model.entity.PromiseEntity;
import com.ssafy.promispot.vote.model.entity.VoteEntity;
import com.ssafy.promispot.vote.model.entity.VoteMemberEntity;
import com.ssafy.promispot.vote.model.service.VoteService;



@RestController
@CrossOrigin
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	VoteService voteService;
	
	
	//��� ��� �ĺ� ���
	@PostMapping("insert")
	public ResponseEntity<?> insertCandidatePlace(@RequestBody VoteEntity voteEntity) {
		try {
			int result = voteService.insertCandidatePlace(voteEntity);
			
			if(result != 0) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}	
	}
	
	
	
	
	
	//��� ��� �ĺ� ��������
	@GetMapping("get/{voteSeq}")
	public ResponseEntity<?> getCandidatePlace(@PathVariable("voteSeq") int voteSeq) {
		try {
			VoteEntity candidatePlace = voteService.getCandidatePlace(voteSeq);
			
			if (candidatePlace != null) {
				System.out.println("success work");
				return new ResponseEntity<VoteEntity>(candidatePlace, HttpStatus.OK);
			} else {
				System.out.println("fail work");
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	
	//��� ��� �ĺ��� �������� - �ϳ��� ��ӿ� ���� ��� ��� �ĺ���
	@GetMapping("getList/{promiseSeq}")
	public ResponseEntity<?> getCandidatePlaceList(@PathVariable("promiseSeq") int promiseSeq) {
		try {
			
			List<VoteEntity> candidatePlaceList = voteService.getCandidatePlaceList(promiseSeq);
			
			if (candidatePlaceList != null) {
				System.out.println("success work");
				return new ResponseEntity<List<VoteEntity>>(candidatePlaceList, HttpStatus.OK);
			} else {
				System.out.println("fail work");
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	
	
	
	
	//��� ��� �ĺ� ����(��ǥ/��ǥ���)
	@PutMapping("modify/{voteSeq}")
	public ResponseEntity<?> modifyCandidatePlace(@PathVariable("voteSeq") int voteSeq) {
		
		try {
			
			int result = voteService.modifyCandidatePlace(voteSeq);
			

			if (result != 0) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	
	//��� ��� �ĺ� ����
	@DeleteMapping("delete/{voteSeq}")
	public ResponseEntity<?> removeCandidatePlace(@PathVariable("voteSeq") int voteSeq) {
		
		try {
			int result = voteService.removeCandidatePlace(voteSeq);
			if (result != 0) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	
	//����ڰ� �� ��ӿ��� �� ��Ҹ� ��ǥ�� ���� �ƴ��� ��ȸ
	@GetMapping("isVoted/{memberSeq}/{voteSeq}")
	public ResponseEntity<?> getPromiseList(@PathVariable("memberSeq") int memberSeq, @PathVariable("voteSeq") int voteSeq) {
		try {
			
			int result = voteService.isVoted(memberSeq, voteSeq);
			
			if (result != 0) {
				System.out.println("success work");
				return new ResponseEntity<String>("1", HttpStatus.OK);
			} else {
				System.out.println("fail work");
				return new ResponseEntity<String>("0", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
	
	//��� ��� ��ǥ��ư ������ ��ǥ�� ���̺� �߰�
	@PostMapping("member/insert")
	public ResponseEntity<?> insertVoter(@RequestBody VoteMemberEntity voteMemberEntity) {
		try {
			
			int result = voteService.insertVoter(voteMemberEntity);
			
			if(result != 0) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}	
	}
	
	
	//��� ��� ��ǥ�� �����ϸ� ��ǥ�� ���̺��� ����
	@DeleteMapping("delete/{memberSeq}/{voteSeq}")
	public ResponseEntity<?> removeVoter(@PathVariable("memberSeq") int memberSeq, @PathVariable("voteSeq") int voteSeq) {
		
		try {
			int result = voteService.removeVoter(memberSeq, voteSeq);
			
			if (result != 0) {
				return new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	
	// ���� ó��
	private ResponseEntity<String> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
