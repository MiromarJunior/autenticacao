package com.padrao.autenticacao.perfil.mapper;

import com.padrao.autenticacao.mapper.BaseMapper;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PerfilMapper {

    private final BaseMapper baseMapper;

    public PerfilMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @PostConstruct
    public void init() {
        ModelMapper modelMapper = baseMapper.getModelMapper();

//        // Mapeamento específico Perfil → PerfilResponse
//        modelMapper.typeMap(Perfil.class, PerfilResponse.class)
//                .addMapping(Perfil::getNome, PerfilResponse::setNome);
//
//        // Outro DTO da mesma entidade
//        modelMapper.typeMap(Perfil.class, PerfilSemPaginacao.class)
//                .addMapping(Perfil::getNome, PerfilSemPaginacao::setNome);
//    }
//
//    // Métodos de conveniência chamando BaseMapper
//    public PerfilResponse toPaginadoDto(Perfil perfil) {
//        return baseMapper.mapper(perfil, PerfilResponse.class);
//    }
//
//    public PerfilSemPaginacao toSemPaginacaoDto(Perfil perfil) {
//        return baseMapper.mapper(perfil, PerfilSemPaginacao.class);
//    }
    }
}

