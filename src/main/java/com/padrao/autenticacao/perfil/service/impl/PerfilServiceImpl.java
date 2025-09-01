package com.padrao.autenticacao.perfil.service.impl;

import com.padrao.autenticacao.exception.DuplicidadeException;
import com.padrao.autenticacao.mapper.BaseMapper;
import com.padrao.autenticacao.perfil.dto.request.CriarPerfilRequest;
import com.padrao.autenticacao.perfil.dto.request.ListarPerfilRequestPaginado;
import com.padrao.autenticacao.perfil.dto.response.PerfilResponse;
import com.padrao.autenticacao.perfil.model.Perfil;
import com.padrao.autenticacao.perfil.repository.PerfilRepository;
import com.padrao.autenticacao.perfil.service.PerfilService;
import com.padrao.autenticacao.perfil.specification.PerfilSpecification;
import com.padrao.autenticacao.perfil.util.PerfilUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final PerfilRepository perfilRepository;

    private final BaseMapper baseMapper;

    public PerfilServiceImpl(PerfilRepository perfilRepository, BaseMapper baseMapper) {
        this.perfilRepository = perfilRepository;
        this.baseMapper = baseMapper;
    }

    @Override
    public Page<PerfilResponse> listarPaginado(ListarPerfilRequestPaginado filtro) {
        PageRequest paginacao = PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(filtro.getDirection(), filtro.getSort().getOrdenacao()));
        Page<Perfil> entities = perfilRepository.findAll(PerfilSpecification.filtrarPerfilPaginado(filtro), paginacao);

        return baseMapper.mapper(entities, PerfilResponse.class);
    }

    @Override
    public PerfilResponse salvar(CriarPerfilRequest request) {
        Perfil entity = baseMapper.mapper(request, Perfil.class);
        entity.setStatus(Boolean.TRUE);
        entity.setNome(PerfilUtils.gerarNome(request.getDescricao()));
        Optional<Perfil> perfilExistente = perfilRepository.findOne(PerfilSpecification.filtrarPerfilPorNome(entity.getNome()));
        if(perfilExistente.isPresent()) throw new DuplicidadeException("Já exite um perfil com esse nome");
        return baseMapper.mapper(perfilRepository.save(entity), PerfilResponse.class);
    }
}

