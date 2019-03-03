drop table if exists mt_devoice_conninfo;
create table mt_devoice_conninfo
(
  id int not null auto_increment primary key ,
  device_id varchar(255) ,
  ip varchar(255) ,
  port int ,
  create_time datetime
) engine=innodb default charset=utf8;