package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.repository.CategoriaRepository;
import br.unitins.topicos1.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoSeeder {

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Transactional
    public void seed() {
        if (categoriaRepository.count() == 0) {
            Categoria vpn = new Categoria();
            vpn.nome = "VPN";
            Categoria firewall = new Categoria();
            firewall.nome = "Firewall";
            Categoria servidores = new Categoria();
            servidores.nome = "Servidores";
            Categoria armazenamento = new Categoria();
            armazenamento.nome = "Armazenamento";

            categoriaRepository.persist(vpn, firewall, servidores, armazenamento);

            Produto roteador = new Produto();
            roteador.nome = "Roteador VPN";
            roteador.descricao = "Roteador especializado";
            roteador.preco = 299.90;
            roteador.estoque = 10;
            roteador.categoria = vpn;

            Produto firewallCaseiro = new Produto();
            firewallCaseiro.nome = "Firewall Caseiro";
            firewallCaseiro.descricao = "Firewall personalizado";
            firewallCaseiro.preco = 199.90;
            firewallCaseiro.estoque = 5;
            firewallCaseiro.categoria = firewall;

            produtoRepository.persist(roteador, firewallCaseiro);
        }
    }
}