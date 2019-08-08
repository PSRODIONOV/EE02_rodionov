create sequence seq_flower;

create table FLOWERS(
id_flower number(10, 0),
name_flower varchar2(30),
price decimal(15,2),
quantity number(10,0),
primary key (id_flower),
unique (name_flower)
);

insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'ruby rose', 30, 40);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'African marigold', 35, 45);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'begonia', 40, 20);
insert into "FLOWERS"(id_flower, name_flower, price, quantity) values (seq_flower.nextval, 'aster', 60, 100);