package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.BusinessUserMessage;
import com.app.service.service.BusinessUserMessageService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.BusinessUserMessagePo;
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
@RequestMapping("/businessUserMessage")
public class BusinessUserMessageController extends BaseRestController<BusinessUserMessageService, BusinessUserMessage> {

    @Autowired
    private BusinessUserMessageService businessUserMessageService;

    @Authorization
    @GetMapping
    public R<?> list(){
        BusinessUserMessage businessUserMessage = new BusinessUserMessage();
        IPage<BusinessUserMessage> page = businessUserMessageService.listPage(getPage(), businessUserMessage);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(businessUserMessageService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody BusinessUserMessagePo businessUserMessagePo){
        ThrowAssert.isNull(businessUserMessagePo, HttpStatus.BAD_REQUEST);

        BusinessUserMessage businessUserMessage = PoToDoUtils.toAddDO(businessUserMessagePo, BusinessUserMessage.class);
        businessUserMessageService.save(businessUserMessage);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody BusinessUserMessagePo businessUserMessagePo){
        ThrowAssert.isNull(businessUserMessagePo, HttpStatus.BAD_REQUEST);

        BusinessUserMessage businessUserMessage = PoToDoUtils.toUpdDO(businessUserMessagePo, BusinessUserMessage.class);
        businessUserMessageService.updateById(businessUserMessage);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(businessUserMessageService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody BusinessUserMessagePo businessUserMessagePo){
        BusinessUserMessage businessUserMessage = PoToDoUtils.toAddDO(businessUserMessagePo, BusinessUserMessage.class);
        IPage<BusinessUserMessage> page = businessUserMessageService.listPage(getPage(), businessUserMessage);
        return R.SUCCESS(page);
    }
}