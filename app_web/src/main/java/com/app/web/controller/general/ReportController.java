package com.app.web.controller.general;

import com.app.common.web.result.R;
import com.app.core.common.ThrowAssert;
import com.app.model.model.Report;
import com.app.service.service.ReportService;
import com.app.web.config.annotation.Authorization;
import com.app.web.config.annotation.LoginUser;
import com.app.web.controller.base.BaseRestController;
import com.app.web.po.LoginUserPo;
import com.app.web.po.general.ReportPo;
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
@RequestMapping("/report")
public class ReportController extends BaseRestController<ReportService, Report> {

    @Autowired
    private ReportService reportService;

    @Authorization
    @GetMapping
    public R<?> list(){
        Report report = new Report();
        IPage<Report> page = reportService.listPage(getPage(), report);
        return R.SUCCESS(page);
    }

    @Authorization
    @GetMapping("/{id}")
    public R<?> get(@PathVariable("id") Integer id){
        return R.SUCCESS(reportService.get(id));
    }

    @Authorization
    @PostMapping
    public R<?> add(@RequestBody ReportPo reportPo){
        ThrowAssert.isNull(reportPo, HttpStatus.BAD_REQUEST);

        Report report = PoToDoUtils.toAddDO(reportPo, Report.class);
        reportService.save(report);
        return R.SUCCESS();
    }

    @Authorization
    @PutMapping
    public R<?> put(@RequestBody ReportPo reportPo){
        ThrowAssert.isNull(reportPo, HttpStatus.BAD_REQUEST);

        Report report = PoToDoUtils.toUpdDO(reportPo, Report.class);
        reportService.updateById(report);
        return R.SUCCESS();
    }

    @Authorization
    @DeleteMapping("/{id}")
    public R<?> delete(@PathVariable("id") Integer id){
        ThrowAssert.isNull(id, HttpStatus.BAD_REQUEST);

        return R.SUCCESS(reportService.removeById(id));
    }

    @Authorization
    @GetMapping("/query")
    public R<?> query(@LoginUser LoginUserPo loginUserPo, @RequestBody ReportPo reportPo){
        Report report = PoToDoUtils.toAddDO(reportPo, Report.class);
        IPage<Report> page = reportService.listPage(getPage(), report);
        return R.SUCCESS(page);
    }
}