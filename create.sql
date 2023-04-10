DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `password` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `description` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

DROP TABLE IF EXISTS `tb_right`;
CREATE TABLE `tb_right` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `name` varchar(100) DEFAULT NULL,
     `description` varchar(100) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

DROP TABLE IF EXISTS `tb_user_role_relation`;
CREATE TABLE `tb_user_role_relation` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `user_id` bigint(20) DEFAULT NULL,
     `role_id` bigint(20) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

DROP TABLE IF EXISTS `tb_role_right_relation`;
CREATE TABLE `tb_role_right_relation` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `role_id` bigint(20) DEFAULT NULL,
     `right_id` bigint(20) DEFAULT NULL,
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单表';

ALTER TABLE tb_user add unique(name);
ALTER TABLE tb_right add unique(name);
ALTER TABLE tb_role add unique(name);
ALTER TABLE tb_user_role_relation add unique(user_id,role_id);
ALTER TABLE tb_role_right_relation add unique(role_id,right_id);

insert into tb_user values(1,"张三","123");
insert into tb_user values(2,"李四","456");
insert into tb_user values(3,"刘五","ffea");

insert into tb_role values(1,"管理员","管理");
insert into tb_role values(2,"老师","中间角色");
insert into tb_role values(3,"用户","普通用户");

insert into tb_right values(1,"后台","查看后台");
insert into tb_right values(2,"统计","查看统计");
insert into tb_right values(3,"普通操作","普通");

insert into tb_user_role_relation values (1,1,1);
insert into tb_user_role_relation values (2,1,2);
insert into tb_user_role_relation values (3,1,3);
insert into tb_user_role_relation values (4,2,2);
insert into tb_user_role_relation values (5,2,3);
insert into tb_user_role_relation values (6,3,3);

insert into tb_role_right_relation values (1,1,1);
insert into tb_role_right_relation values (2,1,2);
insert into tb_role_right_relation values (3,1,3);
insert into tb_role_right_relation values (4,2,2);
insert into tb_role_right_relation values (5,2,3);
insert into tb_role_right_relation values (6,3,3);