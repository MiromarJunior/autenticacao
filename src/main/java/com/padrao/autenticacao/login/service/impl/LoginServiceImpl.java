package com.padrao.autenticacao.login.service.impl;

import com.padrao.autenticacao.login.dto.request.LoginRequest;
import com.padrao.autenticacao.perfil.model.Perfil;
import com.padrao.autenticacao.usuario.dto.response.LoginResponse;
import com.padrao.autenticacao.exception.UnauthorizedException;
import com.padrao.autenticacao.usuario.model.Usuario;
import com.padrao.autenticacao.usuario.repository.UsuarioRepository;
import com.padrao.autenticacao.security.JwtUtil;
import com.padrao.autenticacao.login.service.LoginService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new EntityNotFoundException("Usuário não encontrado com email: "+request.getEmail())
        );

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
          throw new UnauthorizedException("Credenciais inválidas");
        }
        Perfil perfil = usuario.getPerfil();

        return new LoginResponse(JwtUtil.generateToken(usuario));

    }
}
