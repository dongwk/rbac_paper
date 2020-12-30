/**
 * 
 */
package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Element;
import com.app.service.service.ElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.ElementMo;
import com.app.web.utils.MoToDoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/element")
public class ElementController extends BaseController{

    @Autowired
    private ElementService elementService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Element element = new Element();
        IPage<Element> page = elementService.listPageCount(getPage(), element);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(elementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ElementMo elementMo){

        ThrowAssert.isNull(elementMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isBlank(elementMo.getCode(), "element.add.code-empty");
        ThrowAssert.isTrue(elementMo.getCode().trim().length() > 32, "element.add.code-max");
        ThrowAssert.isBlank(elementMo.getDescription(), "element.add.description-empty");
        ThrowAssert.isTrue(elementMo.getDescription().trim().length() > 128,"element.add.description-max");

        elementMo.setCode(elementMo.getCode().trim());
        elementMo.setDescription(elementMo.getDescription().trim());

        Element element = MoToDoUtils.toAddDO(elementMo, Element.class);
        elementService.save(element);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementMo elementMo){

        ThrowAssert.isNull(elementMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isTrue(elementMo.getId() == null || elementMo.getId() < 1, "element.upd.id-empty");
        ThrowAssert.isBlank(elementMo.getCode(), "element.add.code-empty");
        ThrowAssert.isTrue(elementMo.getCode().trim().length() > 32, "element.add.code-max");
        ThrowAssert.isBlank(elementMo.getDescription(), "element.add.description-empty");
        ThrowAssert.isTrue(elementMo.getDescription().trim().length() > 128, "element.add.description-max");
        elementMo.setCode(elementMo.getCode().trim());
        elementMo.setDescription(elementMo.getDescription().trim());

        Element element = MoToDoUtils.toUpdDO(elementMo, Element.class);
        elementService.updateById(element);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(elementService.removeById(id));
    }
}