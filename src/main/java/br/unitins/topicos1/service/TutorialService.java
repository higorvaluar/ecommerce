package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.TutorialRequestDTO;
import br.unitins.topicos1.dto.TutorialResponseDTO;
import br.unitins.topicos1.model.Tutorial;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.TutorialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class TutorialService {

    @Inject
    TutorialRepository repository;

    public List<TutorialResponseDTO> getAll() {
        return repository.listAll()
                .stream()
                .map(TutorialResponseDTO::new)
                .toList();
    }

    public TutorialResponseDTO findById(Long id) {
        Tutorial tutorial = repository.findById(id);
        if (tutorial == null) {
            throw new NotFoundException("Tutorial não encontrado");
        }
        return new TutorialResponseDTO(tutorial);
    }

    @Transactional
    public TutorialResponseDTO create(TutorialRequestDTO dto) {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitulo(dto.titulo());
        tutorial.setConteudo(dto.conteudo());

        Produto produto = Produto.findById(dto.produtoId());
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado");
        }
        tutorial.setProduto(produto);

        repository.persist(tutorial);
        return new TutorialResponseDTO(tutorial);
    }

    @Transactional
    public TutorialResponseDTO update(Long id, TutorialRequestDTO dto) {
        Tutorial tutorial = repository.findById(id);
        if (tutorial == null) {
            throw new NotFoundException("Tutorial não encontrado");
        }

        tutorial.setTitulo(dto.titulo());
        tutorial.setConteudo(dto.conteudo());

        Produto produto = Produto.findById(dto.produtoId());
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado");
        }
        tutorial.setProduto(produto);

        return new TutorialResponseDTO(tutorial);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Tutorial não encontrado");
        }
    }

    public List<TutorialResponseDTO> findByProduto(Long produtoId) {
        return repository.find("produto.id", produtoId)
                .stream()
                .map(TutorialResponseDTO::new)
                .toList();
    }
}