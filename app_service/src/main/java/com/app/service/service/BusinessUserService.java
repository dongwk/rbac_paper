package com.app.service.service;

import com.app.mapper.mapper.BusinessUserMapper;
import com.app.model.model.BusinessUser;
import com.app.service.base.BaseMapperService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
@Service
public class BusinessUserService extends BaseMapperService<BusinessUserMapper, BusinessUser> {

    public List<BusinessUser> listByIds(List<Integer> ids) {
        return baseMapper.listByIds(ids);
    }

}