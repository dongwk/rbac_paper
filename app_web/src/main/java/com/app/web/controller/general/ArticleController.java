package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Article;
import com.app.service.service.ArticleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ArticlePo;
import com.app.web.utils.PoToDoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



/**
*
* @author dongwk
* @date 2021-03-17
* @version 1.0
*/
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseRestController<ArticleService, Article> {

    @Autowired
    private ArticleService articleService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Article article = new Article();
        IPage<Article> page = articleService.listPage(getPage(), article);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(articleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ArticlePo articlePo){
        ThrowAssert.isNull(articlePo, HttpStatus.BAD_REQUEST);

        Article article = PoToDoUtils.toAddDO(articlePo, Article.class);
        articleService.save(article);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ArticlePo articlePo){
        ThrowAssert.isNull(articlePo, HttpStatus.BAD_REQUEST);

        Article article = PoToDoUtils.toUpdDO(articlePo, Article.class);
        articleService.updateById(article);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(articleService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ArticlePo articlePo){
        Article article = PoToDoUtils.toAddDO(articlePo, Article.class);
        IPage<Article> page = articleService.listPage(getPage(), article);
        return R.SUCCESS(page);
    }
}