package com.app.service.service;

import com.app.mapper.mapper.ArticleMapper;
import com.app.model.model.Article;
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
public class ArticleService extends BaseMapperService<ArticleMapper, Article> {

    public List<Article> listByCategoryId(int categoryId) {
        return baseMapper.listByCategoryId(categoryId);
    }

    public List<Article> listAll() {
        return baseMapper.listAll();
    }
}