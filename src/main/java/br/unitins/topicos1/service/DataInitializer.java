//package br.unitins.topicos1.service;
//
//import br.unitins.topicos1.dto.*;
//import io.quarkus.runtime.StartupEvent;
//import jakarta.enterprise.context.ApplicationScoped;
//import jakarta.enterprise.event.Observes;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import java.util.List;
//
//@ApplicationScoped
//public class DataInitializer {
//    @Inject
//    CategoriaServiceImpl categoriaService;
//
//    @Inject
//    ProdutoServiceImpl produtoService;
//
//    @Inject
//    ComponenteServiceImpl componenteService;
//
//    @Inject
//    KitServiceImpl kitService;
//
//    @Transactional
//    public void init(@Observes StartupEvent ev) {
//        // Criar categorias
//        CategoriaResponseDTO cat1 = categoriaService.create(new CategoriaRequestDTO("VPN")); // Linha 28
//        CategoriaResponseDTO cat2 = categoriaService.create(new CategoriaRequestDTO("Firewall"));
//
//        // Criar produtos
//        ProdutoResponseDTO prod1 = produtoService.create(new ProdutoRequestDTO("Roteador VPN", "Roteador especializado", 299.90, 10, cat1.getId()));
//        ProdutoResponseDTO prod2 = produtoService.create(new ProdutoRequestDTO("Firewall Caseiro", "Firewall personalizado", 199.90, 5, cat2.getId()));
//
//        // Criar componentes
//        componenteService.create(new ComponenteRequestDTO("Raspberry Pi 4", "Placa para servidores DIY", 250.00, 50));
//        componenteService.create(new ComponenteRequestDTO("HD Externo 1TB", "Armazenamento para Nextcloud", 400.00, 30));
//
//        // Criar kit
//        kitService.create(new KitRequestDTO("Kit Servidor DIY", "Kit para montar servidor caseiro", 600.00, List.of(prod1.getId(), prod2.getId())));
//    }
//}