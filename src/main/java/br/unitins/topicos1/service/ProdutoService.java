package br.unitins.topicos1.service;

import br.unitins.topicos1.model.CategoriaProduto;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoService {
    @Inject
    ProdutoRepository repository;

    public Produto findById(long id) {
        return repository.findById(id);
    }

    public List<Produto> findAll() {
        return repository.listAll();
    }

    @Transactional
    public Produto create(Produto produto) {
        repository.persist(produto);
        return produto;
    }

    @Transactional
    public Produto update(Long id, Produto produtoAtualizado) {
        Produto produto = repository.findById(id);
        if (produto != null) {
            produto.nome = produtoAtualizado.nome;
            produto.descricao = produtoAtualizado.descricao;
            produto.preco = produtoAtualizado.preco;
            produto.estoque = produtoAtualizado.estoque;
            produto.categoria = produtoAtualizado.categoria;
        }
        return produto;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Produto> findByCategoria(CategoriaProduto categoria) {
        return repository.list("categoria", categoria);
    }
}