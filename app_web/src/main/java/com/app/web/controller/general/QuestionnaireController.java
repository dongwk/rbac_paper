package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Questionnaire;
import com.app.service.service.QuestionnaireService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.QuestionnairePo;
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
@RequestMapping("/questionnaire")
public class QuestionnaireController extends BaseRestController<QuestionnaireService, Questionnaire> {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Questionnaire questionnaire = new Questionnaire();
        IPage<Questionnaire> page = questionnaireService.listPage(getPage(), questionnaire);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(questionnaireService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody QuestionnairePo questionnairePo){
        ThrowAssert.isNull(questionnairePo, HttpStatus.BAD_REQUEST);

        Questionnaire questionnaire = PoToDoUtils.toAddDO(questionnairePo, Questionnaire.class);
        questionnaireService.save(questionnaire);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody QuestionnairePo questionnairePo){
        ThrowAssert.isNull(questionnairePo, HttpStatus.BAD_REQUEST);

        Questionnaire questionnaire = PoToDoUtils.toUpdDO(questionnairePo, Questionnaire.class);
        questionnaireService.updateById(questionnaire);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(questionnaireService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody QuestionnairePo questionnairePo){
        Questionnaire questionnaire = PoToDoUtils.toAddDO(questionnairePo, Questionnaire.class);
        IPage<Questionnaire> page = questionnaireService.listPage(getPage(), questionnaire);
        return R.SUCCESS(page);
    }
}