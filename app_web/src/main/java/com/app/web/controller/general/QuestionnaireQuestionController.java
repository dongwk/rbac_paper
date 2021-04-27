package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.QuestionnaireQuestion;
import com.app.service.service.QuestionnaireQuestionService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.QuestionnaireQuestionPo;
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
@RequestMapping("/questionnaireQuestion")
public class QuestionnaireQuestionController extends BaseRestController<QuestionnaireQuestionService, QuestionnaireQuestion> {

    @Autowired
    private QuestionnaireQuestionService questionnaireQuestionService;

    @Authorization
    @GetMapping
    public R<?> list(){
        QuestionnaireQuestion questionnaireQuestion = new QuestionnaireQuestion();
        IPage<QuestionnaireQuestion> page = questionnaireQuestionService.listPage(getPage(), questionnaireQuestion);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(questionnaireQuestionService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody QuestionnaireQuestionPo questionnaireQuestionPo){
        ThrowAssert.isNull(questionnaireQuestionPo, HttpStatus.BAD_REQUEST);

        QuestionnaireQuestion questionnaireQuestion = PoToDoUtils.toAddDO(questionnaireQuestionPo, QuestionnaireQuestion.class);
        questionnaireQuestionService.save(questionnaireQuestion);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody QuestionnaireQuestionPo questionnaireQuestionPo){
        ThrowAssert.isNull(questionnaireQuestionPo, HttpStatus.BAD_REQUEST);

        QuestionnaireQuestion questionnaireQuestion = PoToDoUtils.toUpdDO(questionnaireQuestionPo, QuestionnaireQuestion.class);
        questionnaireQuestionService.updateById(questionnaireQuestion);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(questionnaireQuestionService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody QuestionnaireQuestionPo questionnaireQuestionPo){
        QuestionnaireQuestion questionnaireQuestion = PoToDoUtils.toAddDO(questionnaireQuestionPo, QuestionnaireQuestion.class);
        IPage<QuestionnaireQuestion> page = questionnaireQuestionService.listPage(getPage(), questionnaireQuestion);
        return R.SUCCESS(page);
    }
}