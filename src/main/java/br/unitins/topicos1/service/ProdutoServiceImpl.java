package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.ProdutoRepository;
import br.unitins.topicos1.repository.CategoriaRepository;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        Categoria categoria = new Categoria();
        categoria.setNome(produtoRequestDTO.categoria().getNome());

        categoriaRepository.persist(categoria);

        produto.setCategoria(categoria);
        produto.setDescricao(produtoRequestDTO.descricao());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setNome(produtoRequestDTO.nome());
        produto.setPreco(produtoRequestDTO.preco());

        produtoRepository.persist(produto);

        return ProdutoResponseDTO.valueOf(produto);
    }

    @Override
    @Transactional
    public void update(Long id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoBanco = produtoRepository.findById(id);
        Categoria categoriaBanco = categoriaRepository.findById(id);

        categoriaBanco.setNome(produtoRequestDTO.categoria().getNome());

        produtoBanco.setCategoria(categoriaBanco);
        produtoBanco.setDescricao(produtoRequestDTO.descricao());
        produtoBanco.setPreco(produtoRequestDTO.preco());
        produtoBanco.setNome(produtoRequestDTO.nome());
        produtoBanco.setPreco(produtoRequestDTO.preco());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        return ProdutoResponseDTO.valueOf(produtoRepository.findById(id));
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository
                .listAll()
                .stream()
                .map(ProdutoResponseDTO::valueOf)
                .toList();
    }

    public List<ProdutoResponseDTO> findAll(int page, int pageSize) {

        List<Produto> listProduto = produtoRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listProduto.stream()
                .map(ProdutoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ProdutoResponseDTO> findByCategoria(Long categoria) {
        return produtoRepository
                .findByCategoria(categoria)
                .stream()
                .map(ProdutoResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        return produtoRepository
                .findByNome(nome)
                .stream()
                .map(ProdutoResponseDTO::valueOf)
                .toList();
    }
}