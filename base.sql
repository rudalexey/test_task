create table if not exists courier
(
	id uuid not null
		constraint courier_pkey
			primary key,
	name varchar(50) not null
		constraint courier_name_key
			unique
);

alter table courier owner to test;

create table if not exists client
(
	id uuid not null
		constraint client_pk
			primary key,
	name varchar(50) not null,
	phone_number varchar(50),
	address varchar(255)
);

alter table client owner to test;

create table if not exists task
(
	id serial not null
		constraint task_pk
			primary key,
	client_id uuid not null
		constraint task_fk
			references client,
	courier_id uuid
		constraint task_fk1
			references courier,
	shipment timestamp,
	receiving timestamp,
	who_created varchar(50) not null,
	who_last_update varchar(50),
	date_created timestamp default now() not null,
	date_update timestamp
);

alter table task owner to test;

