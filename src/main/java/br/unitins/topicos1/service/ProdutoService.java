package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.ProdutoRepository;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository repository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId());
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada!");
        }

        Produto produto = new Produto();
        produto.nome = dto.nome();
        produto.descricao = dto.descricao();
        produto.preco = dto.preco();
        produto.estoque = dto.estoque();
        produto.categoria = categoria;

        repository.persist(produto);
        return new ProdutoResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> getAll() {
        return repository.listAll()
                .stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDTO findById(Long id) {
        Produto produto = repository.findById(id);
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado!");
        }
        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id);
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado!");
        }

        Categoria categoria = categoriaRepository.findById(dto.categoriaId());
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada!");
        }

        produto.nome = dto.nome();
        produto.descricao = dto.descricao();
        produto.preco = dto.preco();
        produto.estoque = dto.estoque();
        produto.categoria = categoria;

        return new ProdutoResponseDTO(produto);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Produto não encontrado!");
        }
    }

    public List<ProdutoResponseDTO> findByNome(String nome) {
        return repository.find("nome like ?1", "%" + nome + "%")
                .stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<ProdutoResponseDTO> findByCategoria(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada!");
        }
        return repository.find("categoria.id = ?1", categoria.id)
                .stream()
                .map(ProdutoResponseDTO::new)
                .collect(Collectors.toList());
    }
}