package com.padrao.autenticacao.perfil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_ACESSO", schema = "SCH_AUTENTICACAO")
public class Acesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String descricao;

    @ManyToMany(mappedBy = "acessos", fetch = FetchType.LAZY)
    private Set<Perfil> perfis = new HashSet<>();
}
