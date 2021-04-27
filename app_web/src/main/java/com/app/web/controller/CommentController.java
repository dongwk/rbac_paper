package com.app.web.controller;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Comment;
import com.app.service.service.CommentService;
import com.app.web.config.annotation.LoginUser;
import com.app.web.config.annotation.WechatAuthorization;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.CommentPo;
import com.app.web.po.LoginUserPo;
import com.app.web.utils.PoToDoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @WechatAuthorization
    @PostMapping
    public R<?> add(@LoginUser LoginUserPo loginUserPo, @RequestBody CommentPo commentPo){
        ThrowAssert.isNull(commentPo, HttpStatus.BAD_REQUEST);

        Comment comment = PoToDoUtils.toAddDO(commentPo, Comment.class);
        commentService.save(comment);
        return R.SUCCESS();
    }
}