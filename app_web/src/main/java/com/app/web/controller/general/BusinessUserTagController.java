package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.BusinessUserTag;
import com.app.service.service.BusinessUserTagService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.BusinessUserTagPo;
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
@RequestMapping("/businessUserTag")
public class BusinessUserTagController extends BaseRestController<BusinessUserTagService, BusinessUserTag> {

    @Autowired
    private BusinessUserTagService businessUserTagService;

    @Authorization
    @GetMapping
    public R<?> list(){
        BusinessUserTag businessUserTag = new BusinessUserTag();
        IPage<BusinessUserTag> page = businessUserTagService.listPage(getPage(), businessUserTag);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(businessUserTagService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody BusinessUserTagPo businessUserTagPo){
        ThrowAssert.isNull(businessUserTagPo, HttpStatus.BAD_REQUEST);

        BusinessUserTag businessUserTag = PoToDoUtils.toAddDO(businessUserTagPo, BusinessUserTag.class);
        businessUserTagService.save(businessUserTag);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody BusinessUserTagPo businessUserTagPo){
        ThrowAssert.isNull(businessUserTagPo, HttpStatus.BAD_REQUEST);

        BusinessUserTag businessUserTag = PoToDoUtils.toUpdDO(businessUserTagPo, BusinessUserTag.class);
        businessUserTagService.updateById(businessUserTag);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(businessUserTagService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody BusinessUserTagPo businessUserTagPo){
        BusinessUserTag businessUserTag = PoToDoUtils.toAddDO(businessUserTagPo, BusinessUserTag.class);
        IPage<BusinessUserTag> page = businessUserTagService.listPage(getPage(), businessUserTag);
        return R.SUCCESS(page);
    }
}