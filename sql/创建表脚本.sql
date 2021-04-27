/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 5.7.28-log : Database - rbac_paper
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rbac_paper` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `rbac_paper`;

/*Table structure for table `t_activity` */

DROP TABLE IF EXISTS `t_activity`;

CREATE TABLE `t_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(512) COMMENT '问卷标题',
  `description` varchar(2048) COMMENT '问卷描述',
  `activity_max_num` smallint(4) DEFAULT NULL COMMENT '报名最大人数',
  `expired_flag` tinyint(1) DEFAULT NULL COMMENT '是否过期 1 过期 0 不过期',
  `start_date` datetime DEFAULT NULL COMMENT '报名开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '报名结束时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷表';


/*Table structure for table `t_article` */

DROP TABLE IF EXISTS `t_article`;

CREATE TABLE `t_article` (
  `id` int(11) NOT NULL COMMENT 'id',
  `title` varchar(512) COMMENT '文章标题',
  `description` varchar(2048) COMMENT '文章描述',
  `content` longtext COMMENT '文章内容',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否展示 1 展示 0 不展示',
  `page_view` int(11) DEFAULT NULL COMMENT '浏览量',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论次数',
  `head_img_id` int(11) DEFAULT NULL COMMENT '文章头图',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

/*Table structure for table `t_authority` */

DROP TABLE IF EXISTS `t_authority`;

CREATE TABLE `t_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(32) DEFAULT NULL COMMENT '权限类型',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Table structure for table `t_authority_element` */

DROP TABLE IF EXISTS `t_authority_element`;

CREATE TABLE `t_authority_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限表id',
  `element_id` int(11) DEFAULT NULL COMMENT '页面元素表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表与页面元素关联表';

/*Table structure for table `t_authority_function` */

DROP TABLE IF EXISTS `t_authority_function`;

CREATE TABLE `t_authority_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限表id',
  `function_id` int(11) DEFAULT NULL COMMENT '功能操作表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与功能操作关联表';

/*Table structure for table `t_authority_menu` */

DROP TABLE IF EXISTS `t_authority_menu`;

CREATE TABLE `t_authority_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限与菜单关联表';

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '分类名称',
  `description` varchar(128) COMMENT '分类描述',
  `icon` varchar(128) COMMENT '分类图标',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否展示 1 展示 0 不展示',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章分类表';

/*Table structure for table `t_category_article` */

DROP TABLE IF EXISTS `t_category_article`;

CREATE TABLE `t_category_article` (
  `id` int(11) NOT NULL COMMENT 'id',
  `category_id` int(11) DEFAULT NULL COMMENT '分类表id',
  `article_id` int(11) DEFAULT NULL COMMENT '文章表id',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否展示 1 展示 0 不展示',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类文章表';

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL COMMENT 'id',
  `content` varchar(2048) COMMENT '评论内容',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否展示 1 展示 0 不展示',
  `first_comment_id` int(11) DEFAULT NULL COMMENT '回复第一层评论id',
  `reply_comment_id` int(11) DEFAULT NULL COMMENT '回复评论id',
  `is_manager` tinyint(1) DEFAULT NULL COMMENT '是否管理员回复 1 是 0 否',
  `article_id` int(11) DEFAULT NULL COMMENT '文章表id',
  `business_user_id` int(11) DEFAULT NULL COMMENT '业务用户表id',
  `business_user_nickname` varchar(128) DEFAULT NULL COMMENT '业务用户昵称',
  `favor_num` int(11) DEFAULT NULL COMMENT '点赞数',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';

/*Table structure for table `t_comment_favor` */

DROP TABLE IF EXISTS `t_comment_favor`;

CREATE TABLE `t_comment_favor` (
  `id` int(11) NOT NULL COMMENT 'id',
  `comment_id` int(11) DEFAULT NULL COMMENT '评论id',
  `business_user_id` int(11) DEFAULT NULL COMMENT '业务用户表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论点赞表';

/*Table structure for table `t_config_constant` */

DROP TABLE IF EXISTS `t_config_constant`;

CREATE TABLE `t_config_constant` (
  `id` int(11) NOT NULL COMMENT 'id',
  `constant_type` varchar(128) DEFAULT NULL COMMENT '类型',
  `constant_value` varchar(512) COMMENT '值',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='配置表';

/*Table structure for table `t_element` */

DROP TABLE IF EXISTS `t_element`;

CREATE TABLE `t_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(32) DEFAULT NULL COMMENT '元素编码',
  `description` varchar(128) DEFAULT NULL COMMENT '描述',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面元素表';

/*Table structure for table `t_element_function` */

DROP TABLE IF EXISTS `t_element_function`;

CREATE TABLE `t_element_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `element_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `function_id` int(11) DEFAULT NULL COMMENT '功能表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='页面元素表和功能表关联表，暂定为一对一关系';

/*Table structure for table `t_business_user` */

DROP TABLE IF EXISTS `t_business_user`;

CREATE TABLE `t_business_user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(128) DEFAULT NULL COMMENT '名字',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `avatar_img_id` int(11) DEFAULT NULL COMMENT '头像img_id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '加密盐',
  `mobile_phone` varchar(128) DEFAULT NULL COMMENT '联系方式',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `business_user_type` int(11) DEFAULT NULL COMMENT '业务用户类型 1 普通用户 2 管理员',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务用户表';

/*Table structure for table `t_business_user_questionnaire` */

DROP TABLE IF EXISTS `t_business_user_questionnaire`;

CREATE TABLE `t_business_user_questionnaire` (
  `id` int(11) NOT NULL COMMENT 'id',
  `questionnaire_id` int(11) DEFAULT NULL COMMENT '问卷表id',
  `business_user_id` int(11) DEFAULT NULL COMMENT '业务用户表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务用户问卷表';

/*Table structure for table `t_business_user_questionnaire_q` */

DROP TABLE IF EXISTS `t_business_user_questionnaire_q`;

CREATE TABLE `t_business_user_questionnaire_q` (
  `id` int(11) NOT NULL COMMENT 'id',
  `questionnaire_id` int(11) DEFAULT NULL COMMENT '问卷表id',
  `questionnaire_q_id` int(11) DEFAULT NULL COMMENT '问卷详情表id',
  `questionnaire_q_value` varchar(512) COMMENT '问卷详情值，文本类型为内容，单选多选逗号,分隔',
  `business_user_id` int(11) DEFAULT NULL COMMENT '业务用户表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务用户问卷详情表';

/*Table structure for table `t_business_user_message` */

DROP TABLE IF EXISTS `t_business_user_message`;

CREATE TABLE `t_business_user_message` (
  `id` int(11) NOT NULL COMMENT 'id',
  `sender_business_user_id` int(11) DEFAULT NULL COMMENT '发送方 business_user_id',
  `receiver_business_user_id` int(11) DEFAULT NULL COMMENT '接收方 business_user_id',
  `message` varchar(2048) COMMENT '消息内容',
  `is_read` varchar(1) DEFAULT NULL COMMENT '是否已读 0 未读 1 已读',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='站内信';

/*Table structure for table `t_business_user_tag` */

DROP TABLE IF EXISTS `t_business_user_tag`;

CREATE TABLE `t_business_user_tag` (
  `id` int(11) NOT NULL COMMENT 'id',
  `business_user_id` int(11) DEFAULT NULL COMMENT '标签id（枚举）',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id（枚举）',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务用户标签';

/*Table structure for table `t_business_user_vote_detail` */

DROP TABLE IF EXISTS `t_business_user_vote_detail`;

CREATE TABLE `t_business_user_vote_detail` (
  `id` int(11) NOT NULL COMMENT 'id',
  `vote_id` int(11) DEFAULT NULL COMMENT '投票表id',
  `vote_detail_id` int(11) DEFAULT NULL COMMENT '投票详情表id',
  `business_user_id` int(11) DEFAULT NULL COMMENT '业务用户表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务用户投票详情表';

/*Table structure for table `t_questionnaire` */

DROP TABLE IF EXISTS `t_questionnaire`;

CREATE TABLE `t_questionnaire` (
  `id` int(11) NOT NULL COMMENT 'id',
  `title` varchar(512) COMMENT '问卷标题',
  `description` longtext COMMENT '问卷描述',  
  `start_date` datetime DEFAULT NULL COMMENT '问卷开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '问卷结束时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷表';

/*Table structure for table `t_questionnaire_question` */

DROP TABLE IF EXISTS `t_questionnaire_question`;

CREATE TABLE `t_questionnaire_question` (
  `id` int(11) NOT NULL COMMENT 'id',
  `questionnaire_id` int(11) DEFAULT NULL COMMENT '问卷表id',
  `title` varchar(512) COMMENT '标题',
  `detail_type` tinyint(1) DEFAULT NULL COMMENT '类型 1 文本 2 单选 3 多选',
  `seq` decimal(2,0) DEFAULT NULL COMMENT '排序',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷详情表';

/*Table structure for table `t_questionnaire_question_vote` */

DROP TABLE IF EXISTS `t_questionnaire_question_vote`;

CREATE TABLE `t_questionnaire_question_vote` (
  `id` int(11) NOT NULL COMMENT 'id',
  `questionnaire_id` int(11) DEFAULT NULL COMMENT '问卷表id',
  `questionnaire_q_id` int(11) DEFAULT NULL COMMENT '问卷详情表id',
  `questionnaire_q_vote_title` varchar(512) COMMENT '问卷详情投票值',
  `seq` decimal(2,0) DEFAULT NULL COMMENT '业务用户表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷详情投票表';

/*Table structure for table `t_function` */

DROP TABLE IF EXISTS `t_function`;

CREATE TABLE `t_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) DEFAULT NULL COMMENT '功能名称',
  `code` varchar(32) DEFAULT NULL COMMENT '功能编码',
  `url` varchar(256) DEFAULT NULL COMMENT '功能url前缀',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='功能表';

/*Table structure for table `t_img` */

DROP TABLE IF EXISTS `t_img`;

CREATE TABLE `t_img` (
  `id` int(11) NOT NULL COMMENT 'id',
  `img_name` varchar(256) COMMENT '图片名称',
  `img_url` varchar(512) COMMENT '图片url',
  `thumbnail_url` varchar(512) COMMENT '缩略图url',
  `img_size` decimal(12,0) DEFAULT NULL COMMENT '图片大小字节',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片表';

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
  `code` varchar(64) DEFAULT NULL COMMENT '菜单编码',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单id',
  `url` varchar(512) COMMENT '菜单地址',
  `icon` varchar(128) DEFAULT NULL COMMENT '图标',
  `seq` int(11) DEFAULT NULL COMMENT '排序',
  `type` decimal(2,0) DEFAULT NULL COMMENT '类型 1 菜单，2 按钮，3 其他',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

/*Table structure for table `t_menu_function` */

DROP TABLE IF EXISTS `t_menu_function`;

CREATE TABLE `t_menu_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单表id',
  `function_id` int(11) DEFAULT NULL COMMENT '功能表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单表和功能表关联表，暂定为一对一关系';

/*Table structure for table `t_report` */

DROP TABLE IF EXISTS `t_report`;

CREATE TABLE `t_report` (
  `id` int(11) NOT NULL COMMENT 'id',
  `article_id` int(11) DEFAULT NULL COMMENT '被举报文章id',
  `comment_id` int(11) DEFAULT NULL COMMENT '被举报评论表id',
  `reported_business_user_id` int(11) DEFAULT NULL COMMENT '被举报业务用户表id',
  `reported_business_user_nickname` varchar(128) DEFAULT NULL COMMENT '被举报业务用户昵称',
  `reported_comment_content` varchar(512) COMMENT '被举报内容',
  `reporter_business_user_id` int(11) DEFAULT NULL COMMENT '举报业务用户表id',
  `reporter_business_user_nickname` varchar(128) DEFAULT NULL COMMENT '举报业务用户昵称',
  `message` varchar(1024) COMMENT '举报原因',
  `type` tinyint(1) DEFAULT NULL COMMENT '举报类型 1 不实信息 2 有害信息 3 违法信息',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论举报表';

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL COMMENT 'id',
  `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Table structure for table `t_role_authority` */

DROP TABLE IF EXISTS `t_role_authority`;

CREATE TABLE `t_role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `authority_id` int(11) DEFAULT NULL COMMENT '权限id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL COMMENT 'id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表和菜单表关联表';

/*Table structure for table `t_shortcut_menu` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL COMMENT 'id',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `salt` varchar(64) DEFAULT NULL COMMENT '加密盐',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Table structure for table `t_user_group` */

DROP TABLE IF EXISTS `t_user_group`;

CREATE TABLE `t_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(16) DEFAULT NULL COMMENT '用户组名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父用户组',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组';

/*Table structure for table `t_user_group_role` */

DROP TABLE IF EXISTS `t_user_group_role`;

CREATE TABLE `t_user_group_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_group_id` int(11) DEFAULT NULL COMMENT '用户组id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组与角色关联表';

/*Table structure for table `t_user_group_user` */

DROP TABLE IF EXISTS `t_user_group_user`;

CREATE TABLE `t_user_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_group_id` int(11) DEFAULT NULL COMMENT '用户组id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组与用户关联表';

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL COMMENT 'id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户表id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表id',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

/*Table structure for table `t_vote` */

DROP TABLE IF EXISTS `t_vote`;

CREATE TABLE `t_vote` (
  `id` int(11) NOT NULL,
  `title` varchar(512) COMMENT '投票标题',
  `description` varchar(1024) COMMENT '投票描述',
  `type` tinyint(1) DEFAULT NULL COMMENT '投票类型 1 单选 2 多选',
  `expired_flag` tinyint(1) DEFAULT NULL COMMENT '是否过期 1 过期 0 不过期',
  `start_date` datetime DEFAULT NULL COMMENT '投票开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '投票结束书剑',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='投票表';

/*Table structure for table `t_vote_detail` */

DROP TABLE IF EXISTS `t_vote_detail`;

CREATE TABLE `t_vote_detail` (
  `id` int(11) NOT NULL COMMENT 'id',
  `vote_id` int(11) DEFAULT NULL COMMENT '投票id',
  `detail_title` varchar(512) COMMENT '选项描述',
  `seq` decimal(2,0) DEFAULT NULL COMMENT '选项排序',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='投票详情表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
