package com.br.login.util;

import com.br.login.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;

import static java.lang.String.format;

@Component
public class JwtTokenUtil implements Serializable {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP512lm2123113513nlm15161515";

    private final static String PREFIX_TOKEN = "Bearer ";

    public String generateAccessToken(UserDetails usuario) {
        return PREFIX_TOKEN + Jwts.builder()
                .setSubject(format("%s", usuario.getUsername()))
                .setIssuer(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        var keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
