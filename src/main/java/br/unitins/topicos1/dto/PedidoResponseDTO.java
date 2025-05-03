package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record PedidoResponseDTO(
        Long id,
        Long usuarioId,
        Usuario usuario,
        LocalDateTime data,
        Double total,
        String status,
        List<ItemPedidoResponseDTO> itens
) {

    public static PedidoResponseDTO valueOf(Pedido pedido) {

        List<ItemPedidoResponseDTO> listaItemPedido = pedido.getItens()
                .stream()
                .map(ItemPedidoResponseDTO::valueOf)
                .toList();

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getUsuario().getId(),
                pedido.getUsuario(),
                pedido.getData(),
                pedido.getTotal(),
                pedido.getStatus().toString(),
                listaItemPedido
        );

    }


}