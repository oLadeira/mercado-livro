create table tb_book(
	id bigint auto_increment not null,
    name varchar(255) not null,
    price decimal(10,2) not null,
    status varchar(255) not null,
    customer_id bigint not null,
    primary key(id),
    foreign key (customer_id) references tb_customer(id)
);