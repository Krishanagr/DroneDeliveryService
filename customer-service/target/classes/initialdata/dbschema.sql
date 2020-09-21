
drop table T_CUSTOMER if exists;

create table T_CUSTOMER (CUSTOMER_ID int identity primary key,NAME varchar(50) not null, ADDRESS varchar(500) not null,
LATITUDE double, LONGITUDE double);