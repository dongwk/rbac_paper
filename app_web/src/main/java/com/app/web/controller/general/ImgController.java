package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Img;
import com.app.service.service.ImgService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ImgPo;
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
@RequestMapping("/img")
public class ImgController extends BaseRestController<ImgService, Img> {

    @Autowired
    private ImgService imgService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Img img = new Img();
        IPage<Img> page = imgService.listPage(getPage(), img);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(imgService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ImgPo imgPo){
        ThrowAssert.isNull(imgPo, HttpStatus.BAD_REQUEST);

        Img img = PoToDoUtils.toAddDO(imgPo, Img.class);
        imgService.save(img);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ImgPo imgPo){
        ThrowAssert.isNull(imgPo, HttpStatus.BAD_REQUEST);

        Img img = PoToDoUtils.toUpdDO(imgPo, Img.class);
        imgService.updateById(img);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(imgService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ImgPo imgPo){
        Img img = PoToDoUtils.toAddDO(imgPo, Img.class);
        IPage<Img> page = imgService.listPage(getPage(), img);
        return R.SUCCESS(page);
    }
}