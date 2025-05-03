package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ComponenteResponseDTO;
import br.unitins.topicos1.dto.KitRequestDTO;
import br.unitins.topicos1.dto.KitResponseDTO;
import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.model.Componente;
import br.unitins.topicos1.model.Kit;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.KitRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class KitServiceImpl implements KitService {
    @Inject
    KitRepository repository;

    @Inject
    ProdutoRepository produtoRepository;
    @Inject
    KitRepository kitRepository;

    @Override
    @Transactional
    public KitResponseDTO create(KitRequestDTO kitRequestDTO) {
        Kit kit = new Kit();

        kit.setNome(kitRequestDTO.nome());
        kit.setDescricao(kitRequestDTO.descricao());
        kit.setPreco(kitRequestDTO.preco());

        List<Produto> produtos = new ArrayList<>();

        for (ProdutoRequestDTO produtoDTO : kitRequestDTO.produtos()) {
            Produto produto = new Produto();
            produto.setNome(produtoDTO.nome());
            produto.setDescricao(produtoDTO.descricao());
            produto.setPreco(produtoDTO.preco());
            produto.setCategoria(produtoDTO.categoria());
            produtos.add(produto);
        }

        kit.setProdutos(produtos);

        kitRepository.persist(kit);

        return KitResponseDTO.valueOf(kit);
    }

    @Override
    @Transactional
    public void update(Long id, KitRequestDTO kitRequestDTO) {
        Kit kitBanco = kitRepository.findById(id);

        kitBanco.setNome(kitRequestDTO.nome());
        kitBanco.setDescricao(kitRequestDTO.descricao());
        kitBanco.setPreco(kitRequestDTO.preco());

        List<Produto> produtos = new ArrayList<>();

        for (ProdutoRequestDTO produtoDTO : kitRequestDTO.produtos()) {
            Produto produto = new Produto();
            produto.setNome(produtoDTO.nome());
            produto.setDescricao(produtoDTO.descricao());
            produto.setPreco(produtoDTO.preco());
            produto.setCategoria(produtoDTO.categoria());
            produtos.add(produto);
        }

        kitBanco.setProdutos(produtos);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        kitRepository.deleteById(id);
    }

    @Override
    public KitResponseDTO findById(Long id) {
        return KitResponseDTO.valueOf(kitRepository.findById(id));
    }

    @Override
    public List<KitResponseDTO> findAll() {
        return kitRepository
                .listAll()
                .stream()
                .map(KitResponseDTO::valueOf)
                .toList();
    }

    public List<KitResponseDTO> findAll(int page, int pageSize) {

        List<Kit> listKit = kitRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listKit.stream()
                .map(KitResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<KitResponseDTO> findByNome(String nome) {
        return kitRepository
                .findByNome(nome)
                .stream()
                .map(KitResponseDTO::valueOf)
                .toList();
    }

}