package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.CategoriaRequestDTO;
import br.unitins.topicos1.dto.CategoriaResponseDTO;
import br.unitins.topicos1.dto.ProdutoResponseDTO;
import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PathParam;

import java.util.List;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {
    @Inject
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public CategoriaResponseDTO create(CategoriaRequestDTO categoriaDTO) {

        try {
            Categoria categoria = new Categoria();

            categoria.setNome(categoriaDTO.nome());

            categoriaRepository.persist(categoria);

            return CategoriaResponseDTO.valueOf(categoria);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar categoria");
        }

    }

    @Override
    @Transactional
    public void update(Long id, CategoriaRequestDTO categoriaDTO) {
        try {
            Categoria categoriaBanco = categoriaRepository.findById(id);
            categoriaBanco.setNome(categoriaDTO.nome());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar categoria");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Categoria categoria = categoriaRepository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada com id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaResponseDTO findById(Long id) {
        return CategoriaResponseDTO.valueOf(categoriaRepository.findById(id));
    }

    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository
                .listAll()
                .stream()
                .map(CategoriaResponseDTO::valueOf)
                .toList();
    }

    public List<CategoriaResponseDTO> findAll(int page, int pageSize) {

        List<Categoria> listCategoria = categoriaRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return listCategoria.stream()
                .map(CategoriaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<CategoriaResponseDTO> findByNome(String nome) {
        return categoriaRepository
                .findByNome(nome)
                .stream()
                .map(CategoriaResponseDTO::valueOf)
                .toList();
    }
}