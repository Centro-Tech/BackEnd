CREATE DATABASE IF NOT EXISTS `MimaStore` DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

USE `MimaStore`;

-- VENDA / Funcionario / CLIENTE
-- Tabela `Usuario`
CREATE TABLE IF NOT EXISTS `Usuario` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `telefone` VARCHAR(20) NOT NULL,
    `endereco` VARCHAR(255) NOT NULL,
    `senha` VARCHAR(255) NOT NULL,
    `cargo` VARCHAR(50) NOT NULL,
    `imagem` VARCHAR(500)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela `Cliente` (presume-se que Cliente seja semelhante a Usuario)
CREATE TABLE IF NOT EXISTS `Cliente` (
    `id_cliente` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `telefone` VARCHAR(20) NOT NULL,
    `endereco` VARCHAR(255) NOT NULL,
    `cpf` VARCHAR(20)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela `Fornecedor`
CREATE TABLE IF NOT EXISTS `Fornecedor` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `telefone` VARCHAR(20),
    `email` VARCHAR(100),
    `endereco` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Venda` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `valor_total` DOUBLE DEFAULT 0.0,
    `data` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `fkCliente` INT,
    `fkFuncionario` INT,
    CONSTRAINT `fk_venda_cliente` FOREIGN KEY (`fkCliente`) REFERENCES `Cliente`(`id_cliente`) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT `fk_venda_funcionario` FOREIGN KEY (`fkFuncionario`) REFERENCES `Usuario`(`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Tamanho` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Cor` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Material` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Categoria` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nome` VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Item` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `codigo` VARCHAR(255),
    `qtd_estoque` INT,
    `nome` VARCHAR(255),
    `preco` DOUBLE,
    `fkTamanho` INT,
    `fkCor` INT,
    `fkMaterial` INT,
    `fkCategoria` INT,
    `fkFornecedor` INT,
    FOREIGN KEY (`fkTamanho`) REFERENCES `Tamanho`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`fkCor`) REFERENCES `Cor`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`fkMaterial`) REFERENCES `Material`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`fkCategoria`) REFERENCES `Categoria`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`fkFornecedor`) REFERENCES `Fornecedor`(`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela `ItemVenda`
CREATE TABLE IF NOT EXISTS `ItemVenda` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `fkItem` INT,
    `fkItemFornecedor` INT,
    `fkVenda` INT,
    `qtdParaVender` INT,
    FOREIGN KEY (`fkItem`) REFERENCES `Item`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`fkItemFornecedor`) REFERENCES `Fornecedor`(`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (`fkVenda`) REFERENCES `Venda`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Adiciona colunas de recovery na tabela Usuario (se já existir nada será alterado além disso)
ALTER TABLE `Usuario`
    ADD COLUMN IF NOT EXISTS `recovery_token` VARCHAR(500),
    ADD COLUMN IF NOT EXISTS `recovery_token_expiry` DATETIME;

DROP TABLE categoria;
DROP TABLE cor;
DROP TABLE item;
DROP TABLE material;
DROP TABLE venda;

-- Inserções de teste
INSERT INTO `Usuario` (`nome`, `email`, `senha`, `telefone`, `endereco`, `cargo`)
VALUES ('John Doe', 'john@doe.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1111-11111', 'Rua do bacana', 'Funcionario');
-- senha: 123456

-- Consultas de verificação (opcional)
SELECT * FROM `Usuario`;

SELECT * FROM `Fornecedor`;

INSERT INTO `Fornecedor` (`nome`, `telefone`, `email`)
VALUES ('Empresa XYZ LTDA', '11987654321', 'contato@empresa.com');

SELECT * FROM `Fornecedor`;

SELECT * FROM `Usuario`;

INSERT INTO `Tamanho` (`nome`) VALUES ('M');

INSERT INTO `Categoria` (`nome`) VALUES ('Camiseta');

INSERT INTO `Cor` (`nome`) VALUES ('Preto');

INSERT INTO `Material` (`nome`) VALUES ('Lã');

SELECT * FROM `Item`;

SELECT * FROM `Fornecedor`;

SELECT * FROM `Categoria`;

SELECT * FROM `Usuario`;

SELECT * FROM `Cliente`;

SELECT * FROM `ItemVenda`;

SELECT * FROM `Venda`;
-- Inserts
INSERT INTO Usuario (nome, email, senha, telefone, endereco, cargo) VALUES
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

INSERT INTO `ItemVenda` (`fkItem`, `fkItemFornecedor`, `fkVenda`, `qtdParaVender`) VALUES
(1, 1, 1, 2),
(2, 2, 1, 1),
(3, 1, 2, 1);
