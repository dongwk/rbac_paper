package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.ElementFunction;
import com.app.service.service.ElementFunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ElementFunctionPo;
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
@RequestMapping("/elementFunction")
public class ElementFunctionController extends BaseRestController<ElementFunctionService, ElementFunction> {

    @Autowired
    private ElementFunctionService elementFunctionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        ElementFunction elementFunction = new ElementFunction();
        IPage<ElementFunction> page = elementFunctionService.listPage(getPage(), elementFunction);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(elementFunctionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ElementFunctionPo elementFunctionPo){
        ThrowAssert.isNull(elementFunctionPo, HttpStatus.BAD_REQUEST);

        ElementFunction elementFunction = PoToDoUtils.toAddDO(elementFunctionPo, ElementFunction.class);
        elementFunctionService.save(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ElementFunctionPo elementFunctionPo){
        ThrowAssert.isNull(elementFunctionPo, HttpStatus.BAD_REQUEST);

        ElementFunction elementFunction = PoToDoUtils.toUpdDO(elementFunctionPo, ElementFunction.class);
        elementFunctionService.updateById(elementFunction);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(elementFunctionService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ElementFunctionPo elementFunctionPo){
        ElementFunction elementFunction = PoToDoUtils.toAddDO(elementFunctionPo, ElementFunction.class);
        IPage<ElementFunction> page = elementFunctionService.listPage(getPage(), elementFunction);
        return R.SUCCESS(page);
    }
}