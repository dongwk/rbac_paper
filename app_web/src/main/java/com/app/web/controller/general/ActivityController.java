package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Activity;
import com.app.service.service.ActivityService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ActivityPo;
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
@RequestMapping("/activity")
public class ActivityController extends BaseRestController<ActivityService, Activity> {

    @Autowired
    private ActivityService activityService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Activity activity = new Activity();
        IPage<Activity> page = activityService.listPage(getPage(), activity);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(activityService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ActivityPo activityPo){
        ThrowAssert.isNull(activityPo, HttpStatus.BAD_REQUEST);

        Activity activity = PoToDoUtils.toAddDO(activityPo, Activity.class);
        activityService.save(activity);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ActivityPo activityPo){
        ThrowAssert.isNull(activityPo, HttpStatus.BAD_REQUEST);

        Activity activity = PoToDoUtils.toUpdDO(activityPo, Activity.class);
        activityService.updateById(activity);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(activityService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ActivityPo activityPo){
        Activity activity = PoToDoUtils.toAddDO(activityPo, Activity.class);
        IPage<Activity> page = activityService.listPage(getPage(), activity);
        return R.SUCCESS(page);
    }
}