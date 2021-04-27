/**
 * 
 */
package com.mybatis.hongten.bean.install;

import java.util.Set;


/**
 * @author hongten<br>
 * @date 2013-3-10
 */
public class BeanUtilsTest{
	public static void main(String[] args) throws Exception{
        BeanUtilsTest beanUtilTest = new BeanUtilsTest();
        BeanUtils beanUtils = new BeanUtils();

        Set<Class<?>> classList = ClassUtil.getClasses("com.app.model.model");

        Object[] ts =   classList.toArray();
        for(Object t:ts){
            Class<?> tt = (Class<?>) t;
            if (tt.getName().contains("Builder")) continue;
            beanUtilTest.beanTool(beanUtils, tt);
        }
    }
    
    /**
     * 根据bean生成相应的文件
     * @param beanUtils
     * @param c
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void beanTool(BeanUtils beanUtils ,Class c)throws Exception{
    	beanUtils.init(c);
        beanUtils.createController(c);
        beanUtils.createPo(c);
        beanUtils.createBeanDao(c);
        beanUtils.createBeanServiceImpl(c);
    }
}
