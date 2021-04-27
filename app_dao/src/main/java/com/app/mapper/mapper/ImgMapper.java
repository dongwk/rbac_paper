package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.Img;

import java.util.List;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
public interface ImgMapper extends BaseMapper<Img> {

    List<Img> listByImgIds(List<Integer> imgIds);
}