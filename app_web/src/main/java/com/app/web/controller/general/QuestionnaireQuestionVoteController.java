package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.QuestionnaireQuestionVote;
import com.app.service.service.QuestionnaireQuestionVoteService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.QuestionnaireQuestionVotePo;
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
@RequestMapping("/questionnaireQuestionVote")
public class QuestionnaireQuestionVoteController extends BaseRestController<QuestionnaireQuestionVoteService, QuestionnaireQuestionVote> {

    @Autowired
    private QuestionnaireQuestionVoteService questionnaireQuestionVoteService;

    @Authorization
    @GetMapping
    public R<?> list(){
        QuestionnaireQuestionVote questionnaireQuestionVote = new QuestionnaireQuestionVote();
        IPage<QuestionnaireQuestionVote> page = questionnaireQuestionVoteService.listPage(getPage(), questionnaireQuestionVote);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(questionnaireQuestionVoteService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody QuestionnaireQuestionVotePo questionnaireQuestionVotePo){
        ThrowAssert.isNull(questionnaireQuestionVotePo, HttpStatus.BAD_REQUEST);

        QuestionnaireQuestionVote questionnaireQuestionVote = PoToDoUtils.toAddDO(questionnaireQuestionVotePo, QuestionnaireQuestionVote.class);
        questionnaireQuestionVoteService.save(questionnaireQuestionVote);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody QuestionnaireQuestionVotePo questionnaireQuestionVotePo){
        ThrowAssert.isNull(questionnaireQuestionVotePo, HttpStatus.BAD_REQUEST);

        QuestionnaireQuestionVote questionnaireQuestionVote = PoToDoUtils.toUpdDO(questionnaireQuestionVotePo, QuestionnaireQuestionVote.class);
        questionnaireQuestionVoteService.updateById(questionnaireQuestionVote);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(questionnaireQuestionVoteService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody QuestionnaireQuestionVotePo questionnaireQuestionVotePo){
        QuestionnaireQuestionVote questionnaireQuestionVote = PoToDoUtils.toAddDO(questionnaireQuestionVotePo, QuestionnaireQuestionVote.class);
        IPage<QuestionnaireQuestionVote> page = questionnaireQuestionVoteService.listPage(getPage(), questionnaireQuestionVote);
        return R.SUCCESS(page);
    }
}