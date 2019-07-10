create sequence seq_user;
create sequence seq_flower;
create sequence seq_order;

create table USERS(
id_user number(10,0),
login varchar2,
password varchar2,
address varchar2,
phone varchar2,
last_name varchar2,
first_name varchar2,
second_name varchar2,
wallet_score number(10, 2),
discount number(3, 0),
primary key (id_user),
unique(login)
);

insert into "USERS"(id_user, login, password, address) values (seq_user.nextval, 'user', '1234', 'mypochta@mail.ru');

create table FLOWERS(
id_flower number(10, 0),
name_flower varchar2,
price number(10, 0),
quantity number(10,0),
primary key (id_flower),
unique (name_flower)
);

insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'rose', 100, 56);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'aster', 100, 56);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'beagonia', 159, 15);

create table ORDERS(
id_order number(10, 0),
id_user number(10, 0),
total_price number(10,0),
primary key (id_order),
foreign key (id_user) references users(id_user)
);

insert into "ORDERS" values(seq_order.nextval, 1, null);
insert into "ORDERS" values(seq_order.nextval, 1, null);

create table ORDERPOSITION(
id_order number(10, 0),
id_flower number(10, 0),
quantity number(3, 0),
primary key (id_order, id_flower),
foreign key (id_flower) references flowers (id_flower),
foreign key (id_order) references orders (id_order)
);

insert into "ORDERPOSITION" values (1, 1, 10);
insert into "ORDERPOSITION" values (1, 2, 3);
insert into "ORDERPOSITION" values (2, 3, 100);