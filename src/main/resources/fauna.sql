create database fauna_db;

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('big fish', 11000, '01.01.1765');
insert into fauna(name, avg_age, discovery_date) values ('cat', 30000, null);
insert into fauna(name, avg_age, discovery_date) values ('dog', 50000, null);
insert into fauna(name, avg_age, discovery_date) values ('strange animal', 50000, '05.05.1900');

select * from fauna where name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';