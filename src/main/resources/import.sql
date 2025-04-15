INSERT INTO usuario (id, email, nome, perfil, senha)
VALUES (1, 'admin@email.com', 'Higor', 'ADMIN', '$2a$10$X5wFBtLrL/kHcmrOGGTrGufsBX8Cg0nQpJXrsl6mI7Is1VdD2qY1K');

-- Categorias
INSERT INTO Categoria (nome) VALUES ('VPN');
INSERT INTO Categoria (nome) VALUES ('Firewall');

-- Produtos
INSERT INTO Produto (id, nome, descricao, preco, estoque, categoria_id)
VALUES (DEFAULT, 'Roteador VPN', 'Roteador especializado', 299.90, 10, 1);

INSERT INTO Produto (id, nome, descricao, preco, estoque, categoria_id)
VALUES (DEFAULT, 'Firewall Caseiro', 'Firewall personalizado', 199.90, 5, 2);