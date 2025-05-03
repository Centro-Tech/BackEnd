insert into usuario
(nome, email, senha)
values
    ('John Doe', 'john@doe.com', '$2a$10$0/TKTGxdREbWaWjWYhwf6e9P1fPOAMMNqEnZgOG95jnSkHSfkkIrC');
-- senha: 123456

insert into categoria values
(1, 'Camiseta');

insert into cor values
(1, 'Preto');

insert into material values
(1, 'Algodão');

insert into tamanho values
(1, 'M');

INSERT INTO Fornecedor (nome, telefone, email)
VALUES ('Empresa XYZ LTDA', '11987654321', 'contato@empresa.com');

INSERT INTO Item (
    codigo,
    qtd_Estoque,
    nome,
    fk_Tamanho,
    fk_Cor,
    fk_Material,
    fk_Categoria,
    preco,
    fk_Fornecedor
)
VALUES (
    'BLU123M',
    10,
    'Camiseta Polo Azul',
    (SELECT id FROM tamanho WHERE tamanho = 'M'),
    (SELECT id FROM cor WHERE nome = 'Azul'),
    (SELECT id FROM material WHERE material = 'Algodão'),
    (SELECT id FROM categoria WHERE nome = 'Camiseta'),
    59.99,
    (SELECT id FROM Fornecedor WHERE nome = 'Empresa XYZ LTDA')
);
