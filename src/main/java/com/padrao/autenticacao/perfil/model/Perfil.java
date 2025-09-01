package com.padrao.autenticacao.perfil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PERFIL", schema = "SCH_AUTENTICACAO")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    private Boolean status = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "TB_ACESSO_PERFIL",
            schema = "SCH_AUTENTICACAO",
            joinColumns = @JoinColumn(name = "id_perfil"),
            inverseJoinColumns = @JoinColumn(name = "id_acesso"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_perfil", "id_acesso"})
    )
    private Set<Acesso> acessos = new HashSet<>();


}
