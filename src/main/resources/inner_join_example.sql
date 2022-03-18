create database inner_join_test;

create table devices
(
    id   serial primary key,
    name varchar(255)
);

create table users
(
    id        serial primary key,
    name      text,
    device_id int references devices (id)
);

insert into devices(name) values ('iphone');
insert into devices(name) values ('pc');
insert into devices(name) values ('xbox');

insert into users(name, device_id) values ('Ivan', 1);
insert into users(name, device_id) values ('Petr', 2);
insert into users(name, device_id) values ('Alex', 3);
insert into users(name) values ('Garik');
insert into users(name) values ('John');

select u.name as UserName, d.name as DeviceName
from users u
         join devices d on u.device_id = d.id;

select *
from users u
         join devices d on u.device_id = d.id
where d.name != 'pc';

select u.name as UserName, d.name as DeviceName, u.device_id as DeviceId
from users u
         join devices d on u.device_id = d.id
where d.name = 'xbox';