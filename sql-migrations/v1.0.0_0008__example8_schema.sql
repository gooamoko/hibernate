create table if not exists output_files (
    id uuid not null,
    persons_count bigint not null,
    file_name varchar(255) not null,
    status varchar(100) not null,
    create_date timestamp not null default current_timestamp(),
    constraint output_files_pk primary key (id)
);
