create table flowers(
    id_flower number(10, 0),
    name_flower varchar2,
    price number(10, 0),
    quantity number(10, 0),
 primary key(id_flower),
unique(name_flower)
);
insert into flowers(id_flower, name_flower, price, quantity) values(FLOWER_SEQ.NEXTVAL, 'ROSE', 100, 56);
insert into flowers(id_flower, name_flower, price, quantity) values(FLOWER_SEQ.NEXTVAL, 'begemot', 100, 56);