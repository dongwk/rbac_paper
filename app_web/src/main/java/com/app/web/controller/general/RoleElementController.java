package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.RoleElement;
import com.app.service.service.RoleElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.RoleElementPo;
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
public class RoleElementController extends BaseRestController<RoleElementService, RoleElement> {

    @Autowired
    private RoleElementService roleElementService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleElement roleElement = new RoleElement();
        IPage<RoleElement> page = roleElementService.listPage(getPage(), roleElement);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleElementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleElementPo roleElementPo){
        ThrowAssert.isNull(roleElementPo, HttpStatus.BAD_REQUEST);

        RoleElement roleElement = PoToDoUtils.toAddDO(roleElementPo, RoleElement.class);
        roleElementService.save(roleElement);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleElementPo roleElementPo){
        ThrowAssert.isNull(roleElementPo, HttpStatus.BAD_REQUEST);

        RoleElement roleElement = PoToDoUtils.toUpdDO(roleElementPo, RoleElement.class);
        roleElementService.updateById(roleElement);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(roleElementService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody RoleElementPo roleElementPo){
        RoleElement roleElement = PoToDoUtils.toAddDO(roleElementPo, RoleElement.class);
        IPage<RoleElement> page = roleElementService.listPage(getPage(), roleElement);
        return R.SUCCESS(page);
    }
}