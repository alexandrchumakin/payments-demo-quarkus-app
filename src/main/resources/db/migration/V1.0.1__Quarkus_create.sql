CREATE SEQUENCE paymentrecord_seq;
CREATE TABLE paymentrecord (
   id UUID PRIMARY KEY,
   amount numeric(10, 2),
   currency varchar,
   name varchar
);

CREATE SEQUENCE userrecord_seq;
CREATE TABLE userrecord (
   id serial PRIMARY KEY,
   name varchar,
   password varchar
);
