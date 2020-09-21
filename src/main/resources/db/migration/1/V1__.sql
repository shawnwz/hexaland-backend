create table hexagons
(
    id serial primary key,
    edge_0 varchar(20),
    edge_1 varchar(20),
    edge_2 varchar(20),
    edge_3 varchar(20),
    edge_4 varchar(20),
    edge_5 varchar(20),
    name varchar(20),
    status boolean default false,
    create_time bigint
);