package com.app.service.base;

import com.app.mapper.base.BaseMapper;
import com.app.model.base.BaseModel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.List;

/**
 * 功能基础类
 * @param <M>
 * @param <T>
 */
public abstract class BaseSimpleService<M extends BaseMapper<T>, T extends BaseModel> extends ServiceImpl<M, T> {


    public T get(T t) {
        return baseMapper.selectOne(new QueryWrapper<>(t));
    }

    public T get(Serializable id){
        return baseMapper.selectById(id);
    }

    public IPage<T> listPage(Page<T> page, T t) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(t);
        page.setSearchCount(Boolean.FALSE);
        return super.page(page, wrapper);
    }

    public IPage<T> listPage(Page<T> page, T t, boolean isAsc, String... columns) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(t);
        page.setSearchCount(Boolean.FALSE);
        wrapper.orderBy(Boolean.TRUE, isAsc, columns);
        return super.page(page, wrapper);
    }

    public IPage<T>  listPageCount(Page<T> page, T t) {
        Wrapper<T> wrapper = new QueryWrapper<T>(t);
        return super.page(page, wrapper);
    }

    public IPage<T> listPageCount(Page<T> page, T t, boolean isAsc, String... columns) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(t);
        wrapper.orderBy(Boolean.TRUE, isAsc, columns);
        return super.page(page, wrapper);
    }

    public List<T> list(T t) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(t);
        return super.list(wrapper);
    }

    public List<T> list(T t, boolean isAsc, String... columns) {
        QueryWrapper<T> wrapper = new QueryWrapper<T>(t);
        wrapper.orderBy(Boolean.TRUE, isAsc, columns);
        return super.list(wrapper);
    }
}
