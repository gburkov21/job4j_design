insert into roles(name) values ('programmer');
insert into roles(name) values ('QA');

insert into users(name, role_id) values ('Igor', 1);
insert into users(name, role_id) values ('Victor', 1);
insert into users(name, role_id) values ('Michail', 2);

insert into rules(name) values ('create');
insert into rules(name) values ('read');
insert into rules(name) values ('update');
insert into rules(name) values ('delete');

insert into role_rules(role_id, rule_id) values (1, 1);
insert into role_rules(role_id, rule_id) values (1, 2);
insert into role_rules(role_id, rule_id) values (1, 3);
insert into role_rules(role_id, rule_id) values (1, 4);
insert into role_rules(role_id, rule_id) values (2, 2);

insert into category(name) values ('bug');
insert into category(name) values ('feature');

insert into state(name) values ('open');
insert into state(name) values ('in progress');
insert into state(name) values ('to verify');
insert into state(name) values ('done');

insert into item(name, user_id, category_id, state_id) values ('create product page', 1, 2, 1);
insert into item(name, user_id, category_id, state_id) values ('fix sql script', 2, 1, 2);
insert into item(name, user_id, category_id, state_id) values ('test product calculation', 3, 2, 3);

insert into comments(name, item_id) values ('create page, button and table in frontend', 1);
insert into comments(name, item_id) values ('try optimize after fix', 2);
insert into comments(name, item_id) values ('create a big calculation and check', 3);

insert into attachs(name, item_id) values ('technical task.doc', 1);
insert into attachs(name, item_id) values ('bad_script.sql', 2);
insert into attachs(name, item_id) values ('calculation description.doc', 3);