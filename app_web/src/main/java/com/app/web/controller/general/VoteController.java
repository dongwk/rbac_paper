package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Vote;
import com.app.service.service.VoteService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.VotePo;
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
@RequestMapping("/vote")
public class VoteController extends BaseRestController<VoteService, Vote> {

    @Autowired
    private VoteService voteService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Vote vote = new Vote();
        IPage<Vote> page = voteService.listPage(getPage(), vote);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(voteService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody VotePo votePo){
        ThrowAssert.isNull(votePo, HttpStatus.BAD_REQUEST);

        Vote vote = PoToDoUtils.toAddDO(votePo, Vote.class);
        voteService.save(vote);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody VotePo votePo){
        ThrowAssert.isNull(votePo, HttpStatus.BAD_REQUEST);

        Vote vote = PoToDoUtils.toUpdDO(votePo, Vote.class);
        voteService.updateById(vote);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(voteService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody VotePo votePo){
        Vote vote = PoToDoUtils.toAddDO(votePo, Vote.class);
        IPage<Vote> page = voteService.listPage(getPage(), vote);
        return R.SUCCESS(page);
    }
}