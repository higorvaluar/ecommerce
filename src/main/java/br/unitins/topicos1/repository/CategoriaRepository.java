package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {

    public List<Categoria> findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}