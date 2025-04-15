package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

public record TutorialRequestDTO(
        String titulo,
        String conteudo,
        @NotNull(message = "O ID do produto é obrigatório")
        Long produtoId  // Garanta que o nome do campo bate com o JSON
) {}