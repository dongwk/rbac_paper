package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.BusinessUserVoteDetail;
import com.app.service.service.BusinessUserVoteDetailService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.BusinessUserVoteDetailPo;
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
@RequestMapping("/businessUserVoteDetail")
public class BusinessUserVoteDetailController extends BaseRestController<BusinessUserVoteDetailService, BusinessUserVoteDetail> {

    @Autowired
    private BusinessUserVoteDetailService businessUserVoteDetailService;

    @Authorization
    @GetMapping
    public R<?> list(){
        BusinessUserVoteDetail businessUserVoteDetail = new BusinessUserVoteDetail();
        IPage<BusinessUserVoteDetail> page = businessUserVoteDetailService.listPage(getPage(), businessUserVoteDetail);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(businessUserVoteDetailService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody BusinessUserVoteDetailPo businessUserVoteDetailPo){
        ThrowAssert.isNull(businessUserVoteDetailPo, HttpStatus.BAD_REQUEST);

        BusinessUserVoteDetail businessUserVoteDetail = PoToDoUtils.toAddDO(businessUserVoteDetailPo, BusinessUserVoteDetail.class);
        businessUserVoteDetailService.save(businessUserVoteDetail);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody BusinessUserVoteDetailPo businessUserVoteDetailPo){
        ThrowAssert.isNull(businessUserVoteDetailPo, HttpStatus.BAD_REQUEST);

        BusinessUserVoteDetail businessUserVoteDetail = PoToDoUtils.toUpdDO(businessUserVoteDetailPo, BusinessUserVoteDetail.class);
        businessUserVoteDetailService.updateById(businessUserVoteDetail);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(businessUserVoteDetailService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody BusinessUserVoteDetailPo businessUserVoteDetailPo){
        BusinessUserVoteDetail businessUserVoteDetail = PoToDoUtils.toAddDO(businessUserVoteDetailPo, BusinessUserVoteDetail.class);
        IPage<BusinessUserVoteDetail> page = businessUserVoteDetailService.listPage(getPage(), businessUserVoteDetail);
        return R.SUCCESS(page);
    }
}