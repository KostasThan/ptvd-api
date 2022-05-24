--liquibase formatted sql

--changeset mds:2

create table metrics_armenia
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_armenia_indicator_code_index
    ON metrics_armenia (indicator_code);

CREATE INDEX metrics_armenia_indicator_code_year_index
    ON metrics_armenia (indicator_code,year);


create table metrics_belgium
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_belgium_indicator_code_index
    ON metrics_belgium (indicator_code);

CREATE INDEX metrics_belgium_indicator_code_year_index
    ON metrics_belgium (indicator_code,year);


create table metrics_bulgaria
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_bulgaria_indicator_code_index
    ON metrics_bulgaria (indicator_code);

CREATE INDEX metrics_bulgaria_indicator_code_year_index
    ON metrics_bulgaria (indicator_code,year);


create table metrics_bosnia_and_herzegovina
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_bosnia_and_herzegovina_indicator_code_index
    ON metrics_bosnia_and_herzegovina (indicator_code);

CREATE INDEX metrics_bosnia_and_herzegovina_indicator_code_year_index
    ON metrics_bosnia_and_herzegovina (indicator_code,year);


create table metrics_cyprus
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_cyprus_indicator_code_index
    ON metrics_cyprus (indicator_code);

CREATE INDEX metrics_cyprus_indicator_code_year_index
    ON metrics_cyprus (indicator_code,year);


create table metrics_germany
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_germany_indicator_code_index
    ON metrics_germany (indicator_code);

CREATE INDEX metrics_germany_indicator_code_year_index
    ON metrics_germany (indicator_code,year);


create table metrics_denmark
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_denmark_indicator_code_index
    ON metrics_denmark (indicator_code);

CREATE INDEX metrics_denmark_indicator_code_year_index
    ON metrics_denmark (indicator_code,year);


create table metrics_spain
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_spain_indicator_code_index
    ON metrics_spain (indicator_code);

CREATE INDEX metrics_spain_indicator_code_year_index
    ON metrics_spain (indicator_code,year);


create table metrics_estonia
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_estonia_indicator_code_index
    ON metrics_estonia (indicator_code);

CREATE INDEX metrics_estonia_indicator_code_year_index
    ON metrics_estonia (indicator_code,year);


create table metrics_france
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_france_indicator_code_index
    ON metrics_france (indicator_code);

CREATE INDEX metrics_france_indicator_code_year_index
    ON metrics_france (indicator_code,year);


create table metrics_united_kingdom
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_united_kingdom_indicator_code_index
    ON metrics_united_kingdom (indicator_code);

CREATE INDEX metrics_united_kingdom_indicator_code_year_index
    ON metrics_united_kingdom (indicator_code,year);


create table metrics_georgia
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_georgia_indicator_code_index
    ON metrics_georgia (indicator_code);

CREATE INDEX metrics_georgia_indicator_code_year_index
    ON metrics_georgia (indicator_code,year);


create table metrics_greece
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_greece_indicator_code_index
    ON metrics_greece (indicator_code);

CREATE INDEX metrics_greece_indicator_code_year_index
    ON metrics_greece (indicator_code,year);


create table metrics_croatia
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_croatia_indicator_code_index
    ON metrics_croatia (indicator_code);

CREATE INDEX metrics_croatia_indicator_code_year_index
    ON metrics_croatia (indicator_code,year);


create table metrics_hungary
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_hungary_indicator_code_index
    ON metrics_hungary (indicator_code);

CREATE INDEX metrics_hungary_indicator_code_year_index
    ON metrics_hungary (indicator_code,year);


create table metrics_italy
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_italy_indicator_code_index
    ON metrics_italy (indicator_code);

CREATE INDEX metrics_italy_indicator_code_year_index
    ON metrics_italy (indicator_code,year);


create table metrics_lithuania
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_lithuania_indicator_code_index
    ON metrics_lithuania (indicator_code);

CREATE INDEX metrics_lithuania_indicator_code_year_index
    ON metrics_lithuania (indicator_code,year);


create table metrics_moldova
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_moldova_indicator_code_index
    ON metrics_moldova (indicator_code);

CREATE INDEX metrics_moldova_indicator_code_year_index
    ON metrics_moldova (indicator_code,year);


create table metrics_netherlands
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_netherlands_indicator_code_index
    ON metrics_netherlands (indicator_code);

CREATE INDEX metrics_netherlands_indicator_code_year_index
    ON metrics_netherlands (indicator_code,year);


create table metrics_norway
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_norway_indicator_code_index
    ON metrics_norway (indicator_code);

CREATE INDEX metrics_norway_indicator_code_year_index
    ON metrics_norway (indicator_code,year);


create table metrics_poland
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_poland_indicator_code_index
    ON metrics_poland (indicator_code);

CREATE INDEX metrics_poland_indicator_code_year_index
    ON metrics_poland (indicator_code,year);


create table metrics_portugal
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_portugal_indicator_code_index
    ON metrics_portugal (indicator_code);

CREATE INDEX metrics_portugal_indicator_code_year_index
    ON metrics_portugal (indicator_code,year);


create table metrics_romania
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_romania_indicator_code_index
    ON metrics_romania (indicator_code);

CREATE INDEX metrics_romania_indicator_code_year_index
    ON metrics_romania (indicator_code,year);


create table metrics_russian_federation
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_russian_federation_indicator_code_index
    ON metrics_russian_federation (indicator_code);

CREATE INDEX metrics_russian_federation_indicator_code_year_index
    ON metrics_russian_federation (indicator_code,year);


create table metrics_sweden
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_sweden_indicator_code_index
    ON metrics_sweden (indicator_code);

CREATE INDEX metrics_sweden_indicator_code_year_index
    ON metrics_sweden (indicator_code,year);


create table metrics_turkey
(
    id                   varchar(36) ,
    indicator_code       varchar(36) ,
    metric               double precision,
    year                 integer,
    primary key (id)
);

CREATE INDEX metrics_turkey_indicator_code_index
    ON metrics_turkey (indicator_code);

CREATE INDEX metrics_turkey_indicator_code_year_index
    ON metrics_turkey (indicator_code,year);
