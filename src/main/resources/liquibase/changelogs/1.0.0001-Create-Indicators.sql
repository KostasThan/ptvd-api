--liquibase formatted sql

--changeset mds:1
create table indicators
(
    id                  varchar(36) ,
    code                varchar(255),
    name                varchar(1000),
    description         varchar(10000),
    source_organization varchar(1000),
    primary key (id)
);

alter table indicators add constraint indicators_unique_code unique (code);
