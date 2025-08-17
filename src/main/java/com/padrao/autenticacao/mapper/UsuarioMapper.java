package com.padrao.autenticacao.mapper;

import com.padrao.autenticacao.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.dto.response.UsuarioResponse;
import com.padrao.autenticacao.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toEntity(CadastrarUsuarioRequest dto);

    UsuarioResponse toDto(Usuario entity);

    List<UsuarioResponse> toDto(List<Usuario> entities);
}
