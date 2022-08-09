
drop database if exists repair_agency;
create database repair_agency;
use repair_agency;

create table users(
	id int auto_increment,
    login varchar(20),
    passwrd varchar(20),
    acc_type varchar(20),
	primary key(id)
);



create table craftsmen(
	id int,
    name varchar(20),
	foreign key(id) references users(id) on delete cascade
);

create table orders(
	id int auto_increment,
    user_id int,
    craftsman_id int,
    order_status varchar(30),
    payment_status varchar(30),
    primary key(id),
    foreign key(user_id) references users(id) on delete cascade,
    foreign key(craftsman_id) references craftsmen(id) on delete cascade
);

insert into users values(0,'admin','admin','admin');
insert into users values(0,'placeholder','placeholder','craftsman');
insert into craftsmen values (2,'placeholder');
select * from users;
select * from craftsmen;
select * from orders;