-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-10-04 10:57:58.885

-- tables
-- Table: customer
CREATE TABLE customer (
                          id serial  NOT NULL,
                          registration_code int  NOT NULL,
                          full_name varchar(255)  NOT NULL,
                          email varchar(255) UNIQUE NOT NULL,
                          telephone int  NOT NULL,
                          CONSTRAINT customer_pk PRIMARY KEY (id)
);

-- Table: customer_order
CREATE TABLE customer_order (
                                id serial  NOT NULL,
                                customer_id int  NOT NULL,
                                order_id int  NOT NULL,
                                CONSTRAINT customer_order_pk PRIMARY KEY (id)
);

-- Table: order
CREATE TABLE "order" (
                         id serial  NOT NULL,
                         submission_date date  NOT NULL,
                         CONSTRAINT order_pk PRIMARY KEY (id)
);

-- Table: order_line
CREATE TABLE order_line (
                            id serial  NOT NULL,
                            product_id int  NOT NULL,
                            quantity int  NOT NULL,
                            CONSTRAINT order_line_pk PRIMARY KEY (id)
);

-- Table: order_order_line
CREATE TABLE order_order_line (
                                  id serial  NOT NULL,
                                  order_id int  NOT NULL,
                                  order_line_id int  NOT NULL,
                                  CONSTRAINT order_order_line_pk PRIMARY KEY (id)
);

-- Table: product
CREATE TABLE product (
                         id serial  NOT NULL,
                         name varchar(255)  NOT NULL,
                         sku_code int UNIQUE NOT NULL,
                         unit_price money NOT NULL,
                         CONSTRAINT product_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: customer_order_customer (table: customer_order)
ALTER TABLE customer_order ADD CONSTRAINT customer_order_customer
    FOREIGN KEY (customer_id)
        REFERENCES customer (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: customer_order_order (table: customer_order)
ALTER TABLE customer_order ADD CONSTRAINT customer_order_order
    FOREIGN KEY (order_id)
        REFERENCES "order" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_line_product (table: order_line)
ALTER TABLE order_line ADD CONSTRAINT order_line_product
    FOREIGN KEY (product_id)
        REFERENCES product (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_order_line_order (table: order_order_line)
ALTER TABLE order_order_line ADD CONSTRAINT order_order_line_order
    FOREIGN KEY (order_id)
        REFERENCES "order" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: order_order_line_order_line (table: order_order_line)
ALTER TABLE order_order_line ADD CONSTRAINT order_order_line_order_line
    FOREIGN KEY (order_line_id)
        REFERENCES order_line (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- End of file.

