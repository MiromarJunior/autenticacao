package com.padrao.autenticacao.controller;

import com.padrao.autenticacao.dto.request.CadastrarUsuarioRequest;
import com.padrao.autenticacao.dto.request.ListarUsuarioRequest;
import com.padrao.autenticacao.dto.response.UsuarioResponse;
import com.padrao.autenticacao.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(
          @Valid @RequestBody CadastrarUsuarioRequest request){
        UsuarioResponse response = usuarioService.salvar(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<UsuarioResponse>> listarPaginado(
            ListarUsuarioRequest request){
        Page<UsuarioResponse> response = usuarioService.listarUsuarioPaginado(request);
        return ResponseEntity.ok(response);
    }

}
