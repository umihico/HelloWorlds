sqlite3 database.sqlite
create table books(id integer, title string);
.tables
insert into books values(1,'やさしい数学');
insert into books values(2,'英文');
insert into books values(3,'花火');
select id,title from books
select * from books;
