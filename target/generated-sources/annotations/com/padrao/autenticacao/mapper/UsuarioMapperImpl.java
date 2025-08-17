package com.padrao.autenticacao.mapper;

import com.padrao.autenticacao.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.dto.response.UsuarioResponse;
import com.padrao.autenticacao.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-16T23:46:25-0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toEntity(CadastrarUsuarioRequest dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( dto.getNome() );
        usuario.setEmail( dto.getEmail() );

        return usuario;
    }

    @Override
    public UsuarioResponse toDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioResponse usuarioResponse = new UsuarioResponse();

        usuarioResponse.setId( entity.getId() );
        usuarioResponse.setNome( entity.getNome() );
        usuarioResponse.setEmail( entity.getEmail() );

        return usuarioResponse;
    }

    @Override
    public List<UsuarioResponse> toDto(List<Usuario> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UsuarioResponse> list = new ArrayList<UsuarioResponse>( entities.size() );
        for ( Usuario usuario : entities ) {
            list.add( toDto( usuario ) );
        }

        return list;
    }
}
