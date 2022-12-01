drop table cartao;

create table cartao(
id int auto_increment,
numero_cartao varchar(16) not null,
senha varchar(4) not null,
saldo decimal(19,2) not null default 500,
primary key(id)
);