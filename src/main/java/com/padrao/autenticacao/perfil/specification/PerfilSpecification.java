package com.padrao.autenticacao.perfil.specification;

import com.padrao.autenticacao.perfil.dto.request.ListarPerfilRequestPaginado;
import com.padrao.autenticacao.perfil.model.Perfil;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;
import java.util.stream.Stream;

public class PerfilSpecification {

    public static Specification<Perfil> filtrarPerfilPaginado(ListarPerfilRequestPaginado filtro) {
        return (root, query, builder) -> {

            Predicate predicatesAnd = getPredicatesAnd(filtro, root, builder);

            return builder.and(predicatesAnd);
        };
    }

    public static Specification<Perfil> filtrarPerfilPorNome(String nome){
        return (root, query, builder) -> {
            ListarPerfilRequestPaginado filtro = new ListarPerfilRequestPaginado();
            filtro.setNome(nome);
            Predicate predicatesAnd = getPredicatesAnd(filtro, root, builder);
            return builder.and(predicatesAnd);
        };
    }

    private static Predicate getPredicateNome(String nome, Root<Perfil> root, CriteriaBuilder builder) {
        if (StringUtils.isNotEmpty(nome)) {
            return builder.like(
                    builder.lower(root.get("nome")),
                    "%" + nome.toLowerCase() + "%"
            );
        }
        return null;
    }

    private static Predicate getPredicateDescricao(String descricao, Root<Perfil> root, CriteriaBuilder builder) {
        if (StringUtils.isNotEmpty(descricao)) {
            return builder.like(
                    builder.lower(root.get("descricao")),
                    "%" + descricao.toLowerCase() + "%"
            );
        }
        return null;
    }

    private static Predicate getPredicateStatus(Boolean status, Root<Perfil> root, CriteriaBuilder builder) {
        if (status != null) {
            return builder.equal(root.get("status"), status);
        }
        return null;
    }

    private static Predicate getPredicateNomeAdm(Root<Perfil> root, CriteriaBuilder builder) {
        return builder.notEqual(root.get("nome"), "ADM");
    }

    private static Predicate getPredicatesAnd(ListarPerfilRequestPaginado filtro, Root<Perfil> root, CriteriaBuilder builder) {

        Predicate[] predicates = getPredicates(
                getPredicateNome(filtro.getNome(), root, builder),
                getPredicateDescricao(filtro.getDescricao(), root, builder),
                getPredicateStatus(filtro.getStatus(), root, builder),
                getPredicateNomeAdm(root, builder)
        );
        return predicates.length > 0 ? builder.and(predicates) : builder.conjunction();
    }

    private static Predicate[] getPredicates(Predicate... predicates) {
        return Stream.of(predicates)
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);
    }
}
