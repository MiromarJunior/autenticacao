package com.padrao.autenticacao.perfil.controller;

import com.padrao.autenticacao.perfil.dto.request.CriarPerfilRequest;
import com.padrao.autenticacao.perfil.dto.request.ListarPerfilRequestPaginado;
import com.padrao.autenticacao.perfil.dto.response.PerfilResponse;
import com.padrao.autenticacao.perfil.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
@Tag(name = "Perfil", description = "Endpoints para gerenciar perfis dos usuários")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @Operation(summary = "Lista perfis de forma paginada")
    @GetMapping("/paginado")
    public ResponseEntity<Page<PerfilResponse>> listarPaginado(ListarPerfilRequestPaginado request){
        return ResponseEntity.ok(perfilService.listarPaginado(request));
    }

    @PostMapping()
    @Operation(summary = "Criar um novo perfil")
    public ResponseEntity<PerfilResponse> criarPerfil(@Valid @RequestBody CriarPerfilRequest request){
        return ResponseEntity.ok(perfilService.salvar(request));
    }
}
