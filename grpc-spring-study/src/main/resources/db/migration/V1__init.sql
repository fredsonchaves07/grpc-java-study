CREATE TABLE PRODUCT(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price FLOAT,
    quantity_in_stock INTEGER NOT NULL,
    CONSTRAINT id UNIQUE (id)
);

INSERT INTO PRODUCT(id, name, price, quantity_in_stock) VALUES (100, 'Product A', 10.9, 10);
INSERT INTO PRODUCT(id, name, price, quantity_in_stock) VALUES (200, 'Product B', 25.6, 100);