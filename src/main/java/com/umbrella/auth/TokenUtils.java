package com.umbrella.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class TokenUtils {

    private final static String secretKey ="aysudji28746hwndji237hd3o39fh372hldkw22jnuhyuhyt6789p0m";
    public static String getEmailFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey()) // Set your secret key here
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
