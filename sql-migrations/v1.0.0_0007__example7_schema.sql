create table if not exists results (
    msg_id uuid not null,
    person_id uuid not null,
    operation varchar(100) not null,
    msg_date timestamp not null,
    amount decimal(9,2) not null,
    constraint results_pk primary key (msg_id, person_id, operation)
);
