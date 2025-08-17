package com.padrao.autenticacao.service.impl;

import com.padrao.autenticacao.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.dto.request.ListarUsuarioRequest;
import com.padrao.autenticacao.dto.response.UsuarioResponse;
import com.padrao.autenticacao.exception.DuplicidadeException;
import com.padrao.autenticacao.mapper.UsuarioMapper;
import com.padrao.autenticacao.model.Usuario;
import com.padrao.autenticacao.repository.UsuarioRepository;
import com.padrao.autenticacao.service.UsuarioService;
import com.padrao.autenticacao.specification.UsuarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponse salvar(CadastrarUsuarioRequest request) {
        validarEmail(request.getEmail());
        Usuario entity = UsuarioMapper.INSTANCE.toEntity(request);
        Usuario savedEntity = usuarioRepository.save(entity);
        return UsuarioMapper.INSTANCE.toDto(savedEntity);
    }

    @Override
    public Page<UsuarioResponse> listarUsuarioPaginado(ListarUsuarioRequest filtro) {
        PageRequest paginacao = PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(filtro.getDirection(), filtro.getSort().getOrdenacao()));
        Page<Usuario> entities = usuarioRepository.findAll(
                UsuarioSpecification.filtrarUsuarioPaginado(filtro), paginacao
        );
        List<UsuarioResponse> dtos = UsuarioMapper.INSTANCE.toDto(entities.getContent());
        return new PageImpl<>(dtos, paginacao, entities.getTotalElements());
    }

    private void validarEmail(String email) {
        List<Usuario> lista = usuarioRepository.findByEmail(email);
        if (lista != null && !lista.isEmpty()) {
            throw new DuplicidadeException(String.format("O e-mail informado: %s, já está em uso!", email));
        }
    }

}
