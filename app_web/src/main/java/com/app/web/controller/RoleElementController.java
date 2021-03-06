/**
 * 
 */
package com.app.web.controller;

import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.core.common.ThrowBiz;
import com.app.model.model.RoleElement;
import com.app.service.service.RoleElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.mo.base.PageMo;
import com.app.web.mo.RoleElementMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
@RequestMapping("/roleElement")
public class RoleElementController extends BaseController{

    @Autowired
    private RoleElementService roleElementService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleElement roleElement = new RoleElement();
        IPage<RoleElement> page = roleElementService.listPageCount(getPage(), roleElement);
        return R.SUCCESS(page.getRecords(), page.getTotal());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleElementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleElementMo roleElementMo){

        ThrowAssert.isNull(roleElementMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(roleElementMo.getRoleId(), "roleElement.add.roleId-empty");
        ThrowAssert.isTrue(roleElementMo.getRoleId() < Constants.INT_1 || roleElementMo.getRoleId() > Integer.MAX_VALUE, "roleElement.add.roleId-range");
        ThrowAssert.isNull(roleElementMo.getElementId(), "roleElement.add.elementId-empty");
        ThrowAssert.isTrue(roleElementMo.getElementId() < Constants.INT_1 || roleElementMo.getElementId() > Integer.MAX_VALUE, "roleElement.add.elementId-range");


        RoleElement roleElement = MoToDoUtils.toAddDO(roleElementMo, RoleElement.class);
        roleElementService.save(roleElement);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleElementMo roleElementMo){

        ThrowAssert.isNull(roleElementMo, HttpStatus.BAD_REQUEST);
        ThrowAssert.isNull(roleElementMo.getRoleId(), "roleElement.add.roleId-empty");
        ThrowAssert.isTrue(roleElementMo.getRoleId() < Constants.INT_1 || roleElementMo.getRoleId() > Integer.MAX_VALUE, "roleElement.add.roleId-range");
        ThrowAssert.isNull(roleElementMo.getElementId(), "roleElement.add.elementId-empty");
        ThrowAssert.isTrue(roleElementMo.getElementId() < Constants.INT_1 || roleElementMo.getElementId() > Integer.MAX_VALUE, "roleElement.add.elementId-range");


        RoleElement roleElement = MoToDoUtils.toUpdDO(roleElementMo, RoleElement.class);
        roleElementService.updateById(roleElement);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleElementService.removeById(id));
    }
}