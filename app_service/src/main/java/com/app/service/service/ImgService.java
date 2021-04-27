package com.app.service.service;

import com.app.mapper.mapper.ImgMapper;
import com.app.model.model.Img;
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
public class ImgService extends BaseMapperService<ImgMapper, Img> {
    public List<Img> listByImgIds(List<Integer> imgIds) {
        return baseMapper.listByImgIds(imgIds);
    }

}