package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.*;
import br.unitins.topicos1.model.*;
import br.unitins.topicos1.repository.PedidoRepository;
import br.unitins.topicos1.repository.KitRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
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
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository repository;

    @Inject
    KitRepository kitRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = new Pedido();

        Kit kit = new Kit();
        kit.setNome(kit.getNome());
        kit.setDescricao(kit.getDescricao());
        kit.setProdutos(kit.getProdutos().stream().toList()); // Provavelmente vai dar erro
        kit.setPreco(kit.getPreco());

        kitRepository.persist(kit);

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoRequestDTO itemPedidoDTO : pedidoRequestDTO.itens()) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setKit(kit);
            itemPedido.setPrecoUnitario(itemPedidoDTO.precoUnitario());
            itemPedido.setQuantidade(itemPedidoDTO.quantidade());
            itens.add(itemPedido);
        }

        pedido.setItens(itens);

        pedido.setStatus(pedidoRequestDTO.status());
        pedido.setData(LocalDateTime.now());
        pedido.setTotal(pedidoRequestDTO.total());
        pedido.setUsuario(pedidoRequestDTO.usuario());
        repository.persist(pedido);

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public void update(Long id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedidoBanco = pedidoRepository.findById(id);

        List<Produto> produtos = new ArrayList<>();

        Kit kit = new Kit();
        kit.setNome(kit.getNome());
        kit.setDescricao(kit.getDescricao());
        kit.setProdutos(kit.getProdutos());
        kit.setPreco(kit.getPreco());

        kitRepository.persist(kit);

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoRequestDTO itemPedidoDTO : pedidoRequestDTO.itens()) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedidoBanco);
            itemPedido.setKit(kit);
            itemPedido.setPrecoUnitario(itemPedidoDTO.precoUnitario());
            itemPedido.setQuantidade(itemPedidoDTO.quantidade());
            itens.add(itemPedido);
        }

        pedidoBanco.setItens(itens);

        pedidoBanco.setStatus(pedidoRequestDTO.status());
        pedidoBanco.setData(LocalDateTime.now());
        pedidoBanco.setTotal(pedidoRequestDTO.total());
        pedidoBanco.setUsuario(pedidoRequestDTO.usuario());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public PedidoResponseDTO updateStatus(Long id, String status) {
        Pedido pedido = repository.findById(id);
        if (pedido == null) {
            throw new NotFoundException("Pedido não encontrado");
        }

        try {
            StatusPedido novoStatus = StatusPedido.valueOf(status);
            pedido.setStatus(novoStatus);
            pedido.setData(LocalDateTime.now()); // Atualiza a data para o momento da mudança de status
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + status);
        }

        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        return PedidoResponseDTO.valueOf(pedidoRepository.findById(id));
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository
                .listAll()
                .stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }

    public List<PedidoResponseDTO> findAll(int page, int pageSize) {

        List<Pedido> listPedido = pedidoRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listPedido.stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }


    @Override
    public List<PedidoResponseDTO> findByUsuario(Long usuarioId) {
        return pedidoRepository
                .findByUsuario(usuarioId)
                .stream()
                .map(PedidoResponseDTO::valueOf)
                .toList();
    }
}