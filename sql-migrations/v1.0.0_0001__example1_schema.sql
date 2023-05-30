--Специальности
create table if not exists SPECIALITIES (
    SPC_ID bigserial,
    SPC_NAME varchar(255) not null,
    SPC_CODE varchar(50) not null,
    SPC_CREATE_TS timestamp not null default current_timestamp(),
    constraint SPECIALITIES_PK primary key (SPC_ID)
);

--Группы
create table if not exists GROUPS (
    GRP_ID bigserial,
    GRP_NAME varchar(50) not null,
    GRP_SPC_ID bigint not null,
    GRP_CREATE_TS timestamp not null default current_timestamp(),
    constraint GROUPS_PK primary key (GRP_ID),
    constraint GROUPS_SPECIALITIES_FK foreign key (GRP_SPC_ID) references SPECIALITIES (SPC_ID) ON UPDATE CASCADE ON DELETE CASCADE
);

--Семестры
create table if not exists SEMESTERS (
    SEM_ID bigserial,
    SEM_YEAR integer not null,
    SEM_NUMBER integer not null,
    SEM_GRP_ID bigint not null,
    SEM_CREATE_TS timestamp not null default current_timestamp(),
    constraint SEMESTERS_PK primary key (SEM_ID),
    constraint SEMESTERS_GROUPS_FK foreign key (SEM_GRP_ID) references GROUPS (GRP_ID) ON UPDATE CASCADE ON DELETE CASCADE
);
