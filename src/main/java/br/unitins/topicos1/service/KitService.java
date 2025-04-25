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
        kit.setPreco(dto.preco());

        List<Produto> produtos = produtoRepository.list("id in ?1", dto.produtosIds());
        if (produtos.isEmpty()) {
            throw new NotFoundException("Nenhum produto encontrado com os IDs fornecidos.");
        }
        kit.setProdutos(produtos);

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
        kit.setPreco(dto.preco());

        List<Produto> produtos = produtoRepository.list("id in ?1", dto.produtosIds());
        kit.setProdutos(produtos);

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