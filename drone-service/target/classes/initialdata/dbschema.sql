drop table T_DRONE if exists;

create table T_DRONE (DRONE_ID int identity primary key, ADDRESS varchar(500) not null,
LATITUDE double, LONGITUDE double);
