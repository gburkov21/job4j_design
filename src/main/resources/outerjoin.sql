create database departments_emploers_db;

create table departments
(
    id   serial primary key,
    name varchar(255)
);

create table emploers
(
    id            serial primary key,
    name          varchar(255),
    department_id int references departments (id)
);

create table teens
(
    id     serial primary key,
    name   varchar(255),
    gender varchar(255)
);

insert into departments(name)
values ('Отдел разработок'), ('Отдел тестирования'), ('Аналитики'), ('Бухгалтерия'), ('Хозяйственный отдел'), ('Отдел продаж');

insert into emploers(name, department_id)
values ('Иван', 1), ('Саша', 1), ('Петя', 2), ('Виктор', 2), ('Катя', 3), ('Маша', 3), ('Вера', 4), ('Наташа', 4);

insert into teens(name, gender)
values ('Иван', 'Male'), ('Саша', 'Male'), ('Петя', 'Male'), ('Виктор', 'Male'), ('Катя', 'Female'),
('Маша', 'Female'), ('Вера', 'Female'), ('Наташа', 'Female');

select *
from departments d
         left join emploers e on d.id = e.department_id
where e.department_id is null;

select d.id, d.name, e.id, e.name, e.department_id
from departments d
         left join emploers e on d.id = e.department_id;
select d.id, d.name, e.id, e.name, e.department_id
from emploers e
         right join departments d on e.department_id = d.id;

select t1.name, t2.name
from teens t1
         cross join teens t2
where t1.gender != t2.gender;
