package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.BusinessUserQuestionnaire;
import com.app.service.service.BusinessUserQuestionnaireService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.BusinessUserQuestionnairePo;
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
@RequestMapping("/businessUserQuestionnaire")
public class BusinessUserQuestionnaireController extends BaseRestController<BusinessUserQuestionnaireService, BusinessUserQuestionnaire> {

    @Autowired
    private BusinessUserQuestionnaireService businessUserQuestionnaireService;

    @Authorization
    @GetMapping
    public R<?> list(){
        BusinessUserQuestionnaire businessUserQuestionnaire = new BusinessUserQuestionnaire();
        IPage<BusinessUserQuestionnaire> page = businessUserQuestionnaireService.listPage(getPage(), businessUserQuestionnaire);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(businessUserQuestionnaireService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody BusinessUserQuestionnairePo businessUserQuestionnairePo){
        ThrowAssert.isNull(businessUserQuestionnairePo, HttpStatus.BAD_REQUEST);

        BusinessUserQuestionnaire businessUserQuestionnaire = PoToDoUtils.toAddDO(businessUserQuestionnairePo, BusinessUserQuestionnaire.class);
        businessUserQuestionnaireService.save(businessUserQuestionnaire);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody BusinessUserQuestionnairePo businessUserQuestionnairePo){
        ThrowAssert.isNull(businessUserQuestionnairePo, HttpStatus.BAD_REQUEST);

        BusinessUserQuestionnaire businessUserQuestionnaire = PoToDoUtils.toUpdDO(businessUserQuestionnairePo, BusinessUserQuestionnaire.class);
        businessUserQuestionnaireService.updateById(businessUserQuestionnaire);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(businessUserQuestionnaireService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody BusinessUserQuestionnairePo businessUserQuestionnairePo){
        BusinessUserQuestionnaire businessUserQuestionnaire = PoToDoUtils.toAddDO(businessUserQuestionnairePo, BusinessUserQuestionnaire.class);
        IPage<BusinessUserQuestionnaire> page = businessUserQuestionnaireService.listPage(getPage(), businessUserQuestionnaire);
        return R.SUCCESS(page);
    }
}