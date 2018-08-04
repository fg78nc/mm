drop table if exists purchase_order;

create table purchase_order (
  order_id bigint not null auto_increment,
  customer_id bigint not null,
  item varchar(255),
  order_date date,
  primary key (order_id)
) engine=InnoDB

