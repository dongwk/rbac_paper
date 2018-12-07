package com;

import com.app.model.model.User;
import com.app.web.util.FieldsUtils;
import com.app.web.vo.UserVo;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author dongwk
 * @version 1.0
 * @date 2018/12/6 15:45
 */
public class FiledsUtilsTest {

    @Test
    public void testQuery(){
         Assert.assertTrue(FieldsUtils.isQuery(UserVo.class, "name"));
    }
}
