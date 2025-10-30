CREATE DATABASE IF NOT EXISTS MimaStore;

USE MimaStore;

 -- VENDA / Funcionario / CLIENTE
-- Tabela Usuario
CREATE TABLE if not exists Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cargo VARCHAR(50) NOT NULL
);

-- Tabela Cliente (presume-se que Cliente seja semelhante a Usuario, mas você pode ajustar)
CREATE TABLE if not exists Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

-- Tabela Fornecedor
CREATE TABLE if not exists Fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255)
);

CREATE TABLE if not exists Venda (
    id INT PRIMARY KEY AUTO_INCREMENT,
    valor_total DOUBLE DEFAULT 0.0,
    data DATETIME DEFAULT current_timestamp,
    fkCliente INT,
    fkFuncionario INT,
    CONSTRAINT fk_venda_cliente FOREIGN KEY (fkCliente) REFERENCES Cliente(id_cliente),
    CONSTRAINT fk_venda_funcionario FOREIGN KEY (fkFuncionario) REFERENCES Usuario(id)
);

CREATE TABLE if not exists Tamanho (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255)
);

CREATE TABLE if not exists Cor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255)
);

CREATE TABLE if not exists Material (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255)
);

CREATE TABLE if not exists Categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255)
);

CREATE TABLE if not exists Item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    codigo VARCHAR(255),
    qtd_estoque INT,
    nome VARCHAR(255),
    preco DOUBLE,
    fkTamanho INT,
    fkCor INT,
    fkMaterial INT,
    fkCategoria INT,
    fkFornecedor INT,
    FOREIGN KEY (fkTamanho) REFERENCES Tamanho(id),
    FOREIGN KEY (fkCor) REFERENCES Cor(id),
    FOREIGN KEY (fkMaterial) REFERENCES Material(id),
    FOREIGN KEY (fkCategoria) REFERENCES Categoria(id),
    FOREIGN KEY (fkFornecedor) REFERENCES Fornecedor(id)
);

-- Tabela ItemVenda
CREATE TABLE if not exists ItemVenda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fkItem INT,
    fkItemFornecedor INT,
    fkVenda INT,
    fkVendaCliente INT,
    fkVendaFuncionario INT,
    qtdParaVender INT,
    FOREIGN KEY (fkItem) REFERENCES Item(id),
    FOREIGN KEY (fkItemFornecedor) REFERENCES Fornecedor(id),
    FOREIGN KEY (fkVenda) REFERENCES Venda(id),
    FOREIGN KEY (fkVendaCliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (fkVendaFuncionario) REFERENCES Usuario(id)
);

insert into Usuario
(nome, email, senha, telefone, endereco,cargo)
values
    ('John Doe', 'john@doe.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1111-11111', 'Rua do bacana','Funcionario');
-- senha: 123456

INSERT INTO Fornecedor (nome, telefone, email)
VALUES ('Empresa XYZ LTDA', '11987654321', 'contato@empresa.com');

insert into Tamanho (nome) values
    ('M');

insert into Categoria (nome) values
    ('Camiseta');

insert into Cor (nome) values
    ('Preto');

insert into Material (nome) values
    ( 'Lã');

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
