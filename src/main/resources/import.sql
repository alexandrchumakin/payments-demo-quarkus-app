-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into userrecord (id, name, password) values(1, 'admin', 'admin');
insert into userrecord (id, name, password) values(2, 'user', 'user');
insert into userrecord (id, name, password) values(3, 'john', 'john');
alter sequence userrecord_seq restart with 4;
