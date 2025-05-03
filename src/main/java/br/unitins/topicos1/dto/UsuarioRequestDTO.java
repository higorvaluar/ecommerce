package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(
        @NotBlank(message = "O email não pode ser vazio")
        String email,

        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O perfil não pode ser vazio")
        String perfil,

        @NotBlank(message = "A senha não pode ser vazia")
        String senha
) {}