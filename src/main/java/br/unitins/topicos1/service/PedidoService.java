package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.PedidoRequestDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Kit;
import br.unitins.topicos1.model.Usuario;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.KitRepository;
import br.unitins.topicos1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PedidoService {

    @Inject
    PedidoRepository repository;

    @Inject
    KitRepository kitRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    public List<PedidoResponseDTO> getAll() {
        return repository.listAll()
                .stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }

    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = repository.findById(id);
        if (pedido == null) {
            throw new NotFoundException("Pedido não encontrado");
        }
        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO create(@Valid PedidoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId());
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        List<ItemPedido> itens = new ArrayList<>();
        Double total = 0.0;

        for (PedidoRequestDTO.ItemPedidoDTO itemDTO : dto.itens()) {
            Kit kit = kitRepository.findById(itemDTO.kitId());
            if (kit == null) {
                throw new NotFoundException("Kit não encontrado");
            }
            if (kit.getProdutos().isEmpty()) {
                throw new IllegalArgumentException("Kit sem produtos não pode ser incluído no pedido");
            }
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setKit(kit);
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoUnitario(kit.getPreco());
            itens.add(item);
            total += itemDTO.quantidade() * kit.getPreco();
        }

        pedido.setItens(itens);
        pedido.setTotal(total);

        repository.persist(pedido);
        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO updateStatus(Long id, String status) {
        Pedido pedido = repository.findById(id);
        if (pedido == null) {
            throw new NotFoundException("Pedido não encontrado");
        }

        if (!List.of("PENDENTE", "PAGO", "ENVIADO", "CANCELADO").contains(status)) {
            throw new IllegalArgumentException("Status inválido");
        }

        pedido.setStatus(status);
        return new PedidoResponseDTO(pedido);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Pedido não encontrado");
        }
    }

    public List<PedidoResponseDTO> findByUsuario(Long usuarioId) {
        return repository.findByUsuario(usuarioId)
                .stream()
                .map(PedidoResponseDTO::new)
                .toList();
    }
}