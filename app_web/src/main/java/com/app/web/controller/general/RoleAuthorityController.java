package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.RoleAuthority;
import com.app.service.service.RoleAuthorityService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.RoleAuthorityPo;
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
@RequestMapping("/roleAuthority")
public class RoleAuthorityController extends BaseRestController<RoleAuthorityService, RoleAuthority> {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @Authorization
    @GetMapping
    public R<?> list(){
        RoleAuthority roleAuthority = new RoleAuthority();
        IPage<RoleAuthority> page = roleAuthorityService.listPage(getPage(), roleAuthority);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(roleAuthorityService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody RoleAuthorityPo roleAuthorityPo){
        ThrowAssert.isNull(roleAuthorityPo, HttpStatus.BAD_REQUEST);

        RoleAuthority roleAuthority = PoToDoUtils.toAddDO(roleAuthorityPo, RoleAuthority.class);
        roleAuthorityService.save(roleAuthority);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody RoleAuthorityPo roleAuthorityPo){
        ThrowAssert.isNull(roleAuthorityPo, HttpStatus.BAD_REQUEST);

        RoleAuthority roleAuthority = PoToDoUtils.toUpdDO(roleAuthorityPo, RoleAuthority.class);
        roleAuthorityService.updateById(roleAuthority);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(roleAuthorityService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody RoleAuthorityPo roleAuthorityPo){
        RoleAuthority roleAuthority = PoToDoUtils.toAddDO(roleAuthorityPo, RoleAuthority.class);
        IPage<RoleAuthority> page = roleAuthorityService.listPage(getPage(), roleAuthority);
        return R.SUCCESS(page);
    }
}