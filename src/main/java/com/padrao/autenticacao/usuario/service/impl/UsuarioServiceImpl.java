package com.padrao.autenticacao.usuario.service.impl;

import com.padrao.autenticacao.usuario.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.usuario.dto.request.ListarUsuarioRequestPaginado;
import com.padrao.autenticacao.usuario.dto.response.UsuarioResponse;
import com.padrao.autenticacao.exception.DuplicidadeException;
import com.padrao.autenticacao.usuario.mapper.UsuarioMapper;
import com.padrao.autenticacao.usuario.model.Usuario;
import com.padrao.autenticacao.usuario.repository.UsuarioRepository;
import com.padrao.autenticacao.usuario.service.UsuarioService;
import com.padrao.autenticacao.usuario.specification.UsuarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UsuarioResponse salvar(CadastrarUsuarioRequest request) {
        validarEmail(request.getEmail());
        Usuario entity = UsuarioMapper.INSTANCE.toEntity(request);
        entity.setSenha(passwordEncoder.encode(entity.getSenha()));
        Usuario savedEntity = usuarioRepository.save(entity);
        return UsuarioMapper.INSTANCE.toDto(savedEntity);
    }

    @Override
    public Page<UsuarioResponse> listarUsuarioPaginado(ListarUsuarioRequestPaginado filtro) {
        PageRequest paginacao = PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(filtro.getDirection(), filtro.getSort().getOrdenacao()));
        Page<Usuario> entities = usuarioRepository.findAll(
                UsuarioSpecification.filtrarUsuarioPaginado(filtro), paginacao
        );
        List<UsuarioResponse> dtos = UsuarioMapper.INSTANCE.toDto(entities.getContent());
        return new PageImpl<>(dtos, paginacao, entities.getTotalElements());
    }

    private void validarEmail(String email) {
        boolean existe = usuarioRepository.findByEmail(email).isPresent();
        if (existe) {
            throw new DuplicidadeException(
                    String.format("O e-mail informado: %s já está em uso!", email)
            );
        }
    }

}
