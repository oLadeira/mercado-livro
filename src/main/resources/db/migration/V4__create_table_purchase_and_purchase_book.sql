create table tb_purchase(
	id bigint auto_increment not null,
    customer_id bigint not null,
    nfe varchar(255),
    price decimal(10,2) not null,
    created_at datetime not null,
    primary key(id),
    foreign key (customer_id) references tb_customer(id)
);

create table tb_purchase_book(
	purchase_id bigint not null,
    book_id bigint not null,
    foreign key (purchase_id) references tb_purchase(id),
    foreign key (book_id) references tb_book(id),
    primary key (purchase_id, book_id)
);