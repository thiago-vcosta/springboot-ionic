INSERT INTO tb_category (name) VALUES ('Informática');
INSERT INTO tb_category (name) VALUES ('Eletrônicos');
INSERT INTO tb_category (name) VALUES ('Escritório');

INSERT INTO tb_product (name, price) VALUES ('Computador', 2000.0);
INSERT INTO tb_product (name, price) VALUES ('TV', 800.0);
INSERT INTO tb_product (name, price) VALUES ('Mouse', 80.0);

INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 1);
INSERT INTO tb_product_category (product_id, category_id) VALUES (1, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (2, 2);
INSERT INTO tb_product_category (product_id, category_id) VALUES (3, 1);

INSERT INTO tb_state (name) VALUES ('São Paulo');
INSERT INTO tb_state (name) VALUES ('Minas Gerais');

INSERT INTO tb_city (name, state_id) VALUES ('Campinas', 1);
INSERT INTO tb_city (name, state_id) VALUES ('São Paulo', 1);
INSERT INTO tb_city (name, state_id) VALUES ('Uberlândia', 2);

INSERT INTO tb_client (name, email, cpf_Cnpj, type) VALUES ('Maria Silva', 'maria@gmail.com', '12345678901', 1);

INSERT INTO phone (phone, tb_client_id) VALUES ('41237080', 1);
INSERT INTO phone (phone, tb_client_id) VALUES ('41214031', 1);

INSERT INTO tb_address (street, num, complement, district, cep, city_id, client_id) VALUES ('Rua Flores', '300', 'AP 303', 'jardim', '12345000', 1, 1);

INSERT INTO tb_address (street, num, complement, district, cep, city_id, client_id) VALUES ('Avenida Matos', '1300', null, 'centro', '78945111', 2, 1);

INSERT INTO tb_order (instant, client_id, shipping_address_id) VALUES (TIMESTAMP WITH TIME ZONE '2020-12-10T13:00:00Z', 1, 1);

INSERT INTO tb_order (instant, client_id, shipping_address_id) VALUES (TIMESTAMP WITH TIME ZONE '2020-12-10T13:00:00Z', 1, 2);

INSERT INTO tb_payment (status, order_id) VALUES (1, 1);
INSERT INTO tb_payment_card (installment, order_id) VALUES (6, 1);


INSERT INTO tb_payment (status, order_id) VALUES (2, 2);
INSERT INTO tb_payment_boleto (payment_due, payment_date, order_id) VALUES (TIMESTAMP WITH TIME ZONE '2020-12-10T13:00:00Z', null, 2);

INSERT INTO tb_order_item (discount, price, quantity, product_id, order_id) VALUES (0.0, 2000.00, 1, 1, 1);
INSERT INTO tb_order_item (discount, price, quantity, product_id, order_id) VALUES (0.0, 80.00, 2, 3, 1);
INSERT INTO tb_order_item (discount, price, quantity, product_id, order_id) VALUES (100.0, 800.00, 1, 2, 2);
