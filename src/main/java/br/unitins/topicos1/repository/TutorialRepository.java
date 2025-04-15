package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Tutorial;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class TutorialRepository implements PanacheRepository<Tutorial> {
    public List<Tutorial> findByProduto(Long produtoId) {
        return list("produto.id", produtoId);
    }
}
