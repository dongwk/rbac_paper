/**
 * 
 */
package com.app.web.controller;

import com.app.common.PageCount;
import com.app.common.constant.Constants;
import com.app.common.web.result.R;
import com.app.model.model.RoleElement;
import com.app.service.service.RoleElementService;
import com.app.web.config.annotation.Authorization;
import com.app.web.controller.base.BaseController;
import com.app.web.controller.exce.BizException;
import com.app.web.mo.RoleElementMo;
import com.app.web.mo.PageMo;
import com.app.web.utils.MoToDoUtils;
import com.app.web.utils.PageMoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roleElement")
public class RoleElementController extends BaseController{

    @Autowired
    private RoleElementService roleElementService;

    @Authorization
    @GetMapping
    public R<?> list(@RequestBody PageMo pageMo){
        RoleElement roleElement = new RoleElement();
        PageCount<RoleElement> page = roleElementService.selectPageCount(PageMoUtils.toMPPage(pageMo), roleElement);
        return R.SUCCESS(page.getData(), page.getCount());
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleElementService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleElementMo roleElementMo){

        if (roleElementMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (roleElementMo.getRoleId() == null) throw new BizException("roleElement.add.roleId-empty");
        if (roleElementMo.getRoleId() < Constants.INT_1 || roleElementMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("roleElement.add.roleId-range");
        if (roleElementMo.getElementId() == null) throw new BizException("roleElement.add.elementId-empty");
        if (roleElementMo.getElementId() < Constants.INT_1 || roleElementMo.getElementId() > Integer.MAX_VALUE) throw new BizException("roleElement.add.elementId-range");


        RoleElement roleElement = MoToDoUtils.toAddDO(roleElementMo, RoleElement.class);
        roleElementService.insert(roleElement);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleElementMo roleElementMo){

        if (roleElementMo == null) throw new BizException(HttpStatus.BAD_REQUEST);
        if (roleElementMo.getRoleId() == null) throw new BizException("roleElement.add.roleId-empty");
        if (roleElementMo.getRoleId() < Constants.INT_1 || roleElementMo.getRoleId() > Integer.MAX_VALUE) throw new BizException("roleElement.add.roleId-range");
        if (roleElementMo.getElementId() == null) throw new BizException("roleElement.add.elementId-empty");
        if (roleElementMo.getElementId() < Constants.INT_1 || roleElementMo.getElementId() > Integer.MAX_VALUE) throw new BizException("roleElement.add.elementId-range");


        RoleElement roleElement = MoToDoUtils.toUpdDO(roleElementMo, RoleElement.class);
        roleElementService.updateById(roleElement);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        if (id == null) throw new BizException(HttpStatus.BAD_REQUEST);
        return R.SUCCESS(roleElementService.deleteById(id));
    }
}