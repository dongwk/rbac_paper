package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.BusinessUserQuestionnaireQ;
import com.app.service.service.BusinessUserQuestionnaireQService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.BusinessUserQuestionnaireQPo;
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
@RequestMapping("/businessUserQuestionnaireQ")
public class BusinessUserQuestionnaireQController extends BaseRestController<BusinessUserQuestionnaireQService, BusinessUserQuestionnaireQ> {

    @Autowired
    private BusinessUserQuestionnaireQService businessUserQuestionnaireQService;

    @Authorization
    @GetMapping
    public R<?> list(){
        BusinessUserQuestionnaireQ businessUserQuestionnaireQ = new BusinessUserQuestionnaireQ();
        IPage<BusinessUserQuestionnaireQ> page = businessUserQuestionnaireQService.listPage(getPage(), businessUserQuestionnaireQ);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(businessUserQuestionnaireQService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody BusinessUserQuestionnaireQPo businessUserQuestionnaireQPo){
        ThrowAssert.isNull(businessUserQuestionnaireQPo, HttpStatus.BAD_REQUEST);

        BusinessUserQuestionnaireQ businessUserQuestionnaireQ = PoToDoUtils.toAddDO(businessUserQuestionnaireQPo, BusinessUserQuestionnaireQ.class);
        businessUserQuestionnaireQService.save(businessUserQuestionnaireQ);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody BusinessUserQuestionnaireQPo businessUserQuestionnaireQPo){
        ThrowAssert.isNull(businessUserQuestionnaireQPo, HttpStatus.BAD_REQUEST);

        BusinessUserQuestionnaireQ businessUserQuestionnaireQ = PoToDoUtils.toUpdDO(businessUserQuestionnaireQPo, BusinessUserQuestionnaireQ.class);
        businessUserQuestionnaireQService.updateById(businessUserQuestionnaireQ);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(businessUserQuestionnaireQService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody BusinessUserQuestionnaireQPo businessUserQuestionnaireQPo){
        BusinessUserQuestionnaireQ businessUserQuestionnaireQ = PoToDoUtils.toAddDO(businessUserQuestionnaireQPo, BusinessUserQuestionnaireQ.class);
        IPage<BusinessUserQuestionnaireQ> page = businessUserQuestionnaireQService.listPage(getPage(), businessUserQuestionnaireQ);
        return R.SUCCESS(page);
    }
}