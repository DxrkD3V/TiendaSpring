package es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.services.jwt;

import es.iesclaradelrey.da2d1e2425.shopaymendavidrodrigo.entities.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService{
    @Value("${spring.security.jwt.signing-key-secret}")
    private String singningKeySecret;

    @Value("${spring.security.jwt.ascces-token.ttl}")
    private long accesTokenTtl;

    @Value("${spring.security.jwt.refresh-token.ttl}")
    private long refreshTokenTtl;

    @Override
    public String generatedAccessToken(AppUser user){
        SecretKey key = Keys.hmacShaKeyFor(singningKeySecret.getBytes());

        return Jwts.builder()
                .claim("type", JwtTokenType.ACCESS)
                .subject(user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + accesTokenTtl))
                .signWith(key)
                .compact();
    }

    @Override
    public String generatedRefreshToken(AppUser user){
        SecretKey key = Keys.hmacShaKeyFor(singningKeySecret.getBytes());

        return Jwts.builder()
                .claim("type", JwtTokenType.REFRESH)
                .subject(user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + refreshTokenTtl))
                .signWith(key)
                .compact();
    }

    @Override
    public void validateAccessToken(String token){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void validateRefreshToken(String token){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
