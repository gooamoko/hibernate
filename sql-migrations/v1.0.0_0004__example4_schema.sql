--Базовая таблица
create table if not exists vehicle (
    id uuid,
    vin_code varchar(50) not null,
    constraint vehicle_pk primary key (id)
);

--Автобус
create table if not exists bus (
    id uuid,
    vin_code varchar(50) not null,
    sits_count int not null,
    constraint bus_pk primary key (id)
);

--Грузовик
create table if not exists truck (
    id uuid,
    vin_code varchar(50) not null,
    max_weight int not null,
    constraint truck_pk primary key (id)
);

