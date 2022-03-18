create database car_db;

create table body
(
    id   serial primary key,
    name varchar(255)
);
create table engine
(
    id   serial primary key,
    name varchar(255)
);
create table gear
(
    id   serial primary key,
    name varchar(255)
);

create table car
(
    id        serial primary key,
    body_id   int references body (id) unique,
    engine_id int references engine (id) unique,
    gear_id   int references gear (id) unique
);

insert into body(name)
values ('Coupe');
insert into body(name)
values ('Hatchback');
insert into body(name)
values ('Pickup');
insert into body(name)
values ('Sedan');

insert into engine(name)
values ('Gas');
insert into engine(name)
values ('Diesel');
insert into engine(name)
values ('Electrical');
insert into engine(name)
values ('Hybrid');

insert into gear(name)
values ('Hand');
insert into gear(name)
values ('Automatic');
insert into gear(name)
values ('Robot');
insert into gear(name)
values ('Preselective');

insert into car(body_id, engine_id, gear_id)
values (1, 1, 1);
insert into car(body_id, engine_id, gear_id)
values (2, 2, 2);
insert into car(body_id, engine_id, gear_id)
values (3, 3, 3);

select b.name, e.name, g.name
from car c
         left join body b on c.body_id = b.id
         left join engine e on c.engine_id = e.id
         left join gear g on c.gear_id = g.id;

select b.name
from body b
         left join car c on c.body_id = b.id
where c.body_id is null;
select e.name
from engine e
         left join car c on e.id = c.engine_id
where c.engine_id is null;
select g.name
from gear g
         left join car c on g.id = c.gear_id
where c.gear_id is null;