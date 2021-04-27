/**
 * 
 */
package com.app.web.controller.base;

import com.app.web.constant.WebConstants;
import com.app.web.utils.RequestUtils;
import com.app.common.constant.Constants;
import com.app.common.util.ValidateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    public Page getPage(){
        Page page = (Page) request.getAttribute(WebConstants.DEFAULT_PAGE_NAME_ATTR);
        if (page != null) {
            return page;
        }
        return null;
    }

    protected int getPageCurrent() {
        Page page = getPage();
        if (page != null) {
            return (int)page.getCurrent();
        }
        return Constants.INT_0;
    }

    protected int getPageSize(){
        Page page = getPage();
        if (page != null) {
            return (int)page.getSize();
        }
        return Constants.INT_0;
    }

    protected Page setPageRecords(List<?> list){
        Page page = getPage();
        page.setRecords(list);
        return page;
    }

    public void enablePage() {
        PageHelper.startPage(getPageCurrent(), getPageSize());
    }

    public void setPage() {
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("page_size");
        String startStr = request.getParameter("start");
        String limitStr = request.getParameter("limit");

        Page p = null;
        if (StringUtils.isNotBlank(pageStr) && ValidateUtil.isInteger(pageStr.trim())
                && StringUtils.isNotBlank(pageSizeStr) && ValidateUtil.isInteger(pageSizeStr.trim())) {
            int page = Integer.parseInt(pageStr);
            int pageSize = Integer.parseInt(pageSizeStr);

            page = ValidateUtil.isIntGtZero(page) ? page : 1;
            pageSize = ValidateUtil.isIntGtZero(pageSize) ? pageSize : Constants.DEF_PAGE_SIZE;
            p = new Page(page, pageSize);
        } else if (StringUtils.isNotBlank(startStr) && ValidateUtil.isInteger(startStr.trim())
                && StringUtils.isNotBlank(limitStr) && ValidateUtil.isInteger(limitStr.trim())) {
            int start = Integer.parseInt(startStr);
            int limit = Integer.parseInt(limitStr);

            start = ValidateUtil.isIntGtZero(start) ? start : 0;
            limit = ValidateUtil.isIntGtZero(limit) ? limit : Constants.DEF_PAGE_SIZE;
            p = new Page(start, limit);
        } else {
            p = new Page(Constants.INT_0, Constants.DEF_PAGE_SIZE);
        }
        request.setAttribute(WebConstants.DEFAULT_PAGE_NAME_ATTR, p);
    }
}