package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.dto.ComponenteRequestDTO;
import br.unitins.topicos1.dto.ComponenteResponseDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Componente;
import br.unitins.topicos1.repository.ComponenteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ComponenteServiceImpl implements ComponenteService {

    @Inject
    ComponenteRepository componenteRepository;

    @Override
    @Transactional
    public ComponenteResponseDTO create(ComponenteRequestDTO componenteDTO) {
        Componente componente = new Componente();
        componente.setNome(componenteDTO.nome());
        componente.setDescricao(componenteDTO.descricao());
        componente.setEstoque(componenteDTO.estoque());
        componente.setPreco(componenteDTO.preco());

        componenteRepository.persist(componente);

        return ComponenteResponseDTO.valueOf(componente);
    }

    @Override
    @Transactional
    public void update(Long id, ComponenteRequestDTO componenteDTO) {

        Componente componenteBanco = componenteRepository.findById(id);
        componenteBanco.setNome(componenteDTO.nome());
        componenteBanco.setDescricao(componenteDTO.descricao());
        componenteBanco.setPreco(componenteDTO.preco());
        componenteBanco.setEstoque(componenteDTO.estoque());

    }

    @Override
    @Transactional
    public void delete(Long id) {
        componenteRepository.deleteById(id);
    }

    @Override
    public ComponenteResponseDTO findById(Long id) {
        return ComponenteResponseDTO.valueOf(componenteRepository.findById(id));
    }

    @Override
    public List<ComponenteResponseDTO> findAll() {
        return componenteRepository
                .listAll()
                .stream()
                .map(ComponenteResponseDTO::valueOf)
                .toList();
    }

    public List<ComponenteResponseDTO> findAll(int page, int pageSize) {

        List<Componente> listComponente = componenteRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listComponente.stream()
                .map(ComponenteResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<ComponenteResponseDTO> findByNome(String nome) {
        return componenteRepository
                .findByNome(nome)
                .stream()
                .map(ComponenteResponseDTO::valueOf).toList();
    }
}