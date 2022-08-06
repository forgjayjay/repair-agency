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
    login varchar(20),
	foreign key(id) references users(id)
);

create table orders(
	id int auto_increment,
    user_id int,
    craftsman_id int,
    order_status varchar(30),
    payment_status varchar(30),
    primary key(id),
    foreign key(user_id) references users(id),
    foreign key(craftsman_id) references craftsmen(id)
);

insert into users values(0,'admin','admin','admin');

select * from users;
select * from craftsmen;
select * from orders;