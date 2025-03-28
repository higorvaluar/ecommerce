package br.unitins.topicos1.repository;

import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    public List<Produto> findByCategoria(Categoria categoria) {
        return list("categoria", categoria);
    }
}
