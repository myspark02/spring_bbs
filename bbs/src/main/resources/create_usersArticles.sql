create table usersArticles (
	userId varchar(20) references members(id) on delete cascade, 
	bno int references boards(bno) on delete cascade,
	readDate datetime,
	primary key(userId, bno) 
)