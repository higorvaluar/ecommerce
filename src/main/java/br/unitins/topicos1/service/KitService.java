package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.KitRequestDTO;
import br.unitins.topicos1.dto.KitResponseDTO;
import br.unitins.topicos1.model.Kit;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.KitRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class KitService {
    @Inject
    KitRepository repository;

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public KitResponseDTO create(KitRequestDTO dto) {
        Kit kit = new Kit();
        kit.setNome(dto.nome());
        kit.setDescricao(dto.descricao());

        List<Produto> produtos = dto.produtosIds()
                .stream().map(id -> {
                    Produto produto = produtoRepository.findById(id);
                    if (produto == null) {
                        throw new NotFoundException("Produto com ID " + id + " não encontrado");
                    }
                    return produto;
                }).collect(Collectors.toList());
        if (produtos.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado com os IDs fornecidos.");
        }
        kit.setProdutos(produtos);

        // Calcula o preço do kit com base nos produtos
        double precoCalculado = produtos.stream()
                        .mapToDouble(produto -> produto.preco)
                                .sum();
        kit.setPreco(precoCalculado);

        repository.persist(kit);
        return new KitResponseDTO(kit);
    }

    public List<KitResponseDTO> getAll() {
        return repository.listAll().stream()
                .map(KitResponseDTO::new)
                .toList();
    }

    public KitResponseDTO findById(Long id) {
        Kit kit = repository.findById(id);
        if (kit == null) {
            throw new NotFoundException("Kit não encontrado!");
        }
        return new KitResponseDTO(kit);
    }

    @Transactional
    public KitResponseDTO update(Long id, KitRequestDTO dto) {
        Kit kit = repository.findById(id);
        if (kit == null) {
            throw new NotFoundException("Kit não encontrado!");
        }

        kit.setNome(dto.nome());
        kit.setDescricao(dto.descricao());

        List<Produto> produtos = dto.produtosIds().stream()
                        .map(prodId -> {
                            Produto produto = produtoRepository.findById(prodId);
                            if (produto == null) {
                                throw new NotFoundException("Produto com ID " + prodId + " não encontrado");
                            }
                            return produto;
                        }).collect(Collectors.toList());

        kit.setProdutos(produtos);

        // Recalcula o preço do kit
        double precoCalculado = produtos.stream()
                .mapToDouble(produto -> produto.preco).sum();
        kit.setPreco(precoCalculado);

        return new KitResponseDTO(kit);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Kit não encontrado!");
        }
    }

    public List<KitResponseDTO> findByNome(String nome) {
        return repository.find("nome like ?1", "%" + nome + "%")
                .stream()
                .map(KitResponseDTO::new)
                .toList();
    }
}