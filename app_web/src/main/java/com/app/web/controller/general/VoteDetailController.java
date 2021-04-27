package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.VoteDetail;
import com.app.service.service.VoteDetailService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.VoteDetailPo;
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
@RequestMapping("/voteDetail")
public class VoteDetailController extends BaseRestController<VoteDetailService, VoteDetail> {

    @Autowired
    private VoteDetailService voteDetailService;

    @Authorization
    @GetMapping
    public R<?> list(){
        VoteDetail voteDetail = new VoteDetail();
        IPage<VoteDetail> page = voteDetailService.listPage(getPage(), voteDetail);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(voteDetailService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody VoteDetailPo voteDetailPo){
        ThrowAssert.isNull(voteDetailPo, HttpStatus.BAD_REQUEST);

        VoteDetail voteDetail = PoToDoUtils.toAddDO(voteDetailPo, VoteDetail.class);
        voteDetailService.save(voteDetail);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody VoteDetailPo voteDetailPo){
        ThrowAssert.isNull(voteDetailPo, HttpStatus.BAD_REQUEST);

        VoteDetail voteDetail = PoToDoUtils.toUpdDO(voteDetailPo, VoteDetail.class);
        voteDetailService.updateById(voteDetail);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(voteDetailService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody VoteDetailPo voteDetailPo){
        VoteDetail voteDetail = PoToDoUtils.toAddDO(voteDetailPo, VoteDetail.class);
        IPage<VoteDetail> page = voteDetailService.listPage(getPage(), voteDetail);
        return R.SUCCESS(page);
    }
}