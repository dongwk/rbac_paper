-- 查看列的信息
DESC app_authority;

-- 查看表的注释
SELECT table_name,table_comment FROM information_schema.tables WHERE table_schema = 'rbac_paper';

-- 查看列的信息
SELECT column_name, column_type, column_comment FROM information_schema.columns WHERE table_schema ='rbac_paper' AND table_name = 'app_authority';

-- 添加公共字段
ALTER TABLE app_user ADD COLUMN `status` TINYINT(1) DEFAULT NULL COMMENT '状态 1 有效 0 删除';
ALTER TABLE app_user ADD COLUMN `create_time` DATETIME DEFAULT NULL COMMENT '创建时间';
ALTER TABLE app_user ADD COLUMN `update_time` DATETIME DEFAULT NULL COMMENT '更新时间';