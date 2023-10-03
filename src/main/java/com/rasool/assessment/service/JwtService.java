package com.rasool.assessment.service;

import com.rasool.assessment.exception.JwtAuthenticationException;
import com.rasool.assessment.model.Users;
import com.rasool.assessment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {
    private static final String ISSUER = "rasool-test";
    public static final String USERNAME = "rasool";
    public static final String encodedKey = "!rasool-tech@2023";
    public static final int expirationHours = 8;

	private final Users user;

	public JwtService(Users user) {
		this.user = user;
	}

	@Autowired(required = true)
	private UserRepository userRepository;

	public String tokenFor(Users user) throws IOException, URISyntaxException {
		Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(expirationHours).toInstant(UTC));
		return Jwts.builder()
				.claim("userId", user.getUserId())
				.setExpiration(expiration)
				.setIssuer(ISSUER)
				.signWith(SignatureAlgorithm.HS512, encodedKey.getBytes())
				.compact();
	}

	public List<Object> verify(String token) throws IOException, URISyntaxException{
		List<Object> responseObjList = new ArrayList<Object>();
		try {
			System.out.println("VERIFY");
			System.out.println(token);
			Jws<Claims> claims = Jwts.parser().setSigningKey(encodedKey.getBytes()).parseClaimsJws(token);
			System.out.println("claims");
			System.out.println(claims);
			Users user = userRepository.findByUserId(Long.parseLong(claims.getBody().get("userId").toString()));
			responseObjList.add(user);
		} catch (Exception exception) {
			throw new JwtAuthenticationException("Failed to return token token", exception);
		}
		return responseObjList;
	}
}
