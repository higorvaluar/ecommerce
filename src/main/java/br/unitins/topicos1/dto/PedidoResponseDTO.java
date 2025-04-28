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
        List<ItemPedidoDTO> itens
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(
                pedido.id,
                pedido.getUsuario().getId(),
                pedido.getUsuario().getNome(),
                pedido.getData(),
                pedido.getTotal(),
                pedido.getStatus().toString(),
                pedido.getItens().stream()
                        .map(item -> new ItemPedidoDTO(
                                item.getKit().id,
                                item.getKit().getNome(),
                                item.getQuantidade(),
                                item.getPrecoUnitario()))
                        .collect(Collectors.toList())
        );
    }

    public record ItemPedidoDTO(
            Long kitId,
            String kitNome,
            Integer quantidade,
            Double precoUnitario
    ) {}
}