package com.padrao.autenticacao.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.padrao.autenticacao.model.Usuario;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "chave-secreta";
    private static final String ISSUER = "meu-app";

    public static String generateToken(Usuario usuario) {
        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withClaim("nome", usuario.getNome())
                .withClaim("role", "USER")
                .withIssuedAt(new Date())
                .withExpiresAt(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .sign(Algorithm.HMAC256(SECRET));
    }



    public static DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token);
    }

    public static String getEmail(String token) {
        return validateToken(token).getSubject();
    }

    public static Long getUserId(String token) {
        return validateToken(token).getClaim("id").asLong();
    }

    public static String getNome(String token) {
        return validateToken(token).getClaim("nome").asString();
    }
}

