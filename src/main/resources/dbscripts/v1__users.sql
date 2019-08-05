create sequence seq_user;

create table USERS(
    role varchar2 check(role in ('ADMIN', 'USER')),
    id_user number(10,0),
    login varchar2(100),
    password varchar2(20),
    address varchar2(100),
    phone varchar2(11),
    last_name varchar2(30),
    first_name varchar2(30),
    second_name varchar2(30),
    wallet_score decimal(15,2),
    discount number(3, 0),
primary key (id_user),
unique(login)
);
ALTER TABLE USERS ALTER COLUMN ROLE SET DEFAULT 'USER';

insert into "USERS"(id_user, role, login, password, address, wallet_score, discount) values (seq_user.nextval, 'ADMIN','admin', '1234', 'mypochta@mail.ru', 10000, 30);

