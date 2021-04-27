package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Element;
import com.app.service.service.ElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ElementPo;
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
@RequestMapping("/element")
public class ElementController extends BaseRestController<ElementService, Element> {

    @Autowired
    private ElementService elementService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Element element = new Element();
        IPage<Element> page = elementService.listPage(getPage(), element);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(elementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ElementPo elementPo){
        ThrowAssert.isNull(elementPo, HttpStatus.BAD_REQUEST);

        Element element = PoToDoUtils.toAddDO(elementPo, Element.class);
        elementService.save(element);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementPo elementPo){
        ThrowAssert.isNull(elementPo, HttpStatus.BAD_REQUEST);

        Element element = PoToDoUtils.toUpdDO(elementPo, Element.class);
        elementService.updateById(element);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(elementService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ElementPo elementPo){
        Element element = PoToDoUtils.toAddDO(elementPo, Element.class);
        IPage<Element> page = elementService.listPage(getPage(), element);
        return R.SUCCESS(page);
    }
}