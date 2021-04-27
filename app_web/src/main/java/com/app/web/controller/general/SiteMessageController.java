package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.SiteMessage;
import com.app.service.service.SiteMessageService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.SiteMessagePo;
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
@RequestMapping("/siteMessage")
public class SiteMessageController extends BaseRestController<SiteMessageService, SiteMessage> {

    @Autowired
    private SiteMessageService siteMessageService;

    @Authorization
    @GetMapping
    public R<?> list(){
        SiteMessage siteMessage = new SiteMessage();
        IPage<SiteMessage> page = siteMessageService.listPage(getPage(), siteMessage);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(siteMessageService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody SiteMessagePo siteMessagePo){
        ThrowAssert.isNull(siteMessagePo, HttpStatus.BAD_REQUEST);

        SiteMessage siteMessage = PoToDoUtils.toAddDO(siteMessagePo, SiteMessage.class);
        siteMessageService.save(siteMessage);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody SiteMessagePo siteMessagePo){
        ThrowAssert.isNull(siteMessagePo, HttpStatus.BAD_REQUEST);

        SiteMessage siteMessage = PoToDoUtils.toUpdDO(siteMessagePo, SiteMessage.class);
        siteMessageService.updateById(siteMessage);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(siteMessageService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody SiteMessagePo siteMessagePo){
        SiteMessage siteMessage = PoToDoUtils.toAddDO(siteMessagePo, SiteMessage.class);
        IPage<SiteMessage> page = siteMessageService.listPage(getPage(), siteMessage);
        return R.SUCCESS(page);
    }
}