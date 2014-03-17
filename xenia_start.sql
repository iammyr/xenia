create database xenia;
use xenia;

create table roles (name varchar(10) not null primary key);

create table forbids (id int(10) auto_increment primary key, role varchar(10), forbmethod varchar(4), forbresource varchar(10), foreign key(role) references roles(name));

create table users (username varchar(20) not null primary key, password blob, role varchar(10), first varchar(20), last varchar(30), gender varchar(1), email varchar(20), homepage varchar(20), weblog varchar(20), uri varchar(20), foreign key(role) references roles(name));

create user 'myrdebby'@'localhost' identified by 'myrdebby';

grant all privileges on xenia.* to 'myrdebby'@'localhost' with grant option;



