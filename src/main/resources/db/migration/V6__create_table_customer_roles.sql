create table tb_customer_roles(
	customer_id bigint not null,
    role varchar(50) not null,
    foreign key (customer_id) references tb_customer(id)
);