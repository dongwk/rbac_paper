package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.BusinessUser;
import com.app.service.service.BusinessUserService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.BusinessUserPo;
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
@RequestMapping("/businessUser")
public class BusinessUserController extends BaseRestController<BusinessUserService, BusinessUser> {

    @Autowired
    private BusinessUserService businessUserService;

    @Authorization
    @GetMapping
    public R<?> list(){
        BusinessUser businessUser = new BusinessUser();
        IPage<BusinessUser> page = businessUserService.listPage(getPage(), businessUser);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(businessUserService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody BusinessUserPo businessUserPo){
        ThrowAssert.isNull(businessUserPo, HttpStatus.BAD_REQUEST);

        BusinessUser businessUser = PoToDoUtils.toAddDO(businessUserPo, BusinessUser.class);
        businessUserService.save(businessUser);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody BusinessUserPo businessUserPo){
        ThrowAssert.isNull(businessUserPo, HttpStatus.BAD_REQUEST);

        BusinessUser businessUser = PoToDoUtils.toUpdDO(businessUserPo, BusinessUser.class);
        businessUserService.updateById(businessUser);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(businessUserService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody BusinessUserPo businessUserPo){
        BusinessUser businessUser = PoToDoUtils.toAddDO(businessUserPo, BusinessUser.class);
        IPage<BusinessUser> page = businessUserService.listPage(getPage(), businessUser);
        return R.SUCCESS(page);
    }
}