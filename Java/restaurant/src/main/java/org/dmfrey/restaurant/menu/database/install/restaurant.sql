create table restaurant(
	id identity,
	name varchar(128) not null,
	primary key(id)
);

create table menu(
	id identity,
	name varchar(128) not null,
	restaurant bigint,
	primary key(id),
	foreign key (restaurant) references restaurant (id)
);

create table section(
	id identity,
	name varchar(128) not null,
	menu bigint,
	primary key(id),
	foreign key (menu) references menu (id)
);

create table menu_item(
	id identity,
	name varchar(128) not null,
	description varchar(512),
	price double,
	section bigint,
	primary key(id),
	foreign key (section) references section (id)
);
