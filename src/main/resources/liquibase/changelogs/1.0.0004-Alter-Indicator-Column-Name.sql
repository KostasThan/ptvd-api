--liquibase formatted sql

--changeset mds:4
alter table indicators change long_description description varchar(1000);