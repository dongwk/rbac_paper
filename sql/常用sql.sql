-- 查看列的信息
DESC app_authority;

-- 查看表的注释
SELECT table_name,table_comment FROM information_schema.tables WHERE table_schema = 'rbac_paper';

-- 查看列的信息
SELECT column_name, column_type, column_comment FROM information_schema.columns WHERE table_schema ='rbac_paper' AND table_name = 'app_authority';

-- 添加公共字段
ALTER TABLE `app_user_group` ADD COLUMN `status` TINYINT(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除';
ALTER TABLE `app_user_group` ADD COLUMN `create_time` DATETIME DEFAULT NULL COMMENT '创建时间';
ALTER TABLE `app_user_group` ADD COLUMN `update_time` DATETIME DEFAULT NULL COMMENT '更新时间';

-- 查询
SELECT * FROM `app_authority` t ORDER BY t.id DESC;
SELECT * FROM `app_authority_element` t ORDER BY t.id DESC;
SELECT * FROM `app_authority_function` t ORDER BY t.id DESC;
SELECT * FROM `app_authority_menu` t ORDER BY t.id DESC;
SELECT * FROM `app_element` t ORDER BY t.id DESC;
SELECT * FROM `app_function` t ORDER BY t.id DESC;
SELECT * FROM `app_menu` t ORDER BY t.id DESC;
SELECT * FROM `app_role` t ORDER BY t.id DESC;
SELECT * FROM `app_role_authority` t ORDER BY t.id DESC;
SELECT * FROM `app_user` t ORDER BY t.id DESC;
SELECT * FROM `app_user_group` t ORDER BY t.id DESC;
SELECT * FROM `app_user_group_role` t ORDER BY t.id DESC;
SELECT * FROM `app_user_group_user` t ORDER BY t.id DESC;
SELECT * FROM `app_user_role` t ORDER BY t.id DESC;


--
app_authority
app_authority_element
app_authority_function
app_authority_menu
app_element
app_function
app_menu
app_role
app_role_authority
app_user
app_user_group
app_user_group_role
app_user_group_user
app_user_role

