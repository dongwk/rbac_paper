/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.6.26-enterprise-commercial-advanced : Database - rbac_paper
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rbac_paper` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rbac_paper`;

/*Table structure for table `app_authority` */

DROP TABLE IF EXISTS `app_authority`;

CREATE TABLE `app_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` varchar(32) DEFAULT NULL COMMENT '权限类型',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Table structure for table `app_authority_element` */

DROP TABLE IF EXISTS `app_authority_element`;

CREATE TABLE `app_authority_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限表ID',
  `element` int(11) DEFAULT NULL COMMENT '页面元素表ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表与页面元素关联表';

/*Table structure for table `app_authority_function` */

DROP TABLE IF EXISTS `app_authority_function`;

CREATE TABLE `app_authority_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限表ID',
  `function_id` int(11) DEFAULT NULL COMMENT '功能操作表ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限与功能操作关联表';

/*Table structure for table `app_authority_menu` */

DROP TABLE IF EXISTS `app_authority_menu`;

CREATE TABLE `app_authority_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限与菜单关联表';

/*Table structure for table `app_element` */

DROP TABLE IF EXISTS `app_element`;

CREATE TABLE `app_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(32) DEFAULT NULL COMMENT '元素编码',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面元素表';

/*Table structure for table `app_function` */

DROP TABLE IF EXISTS `app_function`;

CREATE TABLE `app_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) DEFAULT NULL COMMENT '功能名称',
  `code` varchar(32) DEFAULT NULL COMMENT '功能编码',
  `url` varchar(128) DEFAULT NULL COMMENT '功能URL前缀',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能表';

/*Table structure for table `app_menu` */

DROP TABLE IF EXISTS `app_menu`;

CREATE TABLE `app_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(128) DEFAULT NULL COMMENT '菜单URL',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Table structure for table `app_role` */

DROP TABLE IF EXISTS `app_role`;

CREATE TABLE `app_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) DEFAULT NULL COMMENT '角色名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Table structure for table `app_role_authority` */

DROP TABLE IF EXISTS `app_role_authority`;

CREATE TABLE `app_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `app_user` */

DROP TABLE IF EXISTS `app_user`;

CREATE TABLE `app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Table structure for table `app_user_group` */

DROP TABLE IF EXISTS `app_user_group`;

CREATE TABLE `app_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) DEFAULT NULL COMMENT '用户组名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父用户组',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组';

/*Table structure for table `app_user_group_role` */

DROP TABLE IF EXISTS `app_user_group_role`;

CREATE TABLE `app_user_group_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_group_id` int(11) DEFAULT NULL COMMENT '用户组ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组与角色关联表';

/*Table structure for table `app_user_group_user` */

DROP TABLE IF EXISTS `app_user_group_user`;

CREATE TABLE `app_user_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_group_id` int(11) DEFAULT NULL COMMENT '用户组ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组与用户关联表';

/*Table structure for table `app_user_role` */

DROP TABLE IF EXISTS `app_user_role`;

CREATE TABLE `app_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户表ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
