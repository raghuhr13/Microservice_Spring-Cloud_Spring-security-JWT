package com.hr.scms.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	//private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";
	private static final long EXPIRATION_TIME = 86400000; // 1 day

	@Value("${jwt.secretkey}") 
	private String SECRETEKEY;

	public String generateToken(String username) {
        System.out.println(SECRETEKEY);
        System.out.println(Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(getKey()).compact());
		return Jwts.builder().setSubject(username).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(getKey()).compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	//Instead of getKey() at setSigningKey(SignatureAlgorithm.HS256, SECRET_KEY) can be used like this
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRETEKEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
