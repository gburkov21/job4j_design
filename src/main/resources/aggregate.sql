create database aggregate_db;

create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(name, price) values ('iphone', 2000.00);
insert into devices(name, price) values ('playstation', 7000.00);
insert into devices(name, price) values ('pc', 6000.00);
insert into devices(name, price) values ('apple watch', 3000.00);
insert into devices(name, price) values ('xbox', 8000.00);

insert into people(name) values ('Ivan');
insert into people(name) values ('Petr');
insert into people(name) values ('Gleb');
insert into people(name) values ('Victor');
insert into people(name) values ('Alex');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 4);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (2, 5);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (3, 5);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (4, 4);
insert into devices_people(device_id, people_id) values (5, 3);
insert into devices_people(device_id, people_id) values (5, 4);
insert into devices_people(device_id, people_id) values (5, 5);

select avg(price) from devices;

select p.name, avg(d.price)
from devices_people dp
    join devices d on dp.device_id = d.id
    join people p on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price)
from devices_people dp
         join devices d on dp.device_id = d.id
         join people p on dp.people_id = p.id
group by p.name
having avg(d.price) > 5000;
