package com.app.mapper.mapper;

import com.app.mapper.base.BaseMapper;
import com.app.model.model.Article;

import java.util.List;

/**
 *
 * @author dongwk
 * @date 2021-03-17
 * @version 1.0
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> listByCategoryId(int categoryId);

    List<Article> listAll();
}