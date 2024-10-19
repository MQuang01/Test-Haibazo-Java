CREATE
DATABASE IF NOT EXISTS db_haibazo_test;

USE
db_haibazo_test;

CREATE TABLE categories
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE order_details
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    order_id   BIGINT NULL,
    product_id BIGINT NULL,
    quantity   INT NOT NULL,
    price DOUBLE NOT NULL,
    CONSTRAINT pk_order_details PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    user_id       BIGINT NULL,
    `description` VARCHAR(255) NULL,
    address       VARCHAR(255) NULL,
    phone         VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    created_date  datetime NULL,
    status_order  VARCHAR(255) NULL,
    total DOUBLE NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    quantity      INT NULL,
    price DOUBLE NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE users
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    username  VARCHAR(255) NULL,
    password  VARCHAR(255) NULL,
    full_name VARCHAR(255) NULL,
    address   VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    phone     VARCHAR(255) NULL,
    dob       date NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE order_details
    ADD CONSTRAINT FK_ORDER_DETAILS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_details
    ADD CONSTRAINT FK_ORDER_DETAILS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);