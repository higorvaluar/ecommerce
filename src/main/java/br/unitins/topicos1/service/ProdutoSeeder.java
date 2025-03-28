package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Categoria;
import br.unitins.topicos1.model.Produto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoSeeder {

    @Inject
    EntityManager em;

    @Transactional
    public void seed() {
        if (em.createQuery("SELECT COUNT(p) FROM Produto p", Long.class).getSingleResult() == 0) {
            // Criando as categorias
            Categoria categoriaVpn = new Categoria("VPN");
            Categoria categoriaPihole = new Categoria("PI_HOLE");
            Categoria categoriaNextcloud = new Categoria("NEXTCLOUD");
            Categoria categoriaFirewall = new Categoria("FIREWALL");

            // Persistindo categorias com o EntityManager
            em.persist(categoriaVpn);
            em.persist(categoriaPihole);
            em.persist(categoriaNextcloud);
            em.persist(categoriaFirewall);

            // Criando os produtos
            Produto produto1 = new Produto("Roteador VPN", "Roteador especializado em VPN para segurança online.", 321.22, 20, categoriaVpn);
            Produto produto2 = new Produto("DNS Caseiro", "DNS Caseiro que você mesmo pode fazer", 111.21, 15, categoriaPihole);
            Produto produto3 = new Produto("Servidor de Arquivos Privado", "Servidor dedicado para armazenamento de arquivos privados.", 1223.11, 10, categoriaNextcloud);
            Produto produto4 = new Produto("Firewall caseiro", "Você configura e tem privacidade!", 442.11, 30, categoriaFirewall);

            // Persistindo os produtos
            em.persist(produto1);
            em.persist(produto2);
            em.persist(produto3);
            em.persist(produto4);
        }
    }
}
