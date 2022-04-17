--liquibase formatted sql

--changeset mixas:1
create table person
(
    id   int primary key,
    name varchar(255)
);