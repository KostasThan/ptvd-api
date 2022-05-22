--liquibase formatted sql

--changeset mds:2
create table country_indicator
(
    id             varchar(36) ,
    country_name   varchar(255),
    indicator_code varchar(255),
    primary key (id)
);

alter table country_indicator
    add constraint country_indicator_to_country_name
        foreign key (country_name)
            references countries (name);

alter table country_indicator
    add constraint country_indicator_to_indicator_code
        foreign key (indicator_code)
            references indicators (code);

alter table country_indicator
    add constraint country_indicator_unique_country_name_indicator_code unique (country_name, indicator_code);