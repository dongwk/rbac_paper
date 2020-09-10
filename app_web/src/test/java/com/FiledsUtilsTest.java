package com;

import com.app.web.utils.FieldsUtils;
import com.app.web.mo.LoginUserMo;
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
         Assert.assertTrue(FieldsUtils.isQuery(LoginUserMo.class, "name"));
    }
}
