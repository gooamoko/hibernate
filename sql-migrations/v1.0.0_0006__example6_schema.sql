create table if not exists workers (
    id uuid,
    first_name varchar(100) not null,
    middle_name varchar(100) not null,
    last_name varchar(100) not null,
    birth_date timestamp not null,
    salary int not null default 10,
    constraint workers_pk primary key (id)
);
