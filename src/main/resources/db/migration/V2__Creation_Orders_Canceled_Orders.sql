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
