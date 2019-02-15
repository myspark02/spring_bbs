create table attachments (
  id int auto_increment primary key,
  bno int references boards(bno) on delete cascade, 
  filename varchar(50),
  mime varchar(20),
  bytes int,
  createdAt datetime default now()
 ) 
  