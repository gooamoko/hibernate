--Персоны
create table if not exists HUMANS (
    ID bigserial,
    FIRST_NAME varchar(100) not null,
    MIDDLE_NAME varchar(100) not null,
    LAST_NAME varchar(100) not null,
    BIRTH_DATE date not null,
    PASSPORT_SERIA varchar(4) not null,
    PASSPORT_NUMBER varchar(6) not null,
    PHONE varchar(10) not null,
    ADDRESS varchar(500) not null,
    constraint HUMANS_PK primary key (ID)
);

--Преподаватели
create table if not exists TEACHERS (
    ID bigint not null,
    CATEGORY varchar(100) not null,
    WORK_BEGIN date not null,
    constraint TEACHERS_PK primary key (ID),
    constraint TEACHERS_HUMANS_FK foreign key (ID) references HUMANS(ID) on update cascade on delete no action
);

--Студенты
create table if not exists STUDENTS (
    ID bigint not null,
    DELEGATE_ID bigint not null,
    constraint STUDENTS_PK primary key (ID),
    constraint STUDENTS_HUMANS_FK foreign key (ID) references HUMANS(ID) on update cascade on delete no action,
    constraint STUDENTS_DELEGATE_FK foreign key (DELEGATE_ID) references HUMANS(ID) on update cascade on delete no action
);