package com.padrao.autenticacao.mapper;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;


@Getter
@Component
public class BaseMapper {

    // Exponha o ModelMapper caso precise de configuração extra
    private final ModelMapper modelMapper;

    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    // Mapeia um objeto único
        public <S, D> D mapper(S source, Class<D> destinationClass) {
            if (source == null) return null;
            return modelMapper.map(source, destinationClass);
        }

        // Mapeia uma lista
        public <S, D> List<D> mapper(List<S> source, Class<D> destinationClass) {
            if (source == null) return List.of();
            return source.stream()
                    .map(e -> modelMapper.map(e, destinationClass))
                    .toList();
        }

    // Mapeia um Page
    public <S, D> Page<D> mapper(Page<S> source, Class<D> destinationClass) {
        if (source == null) return Page.empty();
        return source.map(e -> modelMapper.map(e, destinationClass));
    }

}
