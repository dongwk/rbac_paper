package com.app.service.base;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.User;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 功能基础类
 * @param <M>
 * @param <T>
 */
public class MapperSupportService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T>{

    public T get(T t) {
        List<T> list = baseMapper.selectList(new EntityWrapper<>(t));
        return list.size() > 0 ? list.get(0):null;
    }
}
