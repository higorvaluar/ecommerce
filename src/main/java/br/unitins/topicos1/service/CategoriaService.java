package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CategoriaService {

    @Inject
    CategoriaRepository repository;

    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        repository.persist(categoria);
        return new CategoriaResponseDTO(categoria);
    }

    public List<CategoriaResponseDTO> getAll() {
        return repository.listAll().stream()
                .map(CategoriaResponseDTO::new)
                .toList();
    }

    public CategoriaResponseDTO findById(Long id) {
        return new CategoriaResponseDTO(repository.findById(id));
    }

    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = repository.findById(id);
        categoria.setNome(dto.nome());
        return new CategoriaResponseDTO(categoria);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}