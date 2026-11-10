package com.senai.sagsa.controllers; // Pacote da camada de controladores REST

import com.senai.sagsa.models.Usuario; // Modelo de domínio da entidade Usuario
import com.senai.sagsa.models.dtos.UsuarioCreateDTO; // DTO com validações para criação[cite: 6]
import com.senai.sagsa.services.UsuarioService; // Camada de regras de negócio
import jakarta.validation.Valid; // Ativa a validação automática das anotações do DTO (@NotBlank, @Size)[cite: 13]
import org.springframework.beans.factory.annotation.Autowired; // Injeção de dependência pelo Spring
import org.springframework.http.ResponseEntity; // Representação da resposta HTTP completa (status, headers, body)[cite: 13]
import org.springframework.web.bind.annotation.*; // Anotações de roteamento REST (@RestController, @GetMapping, etc.)
import org.springframework.web.servlet.support.ServletUriComponentsBuilder; // Utilitário para construir o header Location[cite: 13]

import java.net.URI; // Objeto de URI para o retorno da rota de recurso criado
import java.util.List; // Coleção de lista do Java

@RestController // Define a classe como controlador REST com respostas em JSON
@RequestMapping("/usuarios") // Rota base para todos os endpoints deste controlador
public class UsuarioController {

    @Autowired // Injeção do serviço de regras de negócio[cite: 11, 12]
    private UsuarioService usuarioService;

    // Endpoint GET /usuarios/{id} - Busca um utilizador específico pelo seu identificador[cite: 10, 19]
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario obj = usuarioService.findById(id); // Obtém o utilizador através do serviço[cite: 10]
        return ResponseEntity.ok().body(obj); // Retorna HTTP 200 OK com o JSON do utilizador[cite: 13, 19]
    }

    // Endpoint GET /usuarios - Lista todos os utilizadores registados na tabela
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = usuarioService.findAll(); // Busca todos os utilizadores no banco
        return ResponseEntity.ok().body(list); // Retorna HTTP 200 OK com a lista em JSON
    }

    // Endpoint POST /usuarios - Registo de novo utilizador utilizando o DTO validado[cite: 13, 14]
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UsuarioCreateDTO dto) {
        Usuario obj = usuarioService.fromDTO(dto); // Converte o DTO validado na entidade Usuario[cite: 13]
        Usuario novo = usuarioService.create(obj); // Guarda o utilizador na base de dados
        
        // Monta o cabeçalho Location (ex: http://localhost:8080/usuarios/1) com o ID gerado[cite: 13, 14]
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(novo.getId()).toUri();
                
        return ResponseEntity.created(uri).build(); // Retorna HTTP 201 Created sem corpo de resposta[cite: 13, 14]
    }
}