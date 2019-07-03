create table USERS(
    login varchar2 PRIMARY KEY,
    password varchar2,
    address varchar2,
    phone varchar2,
    last_name varchar2,
    first_name varchar2,
    second_name varchar2,
    wallet_score number(10,2),
    discount number(3,0)
);
insert into "USERS"(login ,password, address) values ('user','1234','mail');

