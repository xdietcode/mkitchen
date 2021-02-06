package com.mkitchen.server.utils;

import com.mkitchen.server.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.MyUserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    public String generateToken(MyUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000))
                .signWith(SignatureAlgorithm.HS256, Constant.getKey()).compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(Constant.getKey()).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, MyUserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public static void main(String[] args) {
        JwtUtil util = new JwtUtil();
        MyUserDetails details = new MyUserDetails(new User("alice", "1234", true, "user"));
        System.out.println("details: " + details);
        String token = util.generateToken(details);
        System.out.println("token: " + token);

        String username = util.extractUsername(token);
        Date exp = util.extractExpiration(token);
        System.out.println("Username: " + username);
        System.out.println("Exp date: " + exp.toString());
        System.out.println("Claims: " + util.extractAllClaims(token));
    }
}
