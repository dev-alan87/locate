CREATE TABLE tb_city (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    population BIGINT
);
INSERT INTO tb_city (id, name, population)
VALUES
    (1, 'Sao Paulo', 12396372),
    (2, 'Rio de Janeiro', 17463349),
    (3, 'Belo Horizonte', 2530701),
    (4, 'Porto Velho', 400000),
    (5, 'Goiania', 500000),
    (6, 'Ceara', 600000),
    (7, 'Porto Alegre', 700000);