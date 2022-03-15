create database users_db;

create table users (
id serial primary key,
name varchar(255),
age INTEGER,
salary numeric(19,2)
);

insert into users(name, age, salary) values ('Ivan', 30, 50000.00);

update users set salary = 100000.00;

delete from users;