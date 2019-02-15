create table boards (
	bno int auto_increment primary key,
	title varchar(180),
	writer varchar(30) default 'guest',
	content text, 
	readcount int default 0,
	regdate datetime default now(),
	moddate datetime default now()
)
