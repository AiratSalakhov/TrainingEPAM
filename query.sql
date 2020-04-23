--Поиск products по title
select * from products p
where title like '%CRYSTAL%';

--Поиск products по price = 9.99 и category = 8 и отсортировать по category и price
select * from products p
where price = 9.99 and category = 8
order by category, price;

--Поиск products у которых category = 8 или 15
select * from products p
where category = 8 or category = 15;

--Поиск products у которых price между 10 и 20 (используйте BETWEEN)
select * from products p
where price between 10 and 20;

--Поиск orders у которых orderdate между 2004-01-05 и 2004-02-05
select * from orders o 
where orderdate between date '2004-01-05' and date '2004-02-05';

--Сгруппировать данные в orders по полю customerid и посчитать количество относительно каждого customerid
select customerid, count(*) from orders o
group by customerid;

--Сгруппировать данные в orders по полям customerid и orderdate и просуммировать totalamount, при этом сумма totalamount должна быть больше 100
select customerid, orderdate, sum(totalamount) 
from orders o
group by customerid, orderdate having sum(totalamount) > 100; 

--Написать запрос к таблицам customer, orders, orderlines и products с использованием JOIN и вывести firstname, lastname, title, orderdate
select c.firstname, c.lastname, p.title, o.orderdate 
from customers c
join orders o on c.customerid = o.customerid
join orderlines ol on o.orderid = ol.orderid 
join products p on ol.prod_id = p.prod_id;
