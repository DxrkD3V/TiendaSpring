CREATE DATABASE IF NOT EXISTS tiendaspring;
USE tiendaspring;

-- Crear el usuario si no existe
CREATE USER IF NOT EXISTS 'tiendaspring'@'localhost' IDENTIFIED BY 'tiendaspring';

-- Asignar permisos mínimos
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON tiendaspring.* TO 'tiendaspring'@'localhost';
FLUSH PRIVILEGES;

-- Creación de tablas
create table categories
(
    id          bigint auto_increment
        primary key,
    description varchar(1000) not null,
    image       varchar(255)  null,
    name        varchar(50)   not null,
    constraint UKt8o6pivur7nn124jehx7cygw5
        unique (name)
);

create table products
(
    id           bigint auto_increment
        primary key,
    description  varchar(1000) not null,
    hp           int           not null,
    imageurl     varchar(255)  not null,
    manufacture  varchar(50)   not null,
    max_velocity int           not null,
    motor        varchar(50)   not null,
    name         varchar(50)   not null,
    price        double        null,
    stock        int           not null,
    category_id  bigint        not null,
    constraint UKo61fmio5yukmmiqgnxf8pnavn
        unique (name),
    constraint FKog2rp4qthbtt2lfyhfo32lsw9
        foreign key (category_id) references categories (id)
);

create table ratings
(
    id         bigint auto_increment
        primary key,
    comment    varchar(1000) not null,
    date       datetime(6)   not null,
    name       varchar(50)   not null,
    rating     double        not null,
    product_id bigint        null,
    constraint UK6j4yxn6ynkbxxt4gnn3c4358m
        unique (name, product_id),
    constraint FK228us4dg38ewge41gos8y761r
        foreign key (product_id) references products (id)
);

create table shopping_cart
(
    id         bigint auto_increment
        primary key,
    add_at     timestamp default current_timestamp() not null,
    units      int                                   not null,
    update_at  timestamp default current_timestamp() not null on update current_timestamp(),
    product_id bigint                                not null,
    constraint FK26ajdolmyw3a95bhn6pjk5dor
        foreign key (product_id) references products (id)
);
