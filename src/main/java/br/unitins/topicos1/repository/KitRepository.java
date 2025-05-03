package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Kit;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class KitRepository implements PanacheRepository<Kit> {
    public List<Kit> findByNome(String nome) {
        return find("nome", nome).firstResult();
    }
}
