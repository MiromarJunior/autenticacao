package com.padrao.autenticacao.usuario.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastrarUsuarioRequest {

    @NotBlank(message = "Nome de usuário é obrigatório")
    @Size(max = 256)
    private String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    @Size(max = 200)
    private String email;

    @Size(min = 6, max = 30, message = "Senha deve ter entre {min} e {max} caracteres")
    @NotBlank(message = "Senha é obrigatório")
    private String senha;

    @NotNull
    private Long idPerfil;
}
