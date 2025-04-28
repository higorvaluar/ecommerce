package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome
) {}
