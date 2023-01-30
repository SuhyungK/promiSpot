package com.ssafy.promispot.member.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.promispot.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtServiceImpl implements JwtService {
	
	/* 
	 * AccessToken : �Ź� �ΰ��� ���� �� ����ϴ� ��ū. (���� ������ ª��.)
	 * RefreshToken : AccessToken�� ������ ������ �� AccessToken�� ����� �ޱ� ���� ��ū. (���� 2�� ������ �Ⱓ�� ��� ������.)
	 * �������� �α׾ƿ� ��Ű���� refreshToken�� db���� ���������� �Ǵµ� �׷��� accessToken�� �������� �ٷ� ������ ����� ����.
	*/
	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	
	private static final String SALT = "promispotSecret";
	private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 1; // �� ���� 
	private static final int REFRESH_TOKEN_EXPIRE_WEEKS = 2; // �� ����

	@Override
	public <T> String createAccessToken(String key, T data) {
		return create(key, data, "access-token", 1000 * 60 * ACCESS_TOKEN_EXPIRE_MINUTES);
	}//createAccessToken

	@Override
	public <T> String createRefreshToken(String key, T data) { // AccessToken�� ���� ��ȿ�Ⱓ�� ���
		return create(key, data, "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE_WEEKS);
	}//createRefreshToken

	/*
	 * key : Claim�� ���õ� key ��
	 * data : Claim�� ���õ� data ��
	 * subject : payload�� sub�� value�� �� subject ��
	 * expire : ��ū ��ȿ�Ⱓ ������ ���� ��
	 * jwt ��ū�� ���� : header + payload + signature
	 */
	@Override
	public <T> String create(String key, T data, String subject, long expire) {
		String jwt = Jwts.builder()
				// Header ���� : ��ū�� Ÿ��, �ؽ� �˰��� ���� ����
					.setHeaderParam("typ", "JWT")
					.setHeaderParam("regDate", System.currentTimeMillis()) // �����ð�
					// payload ���� : ��ȿ�Ⱓ(Expriration), ��ū ����(Subject), ������(Claim) �� ���� ����)
					.setExpiration(new Date(System.currentTimeMillis() + expire)) // ��ū ��ȿ�Ⱓ
					.setSubject(subject) // ��ū ���� ���� ex) access-token, refresh-token
					.claim(key, data) // ������ ������
					// Signature ���� : secret key�� Ȱ���� ��ȣȭ
					.signWith(SignatureAlgorithm.HS256, this.generateKey())
					.compact(); // ����ȭ ó��
		return jwt;
	}//create

	// Signature ������ �� key ����
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		}catch(UnsupportedEncodingException e) {
			if(logger.isInfoEnabled()) {
				e.printStackTrace();
			}else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		return key;
	}//generateKey

	@Override
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
									.getRequest();
		String jwt = request.getHeader("access-token");
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new UnAuthorizedException();
		}
		
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		
		return value;
	}//get

	@Override
	public int getMemberSeq() {
		return (int) this.get("member").get("memberSeq");
	}//getMemberId

	// ���� ���� ��ū�� ����� �����Ȱ����� Ȯ�� �ϰ� ������ �ִٸ� UnauthorizedException�� �߻�.
	@Override
	public boolean checkToken(String jwt) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			logger.debug("claims: {}", claims);
			return true;
		}catch(Exception e) {
			logger.error(e.getMessage());
			return false;			
		}
	}//checkToken

}//JwtServiceImpl
