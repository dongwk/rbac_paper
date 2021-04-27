package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.CommentFavor;
import com.app.service.service.CommentFavorService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.CommentFavorPo;
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
@RequestMapping("/commentFavor")
public class CommentFavorController extends BaseRestController<CommentFavorService, CommentFavor> {

    @Autowired
    private CommentFavorService commentFavorService;

    @Authorization
    @GetMapping
    public R<?> list(){
        CommentFavor commentFavor = new CommentFavor();
        IPage<CommentFavor> page = commentFavorService.listPage(getPage(), commentFavor);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(commentFavorService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody CommentFavorPo commentFavorPo){
        ThrowAssert.isNull(commentFavorPo, HttpStatus.BAD_REQUEST);

        CommentFavor commentFavor = PoToDoUtils.toAddDO(commentFavorPo, CommentFavor.class);
        commentFavorService.save(commentFavor);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody CommentFavorPo commentFavorPo){
        ThrowAssert.isNull(commentFavorPo, HttpStatus.BAD_REQUEST);

        CommentFavor commentFavor = PoToDoUtils.toUpdDO(commentFavorPo, CommentFavor.class);
        commentFavorService.updateById(commentFavor);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(commentFavorService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody CommentFavorPo commentFavorPo){
        CommentFavor commentFavor = PoToDoUtils.toAddDO(commentFavorPo, CommentFavor.class);
        IPage<CommentFavor> page = commentFavorService.listPage(getPage(), commentFavor);
        return R.SUCCESS(page);
    }
}