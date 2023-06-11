--Таблица семестровых оценок
create table if not exists semester_marks (
    id uuid,
    type varchar(50) not null,
    subject varchar(200) not null,
    theme varchar(500),
    mark int not null default 0,
    constraint semester_marks_pk primary key (id)
);
