--liquibase formatted sql

--changeset lihe:spbt-1.1.1
CREATE TABLE user_test_table (
  usercode int(5) NOT NULL COMMENT '编号',
  password varchar(50) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (usercode)
) DEFAULT CHARSET=utf8 COMMENT='测试表';

--changeset lihe:spbt-1.1.2
insert into user_test_table (usercode, password) values (1,'p1');
insert into user_test_table (usercode, password) values (2,'p2');
insert into user_test_table (usercode, password) values (3,'p3');
insert into user_test_table (usercode, password) values (4,'p4');
insert into user_test_table (usercode, password) values (5,'p5');