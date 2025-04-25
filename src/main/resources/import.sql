-- Inserção para Usuario
INSERT INTO usuario (email, nome, perfil, senha) VALUES ('admin@email.com', 'Higor', 'ADMIN', '$2a$10$X5wFBtLrL/kHcmrOGGTrGufsBX8Cg0nQpJXrsl6mI7Is1VdD2qY1K');

-- Inserções para Categoria
INSERT INTO Categoria (nome) VALUES ('VPN');
INSERT INTO Categoria (nome) VALUES ('Firewall');

-- Inserções para Produto
INSERT INTO Produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Roteador VPN', 'Roteador especializado', 299.90, 10, 1);
INSERT INTO Produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Firewall Caseiro', 'Firewall personalizado', 199.90, 5, 2);

-- Inserções para Componente
INSERT INTO Componente (nome, descricao, preco, estoque) VALUES ('Raspberry Pi 4', 'Placa para servidores DIY', 250.00, 50);
INSERT INTO Componente (nome, descricao, preco, estoque) VALUES ('HD Externo 1TB', 'Armazenamento para Nextcloud', 400.00, 30);

-- Inserções para Kit
INSERT INTO Kit (nome, descricao, preco) VALUES ('Kit Servidor DIY', 'Kit para montar servidor caseiro', 600.00);

-- Inserções para o relacionamento Kit-Produto
INSERT INTO kit_produto (kit_id, produto_id) VALUES (1, 1);
INSERT INTO kit_produto (kit_id, produto_id) VALUES (1, 2);