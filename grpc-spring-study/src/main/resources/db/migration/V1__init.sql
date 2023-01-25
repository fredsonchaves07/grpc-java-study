CREATE TABLE PRODUCT(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    quantity_int_stock INTEGER NOT NULL,
    CONSTRAINT id UNIQUE (id)
);

INSERT INTO PRODUCT(id, name, price, quantity_int_stock) VALUES (1, 'Product A', 10.99, 10);
INSERT INTO PRODUCT(id, name, price, quantity_int_stock) VALUES (1, 'Product B', 25.69, 100);