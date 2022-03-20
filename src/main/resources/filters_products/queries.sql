select p.*
from product p
         join type t on p.type_id = t.id
where t.name = 'СЫР';

select *
from product p
where p.name like 'Мороженое%';

select *
from product p
where p.expired_date < current_date;

select p.*
from product p
where p.price = (select max(price) from product);

select t.name as имя_типа, count(*) as Количество
from type t
         join product p on t.id = p.type_id
group by t.name;

select *
from product p
         join type t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

select t.name as имя_типа, count(*) as Количество
from product p
         join type t on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.name, t.name
from product p
         join type t on t.id = p.type_id;