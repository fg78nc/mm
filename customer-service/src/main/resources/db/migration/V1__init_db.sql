create table customer (
  id bigint not null auto_increment,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  primary key (id)
) engine=InnoDB