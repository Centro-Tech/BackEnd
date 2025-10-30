create database if not exists `mimastore` default character set = utf8mb4 collate = utf8mb4_unicode_ci;

use `mimastore`;

-- venda / funcionario / cliente
-- tabela `usuario`
create table if not exists `usuario` (
    `id` int not null auto_increment primary key,
    `nome` varchar(100) not null,
    `email` varchar(100) not null,
    `telefone` varchar(20) not null,
    `endereco` varchar(255) not null,
    `senha` varchar(255) not null,
    `cargo` varchar(50) not null,
    `imagem` varchar(500),
    `recovery_token` varchar(500),
    `recovery_token_expiry` datetime
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

-- tabela `cliente` (presume-se que cliente seja semelhante a usuario)
create table if not exists `cliente` (
    `id_cliente` int not null auto_increment primary key,
    `nome` varchar(100) not null,
    `email` varchar(100) not null,
    `telefone` varchar(20) not null,
    `endereco` varchar(255) not null,
    `cpf` varchar(20)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

-- tabela `fornecedor`
create table if not exists `fornecedor` (
    `id` int not null auto_increment primary key,
    `nome` varchar(100) not null,
    `telefone` varchar(20),
    `email` varchar(100),
    `endereco` varchar(255)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

create table if not exists `venda` (
    `id` int not null auto_increment primary key,
    `valor_total` double default 0.0,
    `data` datetime default current_timestamp,
    `fkcliente` int,
    `fkfuncionario` int,
    constraint `fk_venda_cliente` foreign key (`fkcliente`) references `cliente`(`id_cliente`) on delete set null on update cascade,
    constraint `fk_venda_funcionario` foreign key (`fkfuncionario`) references `usuario`(`id`) on delete set null on update cascade
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

create table if not exists `tamanho` (
    `id` int not null auto_increment primary key,
    `nome` varchar(255)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

create table if not exists `cor` (
    `id` int not null auto_increment primary key,
    `nome` varchar(255)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

create table if not exists `material` (
    `id` int not null auto_increment primary key,
    `nome` varchar(255)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

create table if not exists `categoria` (
    `id` int not null auto_increment primary key,
    `nome` varchar(255)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

create table if not exists `item` (
    `id` int not null auto_increment primary key,
    `codigo` varchar(255),
    `qtd_estoque` int,
    `nome` varchar(255),
    `preco` double,
    `fktamanho` int,
    `fkcor` int,
    `fkmaterial` int,
    `fkcategoria` int,
    `fkfornecedor` int,
    foreign key (`fktamanho`) references `tamanho`(`id`) on delete set null on update cascade,
    foreign key (`fkcor`) references `cor`(`id`) on delete set null on update cascade,
    foreign key (`fkmaterial`) references `material`(`id`) on delete set null on update cascade,
    foreign key (`fkcategoria`) references `categoria`(`id`) on delete set null on update cascade,
    foreign key (`fkfornecedor`) references `fornecedor`(`id`) on delete set null on update cascade
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

-- tabela `itemvenda`
create table if not exists `itemvenda` (
    `id` int not null auto_increment primary key,
    `fkitem` int,
    `fkitemfornecedor` int,
    `fkvenda` int,
    `qtdparavender` int,
    foreign key (`fkitem`) references `item`(`id`) on delete set null on update cascade,
    foreign key (`fkitemfornecedor`) references `fornecedor`(`id`) on delete set null on update cascade,
    foreign key (`fkvenda`) references `venda`(`id`) on delete cascade on update cascade
) engine=innodb default charset=utf8mb4 collate=utf8mb4_unicode_ci;

-- adiciona colunas de recovery na tabela usuario (se já existir nada será alterado além disso)


show tables;

-- inserções de teste
insert into `usuario` (`nome`, `email`, `senha`, `telefone`, `endereco`, `cargo`)
values ('John Doe', 'john@doe.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC', '1111-11111', 'Rua do bacana', 'Funcionario');
-- senha: 123456

-- consultas de verificação (opcional)
select * from `usuario`;

select * from `fornecedor`;

insert into `fornecedor` (`nome`, `telefone`, `email`)
values ('Empresa XYZ LTDA', '11987654321', 'contato@empresa.com');

select * from `fornecedor`;

select * from `usuario`;

insert into `tamanho` (`nome`) values ('M');

insert into `categoria` (`nome`) values ('Camiseta');

insert into `cor` (`nome`) values ('Preto');

insert into `material` (`nome`) values ('Lã');

select * from `item`;

select * from `fornecedor`;

select * from `categoria`;

select * from `usuario`;

select * from `cliente`;

select * from `itemvenda`;

select * from `venda`;
-- inserts
insert into usuario (nome, email, senha, telefone, endereco, cargo) values
('Ana Souza', 'ana.gerente@mimastore.com', 'senha123', '11999990000', 'Av. Central', 'Gerente'),
('Carlos Lima', 'carlos.funcionario@mimastore.com', 'senha123', '11988887777', 'Rua das Laranjeiras', 'Funcionario');

insert into cliente (nome, email, telefone, endereco, cpf) values
('João Pereira', 'joao@gmail.com', '11977776666', 'Rua das Flores', '12345678901'),
('Maria Silva', 'maria@gmail.com', '11966665555', 'Rua dos Sonhos', '98765432100');

insert into fornecedor (nome, telefone, email) values
('Fornecedora A', '1133221100', 'contato@fornecedoraa.com'),
('Fornecedora B', '1144332211', 'suporte@fornecedorab.com');

insert into cor (nome) values ('Vermelho'), ('Azul'), ('Preto');
insert into categoria (nome) values ('Camiseta'), ('Calça'), ('Acessório');
insert into material (nome) values ('Algodão'), ('Jeans'), ('Couro');
insert into tamanho (nome) values ('P'), ('M'), ('G'), ('GG');

insert into item (codigo, qtd_estoque, nome, preco, fkfornecedor, fkcor, fkcategoria, fkmaterial, fktamanho) values
('CAMV021M', 50, 'Camiseta Vermelha M', 39.90, 1, 1, 1, 1, 2),
('CAL567G', 30, 'Calça Jeans G', 79.90, 2, 2, 2, 2, 3),
('ACS678P', 20, 'Acessório Preto P', 119.90, 1, 3, 3, 3, 1);

insert into venda (valor_total, fkcliente, fkfuncionario) values
(159.80, 1, 1),
(119.90, 2, 2);

insert into `itemvenda` (`fkitem`, `fkitemfornecedor`, `fkvenda`, `qtdparavender`) values
(1, 1, 1, 2),
(2, 2, 1, 1),
(3, 1, 2, 1);
