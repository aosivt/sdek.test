#!/bin/sh -e

psql --variable=ON_ERROR_STOP=1 --username "postgres" <<-EOSQL
    CREATE ROLE suppluers WITH LOGIN PASSWORD 'suppluer';
    GRANT ALL PRIVILEGES ON DATABASE "postgres" TO suppluers;
    CREATE TABLE public.orders (
	id bigserial NOT NULL,
	order_num varchar(255) NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
    );
    CREATE TABLE public.canceled_orders (
	id bigserial NOT NULL,
	id_order bigserial NOT NULL,
	cancel_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT canceled_orders_pkey PRIMARY KEY (id)
    );

    INSERT INTO public.orders (order_num) VALUES('test1');
    INSERT INTO public.orders (order_num) VALUES('test2');
    INSERT INTO public.orders (order_num) VALUES('test3');
    INSERT INTO public.orders (order_num) VALUES('test4');
    INSERT INTO public.orders (order_num) VALUES('test5');

    INSERT INTO public.canceled_orders (id_order) VALUES(1);
    INSERT INTO public.canceled_orders (id_order) VALUES(2);
    INSERT INTO public.canceled_orders (id_order) VALUES(3);

EOSQL