create table members (
  id varchar(20) primary key,
  password varchar(30) not null,
  name varchar(20) not null,
  email varchar(30) not null,
  phone varchar(20)
 )
   
 insert into members values('guest', 'bemyguest', '�մ�', 'guest@yju.ac.kr', null); 