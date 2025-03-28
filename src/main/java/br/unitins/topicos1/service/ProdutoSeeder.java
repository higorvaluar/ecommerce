package br.unitins.topicos1.service;

import br.unitins.topicos1.model.Produto;
import br.unitins.topicos1.model.CategoriaProduto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoSeeder {

    @Transactional
    public void seed() {
        if (Produto.count() == 0) {  // Verifica se já há produtos na base de dados
            // Criando os produtos
            Produto produto1 = new Produto("Roteador VPN", "Roteador especializado em VPN para segurança online.", 321.22, 20, CategoriaProduto.VPN);
            Produto produto2 = new Produto("DNS Caseiro", "DNS Caseiro que você mesmo pode fazer", 111.21, 15, CategoriaProduto.PI_HOLE);
            Produto produto3 = new Produto("Servidor de Arquivos Privado", "Servidor dedicado para armazenamento de arquivos privados.", 1223.11, 10, CategoriaProduto.NEXTCLOUD);
            Produto produto4 = new Produto("Firewall caseiro", "Você configura e tem privacidade!", 442.11, 30, CategoriaProduto.FIREWALL);

            // Persistindo os produtos
            produto1.persist();
            produto2.persist();
            produto3.persist();
            produto4.persist();
        }
    }
}
