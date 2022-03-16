create table hobbies(
    id serial primary key,
    name varchar(255)
);

create table person(
    id serial primary key,
    name varchar(255)
);

create table hobbies_person(
     id serial primary key,
     hobby_id int references hobbies(id),
     person_id int references person(id)
);

insert into hobbies(name) values ('fishing');
insert into hobbies(name) values ('hunting');
insert into hobbies(name) values ('drawing');

insert into person(name) values ('Ivan');
insert into person(name) values ('Petr');
insert into person(name) values ('Alex');

insert into hobbies_person(hobby_id, person_id) values (1, 1);
insert into hobbies_person(hobby_id, person_id) values (1, 2);
insert into hobbies_person(hobby_id, person_id) values (1, 3);
insert into hobbies_person(hobby_id, person_id) values (2, 1);
insert into hobbies_person(hobby_id, person_id) values (2, 2);
insert into hobbies_person(hobby_id, person_id) values (3, 3);