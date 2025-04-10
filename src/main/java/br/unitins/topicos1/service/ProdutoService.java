package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ProdutoRequestDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CategoriaRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ProdutoService {
    @Inject
    ProdutoRepository repository;

    @Inject
    CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO findById(long id) {
        Produto produto = repository.findById(id);
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado");
        }
        return toResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());

        Categoria categoria = categoriaRepository.findById(dto.categoriaId());
        produto.setCategoria(categoria);

        repository.persist(produto);
        return toResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO dto) {
        Produto produto = repository.findById(id);
        if (produto == null) {  // ✅ Corrigido o erro
            throw new NotFoundException("Produto não encontrado");
        }

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setPreco(dto.preco());
        produto.setEstoque(dto.estoque());

        Categoria categoria = categoriaRepository.findById(dto.categoriaId());
        produto.setCategoria(categoria);

        return toResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ProdutoResponseDTO> findByCategoria(String categoriaNome) {
        Categoria categoria = categoriaRepository.findByNome(categoriaNome);
        if (categoria == null)
            throw new NotFoundException("Categoria não encontrada");

        return repository.findByCategoria(categoria)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Metodo correto para converter Produto em ProdutoResponseDTO
    private ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getCategoria().getId()
        );
    }
}
