create table replies (
	 rno int primary key auto_increment , 
	 bno int references boards(bno) on delete cascade, 
	 content varchar(2000) not null,
	 replyer varchar(30) not null, 
	 regdate timestamp default now(), 
	 moddate timestamp default now()
)

