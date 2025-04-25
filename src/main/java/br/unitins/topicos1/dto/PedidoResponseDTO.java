package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.ItemPedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PedidoResponseDTO(
        Long id,
        Long usuarioId,
        String usuarioNome,
        LocalDateTime data,
        Double total,
        String status,
        List<ItemPedidoResponseDTO> itens
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(
                pedido.id,
                pedido.getUsuario().getId(),
                pedido.getUsuario().getNome(),
                pedido.getData(),
                pedido.getTotal(),
                pedido.getStatus(),
                pedido.getItens() != null
                        ? pedido.getItens().stream().map(ItemPedidoResponseDTO::new).collect(Collectors.toList())
                        : List.of()
        );
    }

    public record ItemPedidoResponseDTO(
            Long id,
            Long kitId,
            String kitNome,
            Integer quantidade,
            Double precoUnitario
    ) {
        public ItemPedidoResponseDTO(ItemPedido item) {
            this(
                    item.id,
                    item.getKit().id,
                    item.getKit().getNome(),
                    item.getQuantidade(),
                    item.getPrecoUnitario()
            );
        }
    }
}