package com.app.web.vo;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.Data;

import java.util.List;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/12/7 10:16
 */
@Data
public class PageVo<T> {
    private Integer page;
    private Integer perPage;
    private Integer totalPage;
    private Integer totalSize;
    private List<T> data;

    public static <M> PageVo<M> parseMybatisPlusPage(Page<M> page){
        PageVo<M> pageVo = new PageVo<>();
        pageVo.setPage(page.getCurrent());
        pageVo.setPerPage(page.getSize());
        pageVo.setTotalPage(page.getPages());
        pageVo.setTotalSize(page.getTotal());
        pageVo.setData(page.getRecords());
        return pageVo;
    }
}
