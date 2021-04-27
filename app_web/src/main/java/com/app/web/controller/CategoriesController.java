package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.model.model.Category;
import com.app.service.service.CategoryService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.CommonAnot;
import com.app.web.controller.base.BaseRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author dongwk
* @date 2021-03-17
* @version 1.0
*/
@RestController
@RequestMapping("/categories")
public class CategoriesController extends BaseRestController<CategoryService, Category> {

    @CommonAnot(enablePage = true)
    @Authorization
    @GetMapping
    public R<?> list(){
        return R.SUCCESS(baseService.list());
    }
}