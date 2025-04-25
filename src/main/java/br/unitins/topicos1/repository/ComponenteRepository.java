package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Componente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ComponenteRepository implements PanacheRepository<Componente> {
    public List<Componente> findByNome(String nome) {
        return find("nome like ?1", "%" + nome + "%").list();
    }
}
