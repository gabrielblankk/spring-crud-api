CREATE TABLE product (
    id SERIAL PRIMARY KEY UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL
);