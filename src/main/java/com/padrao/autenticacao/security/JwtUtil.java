package com.padrao.autenticacao.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.padrao.autenticacao.perfil.model.Acesso;
import com.padrao.autenticacao.usuario.model.Usuario;

import java.util.Date;
import java.util.List;
public class JwtUtil {

    private static final String SECRET = "sua_chave_secreta_aqui";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public static String generateToken(Usuario usuario) {
        List<String> acessos = usuario.getPerfil().getAcessos().stream()
                .map(Acesso::getNome)
                .toList();

        return JWT.create()
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withClaim("nome", usuario.getPerfil().getNome())
                .withClaim("acessos", acessos)
                .withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 min
                .sign(ALGORITHM);
    }

    public static DecodedJWT validateToken(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token);
    }
}
