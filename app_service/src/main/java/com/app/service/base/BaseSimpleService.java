package com.app.service.base;

import com.app.mapper.base.BaseMapper;
import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.enums.SqlMethod;
import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.*;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 功能基础类
 * @param <M>
 * @param <T>
 */
public class BaseSimpleService<M extends BaseMapper<T>, T extends BaseModel> extends BaseService{

    @Autowired
    protected M baseMapper;

    public T get(T t) {
        List<T> list = baseMapper.selectList(new EntityWrapper<>(t));
        return list.size() > 0 ? list.get(0):null;
    }

    public T getOne(T t){
        return baseMapper.selectOne(t);
    }

    public T get(Serializable id){
        return baseMapper.selectById(id);
    }

    /**
     * <p>
     * 判断数据库操作是否成功
     * </p>
     * <p>
     * 注意！！ 该方法为 Integer 判断，不可传入 int 基本类型
     * </p>
     *
     * @param result 数据库操作返回影响条数
     * @return boolean
     */
    protected static boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    @SuppressWarnings("unchecked")
    protected Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenricType(getClass(), 1);
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insert(T entity) {
        return retBool(baseMapper.insert(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertAllColumn(T entity) {
        return retBool(baseMapper.insertAllColumn(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(List<T> entityList) {
        return insertBatch(entityList, 30);
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(List<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            String sqlStatement = sqlStatement(SqlMethod.INSERT_ONE);
            for (int i = 0; i < size; i++) {
                batchSqlSession.insert(sqlStatement, entityList.get(i));
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute insertBatch Method. Cause", e);
        }
        return true;
    }

    /**
     * <p>
     * TableId 注解存在更新记录，否插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdate(T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal)) {
                    return insert(entity);
                } else {
                    /*
                     * 更新成功直接返回，失败执行插入逻辑
                     */
                    return updateById(entity) || insert(entity);
                }
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdateAllColumn(T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal)) {
                    return insertAllColumn(entity);
                } else {
                    /*
                     * 更新成功直接返回，失败执行插入逻辑
                     */
                    return updateAllColumnById(entity) || insertAllColumn(entity);
                }
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdateBatch(List<T> entityList) {
        return insertOrUpdateBatch(entityList, 30);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdateBatch(List<T> entityList, int batchSize) {
        return insertOrUpdateBatch(entityList, batchSize, true);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdateAllColumnBatch(List<T> entityList) {
        return insertOrUpdateBatch(entityList, 30, false);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdateAllColumnBatch(List<T> entityList, int batchSize) {
        return insertOrUpdateBatch(entityList, batchSize, false);
    }

    /**
     * 批量插入修改
     *
     * @param entityList 实体对象列表
     * @param batchSize  批量刷新个数
     * @param selective  是否滤掉空字段
     * @return boolean
     */
    private boolean insertOrUpdateBatch(List<T> entityList, int batchSize, boolean selective) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            for (int i = 0; i < size; i++) {
                if (selective) {
                    insertOrUpdate(entityList.get(i));
                } else {
                    insertOrUpdateAllColumn(entityList.get(i));
                }
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute insertOrUpdateBatch Method. Cause", e);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteById(Serializable id) {
        return SqlHelper.delBool(baseMapper.deleteById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByMap(Map<String, Object> columnMap) {
        if (MapUtils.isEmpty(columnMap)) {
            throw new MybatisPlusException("deleteByMap columnMap is empty.");
        }
        return SqlHelper.delBool(baseMapper.deleteByMap(columnMap));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Wrapper<T> wrapper) {
        return SqlHelper.delBool(baseMapper.delete(wrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(T entity) {
        return retBool(baseMapper.updateById(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllColumnById(T entity) {
        return retBool(baseMapper.updateAllColumnById(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean update(T entity, Wrapper<T> wrapper) {
        return retBool(baseMapper.update(entity, wrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(List<T> entityList) {
        return updateBatchById(entityList, 30);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(List<T> entityList, int batchSize) {
        return updateBatchById(entityList, batchSize, true);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllColumnBatchById(List<T> entityList) {
        return updateAllColumnBatchById(entityList, 30);
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllColumnBatchById(List<T> entityList, int batchSize) {
        return updateBatchById(entityList, batchSize, false);
    }

    /**
     * 根据主键ID进行批量修改
     *
     * @param entityList 实体对象列表
     * @param batchSize  批量刷新个数
     * @param selective  是否滤掉空字段
     * @return boolean
     */
    private boolean updateBatchById(List<T> entityList, int batchSize, boolean selective) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            SqlMethod sqlMethod = selective ? SqlMethod.UPDATE_BY_ID : SqlMethod.UPDATE_ALL_COLUMN_BY_ID;
            String sqlStatement = sqlStatement(sqlMethod);
            for (int i = 0; i < size; i++) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put("et", entityList.get(i));
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute updateBatchById Method. Cause", e);
        }
        return true;
    }

    public T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    public List<T> selectByMap(Map<String, Object> columnMap) {
        return baseMapper.selectByMap(columnMap);
    }

    public T selectOne(Wrapper<T> wrapper) {
        return SqlHelper.getObject(baseMapper.selectList(wrapper));
    }

    public Map<String, Object> selectMap(Wrapper<T> wrapper) {
        return SqlHelper.getObject(baseMapper.selectMaps(wrapper));
    }

    public Object selectObj(Wrapper<T> wrapper) {
        return SqlHelper.getObject(baseMapper.selectObjs(wrapper));
    }

    public int selectCount(Wrapper<T> wrapper) {
        return SqlHelper.retCount(baseMapper.selectCount(wrapper));
    }

    public List<T> selectList(Wrapper<T> wrapper) {
        return baseMapper.selectList(wrapper);
    }

    public Page<T> selectPage(Page<T> page) {
        return selectPage(page, Condition.EMPTY);
    }

    public List<Map<String, Object>> selectMaps(Wrapper<T> wrapper) {
        return baseMapper.selectMaps(wrapper);
    }

    public List<Object> selectObjs(Wrapper<T> wrapper) {
        return baseMapper.selectObjs(wrapper);
    }

    public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<T> wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(baseMapper.selectMapsPage(page, wrapper));
        return page;
    }

    public Page<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(baseMapper.selectPage(page, wrapper));
        return page;
    }
}