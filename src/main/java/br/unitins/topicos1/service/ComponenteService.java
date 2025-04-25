package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ComponenteRequestDTO;
import br.unitins.topicos1.dto.ComponenteResponseDTO;
import br.unitins.topicos1.model.Componente;
import br.unitins.topicos1.repository.ComponenteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ComponenteService {

    @Inject
    ComponenteRepository repository;

    public List<ComponenteResponseDTO> getAll() {
        return repository.listAll().stream()
                .map(ComponenteResponseDTO::new)
                .toList();
    }

    public ComponenteResponseDTO findById(Long id) {
        Componente componente = repository.findById(id);
        if (componente == null) {
            throw new NotFoundException("Componente não encontrado.");
        }
        return new ComponenteResponseDTO(componente);
    }

    @Transactional
    public ComponenteResponseDTO create(@Valid ComponenteRequestDTO dto) {
        Componente componente = new Componente();
        componente.setNome(dto.nome());
        componente.setDescricao(dto.descricao());
        componente.setPreco(dto.preco());
        componente.setEstoque(dto.estoque());

        repository.persist(componente);
        return new ComponenteResponseDTO(componente);
    }

    @Transactional
    public ComponenteResponseDTO update(Long id, @Valid ComponenteRequestDTO dto) {
        Componente componente = repository.findById(id);
        if (componente == null) {
            throw new NotFoundException("Componente não encontrado.");
        }

        componente.setNome(dto.nome());
        componente.setDescricao(dto.descricao());
        componente.setPreco(dto.preco());
        componente.setEstoque(dto.estoque());

        return new ComponenteResponseDTO(componente);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Componente não encontrado");
        }
    }

    public List<ComponenteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
                .map(ComponenteResponseDTO::new)
                .toList();
    }
}
