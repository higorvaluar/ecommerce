package br.unitins.topicos1;

import br.unitins.topicos1.service.ProdutoSeeder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.annotation.PostConstruct;

@ApplicationScoped
public class ProdutoInitializer {
    @Inject
    ProdutoSeeder produtoSeeder;

    @PostConstruct
    public void init() {
        produtoSeeder.seed(); // Inicializa os produtos no banco
    }
}
