create table roles(
	id serial primary key,
	name varchar(255)
);

create table users(
    id serial primary key,
    name varchar(255),
    role_id int REFERENCES roles(id)
);

create table rules(
    id serial primary key,
    name varchar(255)
);

create table role_rules(
    role_id int REFERENCES roles(id),
    rule_id int REFERENCES rules(id)
);

create table category(
    id serial primary key,
    name varchar(255)
);

create table state(
    id serial primary key,
    name varchar(255)
);

create table item(
    id serial primary key,
    name varchar(255),
    user_id int REFERENCES users(id),
	category_id int REFERENCES category(id),
	state_id int REFERENCES state(id)
);

create table comments(
    id serial primary key,
    name varchar(255),
    item_id int REFERENCES item(id)
);

create table attachs(
    id serial primary key,
    name varchar(255),
    item_id int REFERENCES item(id)
);