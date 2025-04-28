package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        String nome,

        @NotBlank(message = "O email não pode ser vazio")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        String senha,

        String perfil
        ) {}
