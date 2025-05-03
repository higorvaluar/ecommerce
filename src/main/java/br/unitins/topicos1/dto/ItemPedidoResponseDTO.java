package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ItemPedido;

public record ItemPedidoResponseDTO(
        Long kitId,
        String kitNome,
        Integer quantidade,
        Double precoUnitario
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido itemPedido){
        return new ItemPedidoResponseDTO(
                itemPedido.getKit().getId(),
                itemPedido.getKit().getNome(),
                itemPedido.getQuantidade(),
                itemPedido.getPrecoUnitario()
        );
    }
}
