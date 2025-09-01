package com.padrao.autenticacao.perfil.util;

import lombok.experimental.UtilityClass;

import java.text.Normalizer;

@UtilityClass
public class PerfilUtils {

    public static String gerarNome(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia!");
        }

        // 1. Remover espaços antes/depois
        String nome = descricao.trim();

        // 2. Remover acentos
        nome = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // 3. Substituir espaços internos por _
        nome = nome.replaceAll("\\s+", "_");

        // 4. Converter para maiúsculas
        nome = nome.toUpperCase();

        return nome;
    }
}
