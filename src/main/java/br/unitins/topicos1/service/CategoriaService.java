package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoriaService {

    @Inject
    CategoriaRepository repository;

    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.nome = dto.nome();
        repository.persist(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    public List<CategoriaResponseDTO> getAll() {
        return repository.listAll()
                .stream()
                .map(CategoriaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO findById(Long id) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada!");
        }
        return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada!");
        }
        categoria.nome = dto.nome();
        return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Categoria não encontrada!");
        }
    }

    public List<CategoriaResponseDTO> findByNome(String nome) {
        return repository.find("nome like ?1", "%" + nome + "%")
                .stream()
                .map(CategoriaResponseDTO::new)
                .collect(Collectors.toList());
    }
}