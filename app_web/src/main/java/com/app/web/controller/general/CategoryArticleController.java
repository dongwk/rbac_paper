package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.CategoryArticle;
import com.app.service.service.CategoryArticleService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.CategoryArticlePo;
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
@RequestMapping("/categoryArticle")
public class CategoryArticleController extends BaseRestController<CategoryArticleService, CategoryArticle> {

    @Autowired
    private CategoryArticleService categoryArticleService;

    @Authorization
    @GetMapping
    public R<?> list(){
        CategoryArticle categoryArticle = new CategoryArticle();
        IPage<CategoryArticle> page = categoryArticleService.listPage(getPage(), categoryArticle);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(categoryArticleService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody CategoryArticlePo categoryArticlePo){
        ThrowAssert.isNull(categoryArticlePo, HttpStatus.BAD_REQUEST);

        CategoryArticle categoryArticle = PoToDoUtils.toAddDO(categoryArticlePo, CategoryArticle.class);
        categoryArticleService.save(categoryArticle);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody CategoryArticlePo categoryArticlePo){
        ThrowAssert.isNull(categoryArticlePo, HttpStatus.BAD_REQUEST);

        CategoryArticle categoryArticle = PoToDoUtils.toUpdDO(categoryArticlePo, CategoryArticle.class);
        categoryArticleService.updateById(categoryArticle);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(categoryArticleService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody CategoryArticlePo categoryArticlePo){
        CategoryArticle categoryArticle = PoToDoUtils.toAddDO(categoryArticlePo, CategoryArticle.class);
        IPage<CategoryArticle> page = categoryArticleService.listPage(getPage(), categoryArticle);
        return R.SUCCESS(page);
    }
}