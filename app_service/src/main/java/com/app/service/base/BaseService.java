package com.app.service.base;

import com.app.mapper.base.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

public class BaseService<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
}
