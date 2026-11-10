package com.senai.sagsa.models;

import jakarta.persistence.*;// Pacote das entidades de modelo
import lombok.*;// Mapeamento JPA/Hibernate
import java.time.LocalDateTime;// Controle de data/hora

@Entity // Declara como tabela gerenciada pelo JPA
@Table(name = "usuarios") // Aponta para a tabela 'usuarios' no banco
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id // Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento do MySQL
    private Long id; // Identificador único do usuário

    @Column(nullable = false, length = 100) // Nome obrigatório, máx 100 chars
    private String nome;

    @Column(nullable = false, unique = true, length = 150) // Email obrigatório e único no sistema
    private String email;

    @Column(name = "senha_hash", nullable = false) // Coluna senha_hash no banco, guarda senha com BCrypt
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER) // Carrega o perfil imediatamente junto com o usuário para a segurança
    @JoinColumn(name = "perfil_id", nullable = false) // Chave estrangeira referenciando a tabela perfis
    private Perfil perfil;

    @Column(nullable = false) // Status ativo/inativo para bloqueio de login
    private Boolean ativo = true;

    @Column(name = "data_criacao", updatable = false) // Carimbo de criação, nunca editável
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Column(name = "data_atualizacao") // Carimbo da última alteração
    private LocalDateTime dataAtualizacao = LocalDateTime.now();
}