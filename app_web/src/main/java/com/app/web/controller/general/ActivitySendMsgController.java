package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.ActivitySendMsg;
import com.app.service.service.ActivitySendMsgService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ActivitySendMsgPo;
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
@RequestMapping("/activitySendMsg")
public class ActivitySendMsgController extends BaseRestController<ActivitySendMsgService, ActivitySendMsg> {

    @Autowired
    private ActivitySendMsgService activitySendMsgService;

    @Authorization
    @GetMapping
    public R<?> list(){
        ActivitySendMsg activitySendMsg = new ActivitySendMsg();
        IPage<ActivitySendMsg> page = activitySendMsgService.listPage(getPage(), activitySendMsg);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(activitySendMsgService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ActivitySendMsgPo activitySendMsgPo){
        ThrowAssert.isNull(activitySendMsgPo, HttpStatus.BAD_REQUEST);

        ActivitySendMsg activitySendMsg = PoToDoUtils.toAddDO(activitySendMsgPo, ActivitySendMsg.class);
        activitySendMsgService.save(activitySendMsg);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ActivitySendMsgPo activitySendMsgPo){
        ThrowAssert.isNull(activitySendMsgPo, HttpStatus.BAD_REQUEST);

        ActivitySendMsg activitySendMsg = PoToDoUtils.toUpdDO(activitySendMsgPo, ActivitySendMsg.class);
        activitySendMsgService.updateById(activitySendMsg);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(activitySendMsgService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ActivitySendMsgPo activitySendMsgPo){
        ActivitySendMsg activitySendMsg = PoToDoUtils.toAddDO(activitySendMsgPo, ActivitySendMsg.class);
        IPage<ActivitySendMsg> page = activitySendMsgService.listPage(getPage(), activitySendMsg);
        return R.SUCCESS(page);
    }
}