package com.padrao.autenticacao.usuario.specification;

import com.padrao.autenticacao.usuario.dto.request.ListarUsuarioRequest;
import com.padrao.autenticacao.usuario.model.Usuario;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.stream.Stream;

public class UsuarioSpecification {

    public static Specification<Usuario> filtrarUsuarioPaginado(ListarUsuarioRequest filtro) {
        return (root, query, builder) -> {

            Predicate predicatesAnd = getPredicatesAnd(filtro, root, builder);

            return builder.and(predicatesAnd);
        };
    }

    private static Predicate getPredicateNome(String nome, Root<Usuario> root, CriteriaBuilder builder) {
        if (StringUtils.isNotEmpty(nome)) {
            return builder.like(
                    builder.lower(root.get("nome")),
                    "%" + nome.toLowerCase() + "%"
            );
        }
        return null;
    }

    private static Predicate getPredicateEmail(String email, Root<Usuario> root, CriteriaBuilder builder) {
        if (StringUtils.isNotEmpty(email)) {
            return builder.equal(builder.lower(root.get("nome")), email.toLowerCase());
        }
        return null;
    }

    private static Predicate getPredicatesAnd(ListarUsuarioRequest filtro, Root<Usuario> root, CriteriaBuilder builder) {

        Predicate[] predicates = getPredicates(
                getPredicateNome(filtro.getNome(), root, builder),
                getPredicateEmail(filtro.getEmail(), root, builder)
        );
        return predicates.length > 0 ? builder.and(predicates) : builder.conjunction();
    }

    private static Predicate[] getPredicates(Predicate... predicates) {
        return Stream.of(predicates)
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);
    }
}
