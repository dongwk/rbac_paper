package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Category;
import com.app.service.service.CategoryService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.CategoryPo;
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
@RequestMapping("/category")
public class CategoryController extends BaseRestController<CategoryService, Category> {

    @Autowired
    private CategoryService categoryService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Category category = new Category();
        IPage<Category> page = categoryService.listPage(getPage(), category);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(categoryService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody CategoryPo categoryPo){
        ThrowAssert.isNull(categoryPo, HttpStatus.BAD_REQUEST);

        Category category = PoToDoUtils.toAddDO(categoryPo, Category.class);
        categoryService.save(category);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody CategoryPo categoryPo){
        ThrowAssert.isNull(categoryPo, HttpStatus.BAD_REQUEST);

        Category category = PoToDoUtils.toUpdDO(categoryPo, Category.class);
        categoryService.updateById(category);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(categoryService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody CategoryPo categoryPo){
        Category category = PoToDoUtils.toAddDO(categoryPo, Category.class);
        IPage<Category> page = categoryService.listPage(getPage(), category);
        return R.SUCCESS(page);
    }
}