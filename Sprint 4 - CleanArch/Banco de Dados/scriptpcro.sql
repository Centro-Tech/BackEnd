CREATE DATABASE IF NOT EXISTS MimaStore;
USE MimaStore;

-- Tabela Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cargo VARCHAR(50) NOT NULL
);

-- Tabela Cliente
CREATE TABLE IF NOT EXISTS Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    cpf VARCHAR(14)
);

-- Tabela Fornecedor
CREATE TABLE IF NOT EXISTS Fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255)
);

-- Tabela Venda
CREATE TABLE IF NOT EXISTS Venda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    valor_total DOUBLE DEFAULT 0.0,
    data DATETIME DEFAULT CURRENT_TIMESTAMP,
    fkCliente INT,
    fkFuncionario INT,
    CONSTRAINT fk_venda_cliente FOREIGN KEY (fkCliente) REFERENCES Cliente(id_cliente),
    CONSTRAINT fk_venda_funcionario FOREIGN KEY (fkFuncionario) REFERENCES Usuario(id)
);

-- Tabelas auxiliares
CREATE TABLE IF NOT EXISTS Tamanho (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Cor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Material (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS Categoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

-- Tabela Item
CREATE TABLE IF NOT EXISTS Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
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
CREATE TABLE IF NOT EXISTS ItemVenda (
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

-- Inserts
INSERT INTO Usuario (nome, email, senha, telefone, endereco, cargo) VALUES
('John Doe', 'john@doe.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1111-11111', 'Rua do bacana', 'Funcionario'),
('Ana Souza', 'ana.gerente@mimastore.com', 'senha123', '11999990000', 'Av. Central', 'Gerente'),
('Carlos Lima', 'carlos.funcionario@mimastore.com', 'senha123', '11988887777', 'Rua das Laranjeiras', 'Funcionario');

INSERT INTO Cliente (nome, email, telefone, endereco, cpf) VALUES
('João Pereira', 'joao@gmail.com', '11977776666', 'Rua das Flores', '12345678901'),
('Maria Silva', 'maria@gmail.com', '11966665555', 'Rua dos Sonhos', '98765432100');

INSERT INTO Fornecedor (nome, telefone, email) VALUES
('Fornecedora A', '1133221100', 'contato@fornecedoraa.com'),
('Fornecedora B', '1144332211', 'suporte@fornecedorab.com');

INSERT INTO Cor (nome) VALUES ('Vermelho'), ('Azul'), ('Preto');
INSERT INTO Categoria (nome) VALUES ('Camiseta'), ('Calça'), ('Acessório');
INSERT INTO Material (nome) VALUES ('Algodão'), ('Jeans'), ('Couro');
INSERT INTO Tamanho (nome) VALUES ('P'), ('M'), ('G'), ('GG');

INSERT INTO Item (codigo, qtd_estoque, nome, preco, fkFornecedor, fkCor, fkCategoria, fkMaterial, fkTamanho) VALUES
('CAMV021M', 50, 'Camiseta Vermelha M', 39.90, 1, 1, 1, 1, 2),
('CAL567G', 30, 'Calça Jeans G', 79.90, 2, 2, 2, 2, 3),
('ACS678P', 20, 'Acessório Preto P', 119.90, 1, 3, 3, 3, 1);

INSERT INTO Venda (valor_total, fkCliente, fkFuncionario) VALUES
(159.80, 1, 1),
(119.90, 2, 2);

INSERT INTO ItemVenda (fkItem, fkItemFornecedor, fkVenda, fkVendaCliente, fkVendaFuncionario, qtdParaVender) VALUES
(1, 1, 1, 1, 1, 2),
(2, 2, 1, 1, 1, 1),
(3, 1, 2, 2, 2, 1);
