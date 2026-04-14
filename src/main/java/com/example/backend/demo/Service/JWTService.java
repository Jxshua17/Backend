package com.example.backend.demo.Service;

import com.example.backend.demo.Model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    private String privateKey = "";

    public JWTService() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            privateKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String usernmae){

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(usernmae)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (60*60*300)))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Key getKey(){
        byte[] bytes = Decoders.BASE64.decode(privateKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getKey())
                .build().parseSignedClaims(token).getPayload();
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean validateToken(String token, UserDetails details) {
        final String username = extractUsername(token);
        return (username.equals(details.getUsername()) && !isTokenExpired(token));
    }

}
