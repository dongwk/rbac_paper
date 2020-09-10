1、创建比对表信息的临时表
```sql
CREATE TABLE `online_table` (
  `table_schema` VARCHAR(200) NULL COMMENT '',
  `table_name` VARCHAR(200) NULL COMMENT '',
  `column_name` VARCHAR(200) NULL COMMENT '',
  `column_type` VARCHAR(200) NULL COMMENT '',
  `column_comment` VARCHAR(200) NULL COMMENT '',
  KEY `idx_send_status` (`table_name`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='online';

CREATE TABLE `beta_table` (
  `table_schema` VARCHAR(200) NULL COMMENT '',
  `table_name` VARCHAR(200) NULL COMMENT '',
  `column_name` VARCHAR(200) NULL COMMENT '',
  `column_type` VARCHAR(200) NULL COMMENT '',
  `column_comment` VARCHAR(200) NULL COMMENT '',
  KEY `idx_send_status` (`table_name`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='beta';
```

2、通过 `Navicat` 查询线上/线下所有表信息
```sql
SELECT table_schema, table_name, column_name, column_type, column_comment FROM information_schema.columns WHERE table_schema='库名' ORDER BY table_name, column_name;
```

3、将上方的信息拷贝到 `Excel` 中，再将excel中的信息拷贝到 `Notepad++` 中，这样就是一个标准的文本框，可直接使用搜索替换成 `SQL`
```sql
INSERT INTO `online_table` VALUES('xxx', 'xxx', 'xxx', 'xxx', 'xxx');
```

4、筛选出 beta 环境新增的表
```sql
SELECT * FROM (
SELECT t1.*, t.table_name online_table FROM `online_table` t
RIGHT JOIN `beta_table` t1 ON t.table_schema=t1.table_schema AND t.table_name=t1.table_name
) t2 WHERE t2.online_table IS NULL
GROUP BY t2.table_name
ORDER BY t2.table_schema, t2.table_name;
```

5、根据筛选出来的表，通过 `SHOW CREATE TABLE 表名` 输出建表语句，可通过代码循环生成
```sql
SHOW CREATE TABLE 表名
```

6、筛选出 beta 多余的字段
```sql
SELECT table_schema, table_name, 
CONCAT('ALTER TABLE ', table_name,' ADD COLUMN `', column_name, '` ', column_type, ' NOT NULL DEFAULT ', 
(CASE column_default WHEN '' THEN '\'\'' ELSE CONCAT('\'', column_default, '\'') END), 
' COMMENT \'', column_comment,'\';') 
FROM (
SELECT t1.*, t.table_name online_table, t.column_name online_column FROM `online_table` t
RIGHT JOIN `beta_table` t1 ON t.table_schema=t1.table_schema AND t.table_name=t1.table_name AND t.column_name=t1.column_name
) t2 
WHERE t2.online_column IS NULL
AND t2.table_name IN (
SELECT t3.table_name FROM `online_table` t3
)
ORDER BY t2.table_schema, t2.table_name, t2.column_name;
``` 