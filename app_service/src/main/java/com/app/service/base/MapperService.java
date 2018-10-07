package com.app.service.base;

import com.app.mapper.base.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 功能基础类
 * @param <M>
 * @param <T>
 */
public class MapperService<M extends BaseMapper<T>, T> extends BaseService{

    private ServiceImpl<M, T> serviceImpl;

    public MapperService(){
        serviceImpl = new ServiceImpl();
    }

    public boolean insert(T t) {
        return serviceImpl.insert(t);
    }

    public boolean insertBatch(List<T> list) {
        return serviceImpl.insertBatch(list);
    }

    public boolean insertBatch(List<T> list, int i) {
        return serviceImpl.insertBatch(list, i);
    }

    public boolean deleteById(Serializable serializable) {
        return serviceImpl.deleteById(serializable);
    }

    public boolean deleteByMap(Map<String, Object> map) {
        return serviceImpl.deleteByMap(map);
    }

    public boolean delete(Wrapper<T> wrapper) {
        return serviceImpl.delete(wrapper);
    }

    public boolean deleteBatchIds(Collection<? extends Serializable> collection) {
        return serviceImpl.deleteBatchIds(collection);
    }

    public boolean updateById(T t) {
        return serviceImpl.updateById(t);
    }

    public boolean update(T t, Wrapper<T> wrapper) {
        return serviceImpl.update(t, wrapper);
    }

    public boolean updateBatchById(List<T> list) {
        return serviceImpl.updateBatchById(list);
    }

    public boolean updateBatchById(List<T> list, int i) {
        return serviceImpl.updateBatchById(list, i);
    }

    public T selectById(Serializable serializable) {
        return serviceImpl.selectById(serializable);
    }

    public List<T> selectBatchIds(Collection<? extends Serializable> collection) {
        return serviceImpl.selectBatchIds(collection);
    }

    public List<T> selectByMap(Map<String, Object> map) {
        return serviceImpl.selectByMap(map);
    }

    public T selectOne(Wrapper<T> wrapper) {
        return serviceImpl.selectOne(wrapper);
    }

    public Map<String, Object> selectMap(Wrapper<T> wrapper) {
        return serviceImpl.selectMap(wrapper);
    }

    public Object selectObj(Wrapper<T> wrapper) {
        return serviceImpl.selectObj(wrapper);
    }

    public int selectCount(Wrapper<T> wrapper) {
        return serviceImpl.selectCount(wrapper);
    }

    public List<T> selectList(Wrapper<T> wrapper) {
        return serviceImpl.selectList(wrapper);
    }

    public Page<T> selectPage(Page<T> page) {
        return serviceImpl.selectPage(page);
    }

    public List<Map<String, Object>> selectMaps(Wrapper<T> wrapper) {
        return serviceImpl.selectMaps(wrapper);
    }

    public List<Object> selectObjs(Wrapper<T> wrapper) {
        return serviceImpl.selectObjs(wrapper);
    }

    public Page<Map<String, Object>> selectMapsPage(Page page, Wrapper<T> wrapper) {
        return serviceImpl.selectMapsPage(page, wrapper);
    }

    public Page<T> selectPage(Page<T> page, Wrapper<T> wrapper) {
        return serviceImpl.selectPage(page, wrapper);
    }
}
