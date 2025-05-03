CREATE SEQUENCE IF NOT EXISTS categoria_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS produto_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS kit_seq START 1 INCREMENT 1;

INSERT INTO usuario (email, nome, perfil, senha) VALUES
('admin@example.com', 'admin', 'ADMIN', '$2a$12$f1l.AujAGciqQm4Ic2bqDuG9T93/2BmvRjNBZyfaFjGzns2vGvEOK');

-- Inserindo uma categoria
INSERT INTO Categoria (nome) VALUES ('Eletrônicos');

-- Inserindo um produto
INSERT INTO Produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Arduino Uno', 'Placa microcontroladora', 29.99, 100, 1);

-- Inserindo um kit
INSERT INTO Kit (nome, descricao, preco) VALUES ('Kit Iniciante', 'Kit para iniciantes em eletrônica', 59.99);

-- Associando produto ao kit
INSERT INTO kit_produto (kit_id, produto_id) VALUES (1, 1);

-- Inserindo um componente
INSERT INTO Componente (nome, descricao, preco, estoque) VALUES ('Resistor 10k', 'Resistor de 10k ohms', 0.10, 1000);

-- Inserindo um tutorial
INSERT INTO Tutorial (titulo, conteudo, produto_id) VALUES ('Como usar Arduino', 'Tutorial básico de Arduino...', 1);

-- Inserindo um pedido
INSERT INTO Pedido (usuario_id, data, total, status) VALUES (1, '2025-05-03 10:00:00', 59.99, 'PENDENTE');

-- Inserindo um item de pedido
INSERT INTO ItemPedido (pedido_id, kit_id, quantidade, precoUnitario) VALUES (1, 1, 1, 59.99);