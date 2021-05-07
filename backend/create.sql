
    create table phone (
       tb_client_id bigint not null,
        phone varchar(255)
    ) engine=InnoDB;

    create table tb_address (
       id bigint not null auto_increment,
        cep varchar(255),
        complement varchar(255),
        district varchar(255),
        num varchar(255),
        street varchar(255),
        city_id bigint,
        client_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table tb_category (
       id bigint not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    create table tb_city (
       id bigint not null auto_increment,
        name varchar(255),
        state_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table tb_client (
       id bigint not null auto_increment,
        cpf_cnpj varchar(255),
        email varchar(255),
        name varchar(255),
        type integer,
        primary key (id)
    ) engine=InnoDB;

    create table tb_order (
       id bigint not null auto_increment,
        instant TIMESTAMP WITHOUT TIME ZONE,
        client_id bigint,
        shipping_address_id bigint,
        primary key (id)
    ) engine=InnoDB;

    create table tb_order_item (
       discount double precision,
        price double precision,
        quantity integer,
        product_id bigint not null,
        order_id bigint not null,
        primary key (order_id, product_id)
    ) engine=InnoDB;

    create table tb_payment (
       order_id bigint not null,
        status integer,
        primary key (order_id)
    ) engine=InnoDB;

    create table tb_payment_boleto (
       payment_date datetime,
        payment_due datetime,
        order_id bigint not null,
        primary key (order_id)
    ) engine=InnoDB;

    create table tb_payment_card (
       installment integer,
        order_id bigint not null,
        primary key (order_id)
    ) engine=InnoDB;

    create table tb_product (
       id bigint not null auto_increment,
        name varchar(255),
        price double precision,
        primary key (id)
    ) engine=InnoDB;

    create table tb_product_category (
       product_id bigint not null,
        category_id bigint not null
    ) engine=InnoDB;

    create table tb_state (
       id bigint not null auto_increment,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB;

    alter table tb_client 
       add constraint UK_tbu74oe5ntiego6pu36jfokha unique (email);

    alter table phone 
       add constraint FKhw1o6o0vvyjgcv92mwak3fv4l 
       foreign key (tb_client_id) 
       references tb_client (id);

    alter table tb_address 
       add constraint FKpm4x3qy2wea2p4ea7bs217nr5 
       foreign key (city_id) 
       references tb_city (id);

    alter table tb_address 
       add constraint FKmgkeodciwq4rofwws5m08v1yh 
       foreign key (client_id) 
       references tb_client (id);

    alter table tb_city 
       add constraint FK1rn7oty4mwqviyw8vk67crapo 
       foreign key (state_id) 
       references tb_state (id);

    alter table tb_order 
       add constraint FK7c9of0p9ogx0w8sfrebt4n9kk 
       foreign key (client_id) 
       references tb_client (id);

    alter table tb_order 
       add constraint FKj0sp23t2b34btdre6r1prr236 
       foreign key (shipping_address_id) 
       references tb_address (id);

    alter table tb_order_item 
       add constraint FK4h5xid5qehset7qwe5l9c997x 
       foreign key (product_id) 
       references tb_product (id);

    alter table tb_order_item 
       add constraint FKgeobgl2xu916he8vhljktwxnx 
       foreign key (order_id) 
       references tb_order (id);

    alter table tb_payment 
       add constraint FKokaf4il2cwit4h780c25dv04r 
       foreign key (order_id) 
       references tb_order (id);

    alter table tb_payment_boleto 
       add constraint FKecaol07qkc8mwpwqtx4mor084 
       foreign key (order_id) 
       references tb_payment (order_id);

    alter table tb_payment_card 
       add constraint FKes6mhnl95dbkt22wi0f965rwg 
       foreign key (order_id) 
       references tb_payment (order_id);

    alter table tb_product_category 
       add constraint FK5r4sbavb4nkd9xpl0f095qs2a 
       foreign key (category_id) 
       references tb_category (id);

    alter table tb_product_category 
       add constraint FKgbof0jclmaf8wn2alsoexxq3u 
       foreign key (product_id) 
       references tb_product (id);
