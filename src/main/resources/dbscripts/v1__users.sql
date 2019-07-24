create sequence seq_user;
create sequence seq_flower;
create sequence seq_order;

create table USERS(
role varchar2 check(role in ('ADMIN', 'USER')),
id_user number(10,0),
login varchar2,
password varchar2,
address varchar2,
phone varchar2,
last_name varchar2,
first_name varchar2,
second_name varchar2,
wallet_score decimal(15,2),
discount number(3, 0),
primary key (id_user),
unique(login)
);
ALTER TABLE USERS ALTER COLUMN ROLE SET DEFAULT 'USER';

insert into "USERS"(id_user, role, login, password, address, wallet_score, discount) values (seq_user.nextval, 'ADMIN','admin', '1234', 'mypochta@mail.ru', 10000, 30);

create table FLOWERS(
id_flower number(10, 0),
name_flower varchar2,
price decimal(15,2),
quantity number(10,0),
primary key (id_flower),
unique (name_flower)
);

insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (1, 'ruby rose', 30, 40);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (2, 'African marigold', 35, 45);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (3, 'begonia', 40, 20);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (4, 'aster', 60, 100);

create table ORDERS(
id_order number(10, 0),
id_user number(10, 0),
total_price decimal(15,2),
status varchar2 check(status in ('CREATED', 'PAID', 'CLOSED')),
date_create date,
date_close date,
primary key (id_order),
foreign key (id_user) references users(id_user)
);
ALTER TABLE ORDERS ALTER COLUMN STATUS SET DEFAULT 'CREATED';

create table ORDERPOSITION(
id_order number(10, 0),
id_flower number(10, 0),
quantity number(3, 0),
primary key (id_order, id_flower),
foreign key (id_flower) references flowers (id_flower),
foreign key (id_order) references orders (id_order)
);
