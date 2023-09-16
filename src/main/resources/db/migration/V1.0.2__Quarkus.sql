insert into userrecord (id, name, password) values(1, 'admin', 'admin');
insert into userrecord (id, name, password) values(2, 'user', 'user');
insert into userrecord (id, name, password) values(3, 'john', 'john');
alter sequence userrecord_seq restart with 4;
