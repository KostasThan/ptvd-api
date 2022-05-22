--liquibase formatted sql

--changeset mds:3
create table metric
(
    id                   varchar(36) ,
    metric               double precision,
    year                 integer,
    country_indicator_id varchar(36) ,
    primary key (id)
);

alter table metric
    add constraint metric_to_country_indicator_id
        foreign key (country_indicator_id)
            references country_indicator (id);

CREATE INDEX metric_year_index
    ON metric (country_indicator_id);
