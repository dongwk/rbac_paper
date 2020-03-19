/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.web.result.R;
import com.app.model.model.Element;
import com.app.service.service.ElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.PageMo;
import com.app.web.mo.ElementMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
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
    public R<?> list(@RequestBody PageMo pageMo){
        Element element = new Element();
        PageCount<Element> page = elementService.selectPageCount(PageMoUtils.toMPPage(pageMo), element);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(elementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ElementMo elementMo){

        if (elementMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(elementMo.getCode())) throw new BizException("element.add.code-empty");
        if (elementMo.getCode().trim().length() > 32) throw new BizException("element.add.code-max");
        if (StringUtils.isBlank(elementMo.getDescription())) throw new BizException("element.add.description-empty");
        if (elementMo.getDescription().trim().length() > 128) throw new BizException("element.add.description-max");
        elementMo.setCode(elementMo.getCode().trim());
        elementMo.setDescription(elementMo.getDescription().trim());

        Element element = MoToDoUtils.toAddDO(elementMo, Element.class);
        elementService.insert(element);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementMo elementMo){

        if (elementMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (elementMo.getId() == null || elementMo.getId() < 1) throw new BizException("element.upd.id-empty");
        if (StringUtils.isBlank(elementMo.getCode())) throw new BizException("element.add.code-empty");
        if (elementMo.getCode().trim().length() > 32) throw new BizException("element.add.code-max");
        if (StringUtils.isBlank(elementMo.getDescription())) throw new BizException("element.add.description-empty");
        if (elementMo.getDescription().trim().length() > 128) throw new BizException("element.add.description-max");
        elementMo.setCode(elementMo.getCode().trim());
        elementMo.setDescription(elementMo.getDescription().trim());

        Element element = MoToDoUtils.toUpdDO(elementMo, Element.class);
        elementService.updateById(element);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(elementService.deleteById(id));
    }
}