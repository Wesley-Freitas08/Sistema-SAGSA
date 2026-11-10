package com.senai.sagsa.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 5, max = 150)
    private String email;

    @NotBlank
    @Size(min = 6, max = 60)
    private String senha;

    @NotNull
    private Long perfilId;
}