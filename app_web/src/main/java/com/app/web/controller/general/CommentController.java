package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Comment;
import com.app.service.service.CommentService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.CommentPo;
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
@RequestMapping("/comment")
public class CommentController extends BaseRestController<CommentService, Comment> {

    @Autowired
    private CommentService commentService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Comment comment = new Comment();
        IPage<Comment> page = commentService.listPage(getPage(), comment);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(commentService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody CommentPo commentPo){
        ThrowAssert.isNull(commentPo, HttpStatus.BAD_REQUEST);

        Comment comment = PoToDoUtils.toAddDO(commentPo, Comment.class);
        commentService.save(comment);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody CommentPo commentPo){
        ThrowAssert.isNull(commentPo, HttpStatus.BAD_REQUEST);

        Comment comment = PoToDoUtils.toUpdDO(commentPo, Comment.class);
        commentService.updateById(comment);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(commentService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody CommentPo commentPo){
        Comment comment = PoToDoUtils.toAddDO(commentPo, Comment.class);
        IPage<Comment> page = commentService.listPage(getPage(), comment);
        return R.SUCCESS(page);
    }
}