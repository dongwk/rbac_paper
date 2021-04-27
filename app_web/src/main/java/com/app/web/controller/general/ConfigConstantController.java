package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.ConfigConstant;
import com.app.service.service.ConfigConstantService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ConfigConstantPo;
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
@RequestMapping("/configConstant")
public class ConfigConstantController extends BaseRestController<ConfigConstantService, ConfigConstant> {

    @Autowired
    private ConfigConstantService configConstantService;

    @Authorization
    @GetMapping
    public R<?> list(){
        ConfigConstant configConstant = new ConfigConstant();
        IPage<ConfigConstant> page = configConstantService.listPage(getPage(), configConstant);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(configConstantService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ConfigConstantPo configConstantPo){
        ThrowAssert.isNull(configConstantPo, HttpStatus.BAD_REQUEST);

        ConfigConstant configConstant = PoToDoUtils.toAddDO(configConstantPo, ConfigConstant.class);
        configConstantService.save(configConstant);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ConfigConstantPo configConstantPo){
        ThrowAssert.isNull(configConstantPo, HttpStatus.BAD_REQUEST);

        ConfigConstant configConstant = PoToDoUtils.toUpdDO(configConstantPo, ConfigConstant.class);
        configConstantService.updateById(configConstant);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(configConstantService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ConfigConstantPo configConstantPo){
        ConfigConstant configConstant = PoToDoUtils.toAddDO(configConstantPo, ConfigConstant.class);
        IPage<ConfigConstant> page = configConstantService.listPage(getPage(), configConstant);
        return R.SUCCESS(page);
    }
}