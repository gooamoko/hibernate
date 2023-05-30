--Производители оборудования
create table if not exists VENDORS (
    ID bigserial,
    NAME varchar(100) not null,
    SITE varchar(200),
    constraint VENDORS_PK primary key (ID)
);

-- Устройство
create table if not exists DEVICES (
    ID bigserial,
    NAME varchar(100) not null,
    DESCRIPTION varchar(200),
    VENDOR_ID bigint not null,
    MODEL varchar(100) not null,
    DAMAGED boolean not null default false,
    INVENTORY_NUMBER varchar(100) not null,
    constraint DEVICES_PK primary key (ID),
    constraint DEVICES_VENDORS_FK foreign key (VENDOR_ID) references VENDORS(ID) on update cascade on delete no action
)