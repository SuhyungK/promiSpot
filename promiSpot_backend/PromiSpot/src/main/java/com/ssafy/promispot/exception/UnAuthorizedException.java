package com.ssafy.promispot.exception;

public class UnAuthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = -2238030302650813813L;
	
	public UnAuthorizedException() {
		super("���� ������ ��ȿ���� �ʽ��ϴ�. \n �ٽ� �α��� ���ּ���.");
	}

}//UnAuthorizedException
