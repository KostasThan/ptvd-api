--liquibase formatted sql

--changeset mds:0
create table countries
(
    name varchar(255) not null,
    primary key (name)
);








