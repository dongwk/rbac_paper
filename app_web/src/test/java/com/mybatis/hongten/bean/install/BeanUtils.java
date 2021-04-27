/**
 *
 */
package com.mybatis.hongten.bean.install;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;


/**
 * @author hongten<br>
 * @date 2013-3-10
 */
public class BeanUtils {

	public static final String BEAN_DAO_TEMPLATE_VM_PATH = "com/mybatis/hongten/vms/beanDao.vm";
	public static final String BEAN_DAO_IMPL_TEMPLATE_VM_PATH = "com/mybatis/hongten/vms/beanDaoImpl.vm";
	public static final String BEAN_SERVICE_TEMPLATE_VM_PATH = "com/mybatis/hongten/vms/beanService.vm";
	public static final String BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH = "com/mybatis/hongten/vms/beanServiceImpl.vm";
    public static final String BEAN_CONTROLLER_TEMPLATE_VM_PATH = "com/mybatis/hongten/vms/beanController.vm";
    public static final String BEAN_PO_TEMPLATE_VM_PATH = "com/mybatis/hongten/vms/beanPo.vm";

	 //文件 地址
    //private static final String BEAN_PATH = "com/b510/base/bean";
    private static final String DAO_PATH = "com/app/mapper/mapper";
    private static final String DAO_IMPL_PATH = "com/b510/base/dao/impl";
    private static final String SERVICE_PATH = "com/b510/base/service";
    private static final String SERVICE_IMPL_PATH = "com/app/service/service";
    private static final String CONTROLLER_PATH = "com/app/api/controller/general";
    private static final String PO_PATH = "com/app/api/po/general";



    //包名
    private static final String BEAN_URL = "com.b510.base.bean";
    private static final String DAO_URL = "com.app.mapper.mapper.general";
    private static final String DAO_IMPL_URL = "com.b510.base.dao.impl";
    private static final String SERVICE_URL = "com.app.service.service.general";
    private static final String SERVICE_IMPL_URL = "com.app.service.service.general";
    private static final String CONTROLLER_URL = "com.app.web.controller.general";
    private static final String PO_URL = "com.app.web.po.general";


    private static Bean bean = new Bean();
    private static Annotation annotation = new Annotation();

    /**
     * 初始化bean和注解
     * @param c
     */
    public void init(Class c){
    	if(c != null){
    		String cName = c.getName();
    		bean.setName(getLastChar(cName));
    		bean.setBeanUrl(cName);
    		bean.setLowerName(getLowercaseChar(getLastChar(cName)));

    		annotation.setAuthorName("dongwk");
            annotation.setAuthorMail("hongtenzone@foxmail.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
    				"yyyy-MM-dd");
    		annotation.setDate(simpleDateFormat.format(new Date()));
    		annotation.setVersion("1.0");
    	}
    }

    /**
     * 创建bean的Dao<br>
     *
     * @param c
     * @throws Exception
     */
    public void createBeanDao(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + DAO_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Mapper.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanDaoUrl(DAO_URL);

        fw.write(createCode(BEAN_DAO_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    /**
     * 创建bean的Dao的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void createBeanDaoImpl(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + DAO_IMPL_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "DaoImpl.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanDaoUrl(DAO_URL);
        bean.setBeanDaoImplUrl(DAO_IMPL_URL);

        fw.write(createCode(BEAN_DAO_IMPL_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }


    /**
     * 创建bean的Service<br>
     *
     * @param c
     * @throws Exception
     */
    public void createBeanService(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + SERVICE_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Service.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanServiceUrl(SERVICE_URL);

        fw.write(createCode(BEAN_SERVICE_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }

    /**
     * 创建bean的Service的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void createBeanServiceImpl(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + SERVICE_IMPL_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Service.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanDaoUrl(DAO_URL);
        bean.setBeanServiceUrl(SERVICE_URL);
        bean.setBeanServiceImplUrl(SERVICE_IMPL_URL);

        fw.write(createCode(BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH,bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }


    /**
     * 创建bean的Service的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void createController(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + CONTROLLER_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Controller.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        bean.setBeanControllerUrl(CONTROLLER_URL);

        fw.write(createCode(BEAN_CONTROLLER_TEMPLATE_VM_PATH, bean,annotation));
        fw.flush();
        fw.close();
        showInfo(fileName);
    }




    /**
     * 创建bean的Service的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void createPo(Class c) throws Exception {
        String cName = c.getName();
        String path = System.getProperty("user.dir") + "/src/" + PO_PATH
                + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        StringBuilder content = new StringBuilder();
        File modelFilePath = new File(System.getProperty("user.dir") + "/app_model/src/main/java/" + c.getName().replace(".", "/")+".java");
        List<String> list = IOUtils.readLines(new FileReader(modelFilePath));
        boolean isHeadClass = false;
        for (String s : list) {
            if (s.startsWith("package")) {
                s = s.replace(s, "package "+PO_URL+";");
            }
            if (s.startsWith("@TableName")) {
                continue;
            }
            if (!isHeadClass) {
                s = s.replace("表", "表po");
            }

            if (s.indexOf(" class ") > -1) {

                s = s.replace(getLastChar(cName), getLastChar(cName)+"Po");
                isHeadClass = true;
            }

            s = s + "\r\n";
            content.append(s);
        }


        String fileName = path + getLastChar(cName) + "Po.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        fw.write(content.toString());
        fw.flush();
        fw.close();
        showInfo(fileName);
    }








    /**
     * 根据模板生成代码
     * @param fileVMPath 模板路径
     * @param bean 目标bean
     * @param annotation 注释
     * @return
     * @throws Exception
     */
    public String createCode(String fileVMPath,Bean bean,Annotation annotation) throws Exception{
    	VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("input.encoding", "UTF-8");
		velocityEngine.setProperty("output.encoding", "UTF-8");
        Properties p = new Properties();
        p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "D:\\project\\work\\rbac_paper\\app_web\\src\\test\\java\\");
        velocityEngine.init(p);
		Template template = velocityEngine.getTemplate(fileVMPath);
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("bean", bean);
		velocityContext.put("annotation", annotation);
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		return stringWriter.toString();
    }

	 /**
     * 创建文件
     * @param file
     */
    public void createFilePath(File file){
    	if(!file.exists()){
            System.out.println("创建["+file.getAbsolutePath()+"]情况："+file.mkdirs());
        }else{
            System.out.println("存在目录："+file.getAbsolutePath());
        }
    }




    /**
     * 获取路径的最后面字符串<br>
     * 如：<br>
     *     <code>str = "com.b510.base.bean.User"</code><br>
     *     <code> return "User";<code>
     * @param str
     * @return
     */
    public String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }

    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     *     <code>str = "UserDao";</code><br>
     *     <code>return "userDao";</code>
     * @param str
     * @return
     */
    public String getLowercaseChar(String str){
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }

    /**
     * 显示信息
     * @param info
     */
    public void showInfo(String info){
        System.out.println("创建文件："+ info+ "成功！");
    }

    /**
     * 获取系统时间
     * @return
     */
    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
