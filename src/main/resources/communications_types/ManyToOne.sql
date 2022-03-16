create table roles(
    id serial primary key,
    name varchar(255)
);

create table pc_users(
    id serial primary key,
    name varchar(255),
	role_id references roles(id)
);

insert into roles(name) values ('admin');
insert into roles(name) values ('user');

insert into pc_users(name, role_id) values ('Ivan', 1);
insert into pc_users(name, role_id) values ('Petr', 1);
insert into pc_users(name, role_id) values ('Alex', 2);