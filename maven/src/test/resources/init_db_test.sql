create database eroad_test default character set utf8;
use eroad_test;

# 用户表
create table user
(
	id int unsigned auto_increment,
	username varchar(50) unique not null comment '用户名',
	passwd varchar(50) not null comment '密码',

	primary key (id)
);