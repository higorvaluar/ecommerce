package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.dto.TutorialRequestDTO;
import br.unitins.topicos1.dto.TutorialResponseDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Tutorial;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.CategoriaRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import br.unitins.topicos1.repository.TutorialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class TutorialServiceImpl implements TutorialService {

    @Inject
    TutorialRepository repository;

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    TutorialRepository tutorialRepository;

    @Override
    @Transactional
    public TutorialResponseDTO create(TutorialRequestDTO tutorialDTO) {
        Tutorial tutorial = new Tutorial();

        Produto produto = new Produto();
        Categoria categoria = new Categoria();

        produto.setNome(tutorialDTO.produto().nome());
        produto.setDescricao(tutorialDTO.produto().descricao());
        produto.setPreco(tutorialDTO.produto().preco());
        produto.setEstoque(tutorialDTO.produto().estoque());
        categoria.setNome(tutorialDTO.produto().categoria().getNome());
        produtoRepository.persist(produto);
        categoriaRepository.persist(categoria);

        tutorial.setTitulo(tutorialDTO.titulo());
        tutorial.setProduto(produto);
        tutorial.setConteudo(tutorialDTO.conteudo());

        tutorialRepository.persist(tutorial);

        return TutorialResponseDTO.valueOf(tutorial);
    }

    @Override
    @Transactional
    public void update(Long id, TutorialRequestDTO tutorialDTO) {
        Tutorial tutorialBanco = tutorialRepository.findById(id);

        Produto produto = produtoRepository.findById(tutorialBanco.getProduto().getId());
        Categoria categoria = categoriaRepository.findById(id);

        produto.setNome(tutorialDTO.produto().nome());
        produto.setDescricao(tutorialDTO.produto().descricao());
        produto.setPreco(tutorialDTO.produto().preco());
        produto.setEstoque(tutorialDTO.produto().estoque());
        categoria.setNome(tutorialDTO.produto().categoria().getNome());

        tutorialBanco.setTitulo(tutorialDTO.titulo());
        tutorialBanco.setProduto(produto);
        tutorialBanco.setConteudo(tutorialDTO.conteudo());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public TutorialResponseDTO findById(Long id) {
        return TutorialResponseDTO.valueOf(tutorialRepository.findById(id));
    }

    @Override
    public List<TutorialResponseDTO> findAll() {
        return tutorialRepository
                .listAll()
                .stream()
                .map(TutorialResponseDTO::valueOf)
                .toList();
    }

    public List<TutorialResponseDTO> findAll(int page, int pageSize) {

        List<Tutorial> listTutorial = tutorialRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listTutorial.stream()
                .map(TutorialResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<TutorialResponseDTO> findByProduto(Long IdProduto) {
        return tutorialRepository
                .findByProduto(IdProduto)
                .stream()
                .map(TutorialResponseDTO::valueOf)
                .toList();
    }
}