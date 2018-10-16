package com.kiss.account.utils;

import com.kiss.account.output.AuthOutput;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JwtUtil {
    private static final String SECRET = "Z~9T2*rTgm9W~I>3";
    private static final long EXPIRATION = 1000 * 60 * 60 * 24;

    public static AuthOutput getToken(int userId, String username) {
        String key = UUID.randomUUID().toString();
        Date expiredDate = computeExpired();

        String token = Jwts.builder()
                .setIssuer(username)
                .setSubject(userId + "")
                .setId(key)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        AuthOutput authOutput = new AuthOutput();
        authOutput.setAccessToken(token);
        authOutput.setExpiredAt(expiredDate.getTime());

        return authOutput;
    }

    public static String getUserId(String token) {
        String userId;
        try {
            userId = getClaims(token).getSubject();
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public static String getUserName(String token) {
        String username;
        try {
            username = getClaims(token).getIssuer();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static boolean isNotExpired(String token) {
        final Date expired = getExpired(token);
        return expired.after(new Date());
    }

    public static Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static Date getExpired(String token) {
        Date expired;
        try {
            final Claims claims = getClaims(token);
            expired = claims.getExpiration();
        } catch (Exception e) {
            expired = null;
        }
        return expired;
    }

    private static Date computeExpired() {
        return new Date(System.currentTimeMillis() + EXPIRATION);
    }


    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpT1MiLCJqdGkiOiIzOTAxNzFjZC1kZWQxLTQwZmQtYTViNi1hNmEzYmI2NGM4ZjMiLCJleHAiOjE1MzUwOTg3ODN9.Hdl6Kzl_DMdqT6mSOO6WFMlydtravrlg8gswehbChjM3E9Nzu0rgXK8eADaKarDek9IR6N_QqcYHTgQLdBUm3g";
        System.out.println(getExpired(token));
    }


}
