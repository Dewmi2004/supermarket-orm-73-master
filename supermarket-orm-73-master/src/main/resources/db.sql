DROP DATABASE supermarketfx;

CREATE DATABASE IF NOT EXISTS supermarketfx;

USE supermarketfx;

CREATE TABLE customer
(
    customer_id VARCHAR(10) PRIMARY KEY,
    name        VARCHAR(100),
    nic         VARCHAR(100),
    email       VARCHAR(100),
    phone       VARCHAR(20)
);

CREATE TABLE item
(
    item_id  VARCHAR(10) PRIMARY KEY,
    name     VARCHAR(100),
    quantity INT,
    price    DECIMAL(10, 2)
);

CREATE TABLE orders
(
    order_id    VARCHAR(10) PRIMARY KEY,
    customer_id VARCHAR(10),
    order_date  DATE,
    FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
);

CREATE TABLE order_details
(
    order_id VARCHAR(10),
    item_id  VARCHAR(10),
    quantity INT,
    price    DECIMAL(10, 2),
    FOREIGN KEY (order_id) REFERENCES orders (order_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id)
);