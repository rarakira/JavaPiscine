DROP TABLE IF EXISTS product;

CREATE TABLE IF NOT EXISTS product (
    identifier  INT PRIMARY KEY NOT NULL,
    name        VARCHAR(50) NOT NULL,
    price       INT NOT NULL
);