drop table T_STORE if exists;

create table T_STORE (STORE_ID int identity primary key, ADDRESS varchar(500) not null,
LATITUDE double, LONGITUDE double);
