/**
 * 
 */
package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.core.common.ThrowBiz;
import com.app.model.model.Element;
import com.app.service.service.ElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.ElementMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/element")
public class ElementController extends BaseController{

    @Autowired
    private ElementService elementService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        Element element = new Element();
        IPage<Element> page = elementService.listPageCount(PageMoUtils.toMPPage(pageMo), element);
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

        if (elementMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(elementMo.getCode())) ThrowBiz.throwExce("element.add.code-empty");
        if (elementMo.getCode().trim().length() > 32) ThrowBiz.throwExce("element.add.code-max");
        if (StringUtils.isBlank(elementMo.getDescription())) ThrowBiz.throwExce("element.add.description-empty");
        if (elementMo.getDescription().trim().length() > 128) ThrowBiz.throwExce("element.add.description-max");
        elementMo.setCode(elementMo.getCode().trim());
        elementMo.setDescription(elementMo.getDescription().trim());

        Element element = MoToDoUtils.toAddDO(elementMo, Element.class);
        elementService.save(element);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementMo elementMo){

        if (elementMo == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        if (elementMo.getId() == null || elementMo.getId() < 1) ThrowBiz.throwExce("element.upd.id-empty");
        if (StringUtils.isBlank(elementMo.getCode())) ThrowBiz.throwExce("element.add.code-empty");
        if (elementMo.getCode().trim().length() > 32) ThrowBiz.throwExce("element.add.code-max");
        if (StringUtils.isBlank(elementMo.getDescription())) ThrowBiz.throwExce("element.add.description-empty");
        if (elementMo.getDescription().trim().length() > 128) ThrowBiz.throwExce("element.add.description-max");
        elementMo.setCode(elementMo.getCode().trim());
        elementMo.setDescription(elementMo.getDescription().trim());

        Element element = MoToDoUtils.toUpdDO(elementMo, Element.class);
        elementService.updateById(element);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) ThrowBiz.throwExce(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(elementService.removeById(id));
    }
}