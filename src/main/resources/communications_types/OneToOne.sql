create table address(
    id serial primary key,
    city varchar(255),
	street varchar(255),
	house int,
	apartment int
);

create table worker(
    id serial primary key,
    name varchar(255),
);

create table address_worker(
    id serial primary key,
    address_id int references address(id) unique,
    worker_id int references worker(id) unique
);

insert into address(city, street, house, apartment) values ('Moskow', 'Pushkina', 10, 20);
insert into address(city, street, house, apartment) values ('Kiev', 'Ivanova', 30, 40);
insert into address(city, street, house, apartment) values ('Minsk', 'Petrova', 50, 60);

insert into worker(name) values ('Ivan');
insert into worker(name) values ('Igor');
insert into worker(name) values ('Petr');

insert into address_worker(address_id, worker_id) values (1, 1);
insert into address_worker(address_id, worker_id) values (2, 2);
insert into address_worker(address_id, worker_id) values (3, 3);