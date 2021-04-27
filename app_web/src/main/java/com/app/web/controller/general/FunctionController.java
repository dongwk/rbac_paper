package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Function;
import com.app.service.service.FunctionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.FunctionPo;
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
@RequestMapping("/function")
public class FunctionController extends BaseRestController<FunctionService, Function> {

    @Autowired
    private FunctionService functionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Function function = new Function();
        IPage<Function> page = functionService.listPage(getPage(), function);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(functionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody FunctionPo functionPo){
        ThrowAssert.isNull(functionPo, HttpStatus.BAD_REQUEST);

        Function function = PoToDoUtils.toAddDO(functionPo, Function.class);
        functionService.save(function);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody FunctionPo functionPo){
        ThrowAssert.isNull(functionPo, HttpStatus.BAD_REQUEST);

        Function function = PoToDoUtils.toUpdDO(functionPo, Function.class);
        functionService.updateById(function);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(functionService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody FunctionPo functionPo){
        Function function = PoToDoUtils.toAddDO(functionPo, Function.class);
        IPage<Function> page = functionService.listPage(getPage(), function);
        return R.SUCCESS(page);
    }
}