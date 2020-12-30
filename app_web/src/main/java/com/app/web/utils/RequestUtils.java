package com.app.web.utils;

import com.app.common.util.ValidateUtil;
import com.app.web.constant.WebConstants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    /**
     * 分页请求头处理
     * @return
     */
    public static Page getPage(HttpServletRequest request){
        String pageStr = request.getParameter("page");
        String pageSizeStr = request.getParameter("page_size");
        int page = 0;
        int pageSize = 0;

        if (StringUtils.isNotBlank(pageStr) && ValidateUtil.isInteger(pageStr)
                && StringUtils.isNotBlank(pageSizeStr) && ValidateUtil.isInteger(pageSizeStr)) {
            page = Integer.parseInt(pageStr);
            pageSize = Integer.parseInt(pageSizeStr);
            page = ValidateUtil.isIntGtZero(page) ? page : 1;
            pageSize = ValidateUtil.isIntGtZero(pageSize) ? pageSize : 1;
        }
        return new Page(page <= 1 ? 0 : (page-1)*pageSize, pageSize < 1 ? WebConstants.DEFAULT_PAGE_SIZE : pageSize);
    }
}
