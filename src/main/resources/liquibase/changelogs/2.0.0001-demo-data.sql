--liquibase formatted sql

--changeset mds:2.3

INSERT INTO countries (name) VALUES
                                         ('ALBANIA'),
                                         ('Berlin'),
                                         ('GREECE'),
                                         ('Italy'),
                                         ('MIXAS'),
                                         ('SPAIN');

INSERT INTO indicators (id,code,long_description,name,source_organization) VALUES
                                                                                       ('4bcea180-1943-44f6-919a-19c703be4f44','AG.CON.FERT.PT.ZS','Fertilizer consumption measures the quantity of plant nutrients used per unit of arable land. Fertilizer products cover nitrogenous, potash, and phosphate fertilizers (including ground rock phosphate). Traditional nutrients--animal and plant manures--are not included. For the purpose of data dissemination, FAO has adopted the concept of a calendar year (January to December). Some countries compile fertilizer data on a calendar year basis, while others are on a split-year basis.','Fertilizer consumption (% of fertilizer production)','International Monetary Fund, Balance of Payments Statistics Yearbook and data files.'),
                                                                                       ('6525699c-d699-4e05-803b-05d4ffdbfeaa','AG.AGR.TRAC.NO','Agricultural machinery refers to the number of wheel and crawler tractors (excluding garden tractors) in use in agriculture at the end of the calendar year specified or during the first quarter of the following year.','Agricultural machinery, tractors','Food and Agriculture Organization, electronic files and web site.');


INSERT INTO country_indicator (id,country_name,indicator_code) VALUES
                                                                           ('a1d35dd2-8189-4be8-a426-54d964c69ba8','Greece','AG.AGR.TRAC.NO'),
                                                                           ('80ce21da-f6fe-484e-811b-8d25823267d8','Spain','AG.CON.FERT.PT.ZS');


INSERT INTO metric (id,metric,`year`,country_indicator_id) VALUES
                                                                       ('01e3f292-8f66-41fc-ab54-f83c5f34d7e6',0.1,1974,'a1d35dd2-8189-4be8-a426-54d964c69ba8'),
                                                                       ('133541ec-527f-4c9d-aecc-44126bdf8b69',0.4,1972,'a1d35dd2-8189-4be8-a426-54d964c69ba8'),
                                                                       ('1846d407-0f5d-4677-954b-75b0c3a44eb8',0.2,1973,'a1d35dd2-8189-4be8-a426-54d964c69ba8'),
                                                                       ('2f6814ae-a8bc-4236-8be5-95179911e689',0.5,2000,'80ce21da-f6fe-484e-811b-8d25823267d8'),
                                                                       ('b3788f74-bc4f-44e6-9dee-c636dafb115e',0.123,1970,'a1d35dd2-8189-4be8-a426-54d964c69ba8'),
                                                                       ('be0ff75e-5096-48e9-ab57-b92194a57a5d',1.2,2001,'80ce21da-f6fe-484e-811b-8d25823267d8'),
                                                                       ('d7662f5a-1bfd-4c64-b8f3-225052eec23e',0.2,1971,'a1d35dd2-8189-4be8-a426-54d964c69ba8'),
                                                                       ('ee40897e-6ce4-48b7-b911-9e4ef60d6933',0.3,1975,'a1d35dd2-8189-4be8-a426-54d964c69ba8');

