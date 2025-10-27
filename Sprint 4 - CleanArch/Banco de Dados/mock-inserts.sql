INSERT INTO Usuario (nome, telefone, email, senha, cargo) VALUES
('Ana Souza', '11999990000', 'ana.gerente@mimastore.com', 'senha123', 'Gerente'),
('Carlos Lima', '11988887777', 'carlos.funcionario@mimastore.com', 'senha123', 'Funcionario');


INSERT INTO Cliente (nome, telefone, CPF, email) VALUES
('João Pereira', '11977776666', '12345678901', 'joao@gmail.com'),
('Maria Silva', '11966665555', '98765432100', 'maria@gmail.com');


INSERT INTO Fornecedor (nome, telefone, email) VALUES
('Fornecedora A', '1133221100', 'contato@fornecedoraa.com'),
('Fornecedora B', '1144332211', 'suporte@fornecedorab.com');


INSERT INTO Cor (nome) VALUES
('Vermelho'),
('Azul'),
('Preto');


INSERT INTO Categoria (tipo) VALUES
('Camiseta'),
('Calça'),
('Acessório');


INSERT INTO Material (material) VALUES
('Algodão'),
('Jeans'),
('Couro');


INSERT INTO Tamanho (tamanho) VALUES
('P'),
('M'),
('G'),
('GG');


INSERT INTO Item (fkFornecedor, quantidade, preco, codigo, fkCor, fkCategoria, fkMaterial, fkTamanho) VALUES
(1, 50, 39.90, 'CAMV021M', 1, 1, 1, 2), -- Camiseta Vermelha M
(2, 30, 79.90, 'CAL567G', 2, 2, 2, 3), -- Calça Jeans G
(1, 20, 119.90, 'ACS678P', 3, 3, 3, 1); -- Acessório Preto P


INSERT INTO Venda (preco_total, fkCliente, fkUsuario) VALUES
(159.80, 1, 1),
(119.90, 2, 2);


INSERT INTO ItensVenda (fkItem, fkItemFornecedor, fkVenda, fkVendaCliente, fKVendaFuncionario, quantidade) VALUES
(10, 1, 1, 1, 1, 2),
(11, 2, 1, 1, 1, 1),
(12, 1, 2, 2, 2, 1);


