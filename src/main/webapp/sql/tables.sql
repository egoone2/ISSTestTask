create table Area
(
    id         int DEFAULT nextval('serial'),
    name       varchar(100) not null,
    code       int,
    is_archive boolean,
    primary key (id)
);

create table Farmer
(
    id                   int DEFAULT nextval('serial'),
    name                 varchar not null,
    opf                  varchar(5),
    inn                  varchar(12),
    kpp                  varchar(10),
    ogrn                 varchar(13),
    registration_area_id int,
    registration_date    date,
    is_archive           boolean,

    primary key (id),

    constraint fk_area
        foreign key (registration_area_id)
            references Area (id)

);