/**
 * 
 */
package com.business.generate;

import com.app.common.constant.Constants;
import com.business.generate.core.Attribute;
import com.business.generate.core.TemplateInfo;
import com.business.generate.enums.TemplateAttr;
import com.google.common.collect.Lists;
import com.mybatis.hongten.bean.install.ClassUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class GenerateMain {

	public void build() throws IOException, TemplateException {
        /**************** 准备 ****************/
        String beanPackage = Introduction.beanPackage; // 实体目录
        File dir = new File(Introduction.templatePath); // 模板目录
        File targetPath = new File(Introduction.targetPath); // 输出目录

        /**************** 环境 ****************/
	    // 模板
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(dir);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 预设数据
        Map<String, Object> preset = new HashMap<>();

        // 模板信息
        Map<String, TemplateInfo> templateInfoMap = Introduction.templateInfoMap;

        /**************** 业务 ****************/
        // 实体类
        Set<Class<?>> classes = ClassUtil.getClasses(beanPackage);

        // 目标目录
        FileUtils.cleanDirectory(targetPath);

        /**************** 处理 ****************/
        // 处理
        process(dir, preset, cfg, classes, beanPackage, targetPath, templateInfoMap);
    }

    // 处理
    private void process(File dir, Map<String, Object> preset, Configuration cfg,
                         Set<Class<?>> classSet, String beanPackage, File targetPath, Map<String, TemplateInfo> templateInfoMap)
            throws IOException, TemplateException {
        // 准备模板和数据
        File[] files = dir.listFiles();

        for (File tplFile : files) {
            String tName = tplFile.getName();
            if (tplFile.isDirectory()) {
                process(tplFile, preset, cfg, classSet, beanPackage, targetPath, templateInfoMap);
            } else if (tplFile.isFile()) {
                Map<String, Object> root = new HashMap<>(preset);
                for (Class<?> clazz : classSet) {
                    // 填充模板数据
                    full(clazz, root);
                    // 输出
                    out(clazz, beanPackage, templateInfoMap.get(tName), cfg.getTemplate(tName), root, targetPath);
                }
            }
        }
    }

    // 填充数据
    private void full(Class<?> clazz, Map<String, Object> root) {
        String sim = clazz.getSimpleName();
        List<Attribute> attributes = Lists.newArrayList();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            attributes.add(new Attribute(field.getName(), field.getType().getTypeName()));
        }
        // bean
        Map<String, Object> bean = new HashMap<>();
        bean.put("package", clazz.getPackage().getName());
        bean.put("name", sim);
        bean.put("attributes", attributes);
        root.put(TemplateAttr.BEAN.getName(), bean);
    }

    /**
     * 输出
     * @param clazz 实体类
     * @param beanPackage 实体类包
     * @param templateInfo 模板信息
     * @param template freemarker 模板
     * @param root freemarker 数据
     * @throws IOException
     */
    private void out(Class<?> clazz, String beanPackage, TemplateInfo templateInfo, Template template
            , Map<String, Object> root, File targetPath) throws IOException, TemplateException {
        String outExt = null;
        if (templateInfo != null) {
            outExt = templateInfo.getOutExt();
        }

        // 基本信息
        String sim = clazz.getSimpleName();
        String name = clazz.getName();

        // 实体类包的子目录
        String targetDir = name.substring(beanPackage.length(), name.lastIndexOf(Constants.DOT))
                .replace(Constants.DOT, Constants.VIRGULE);

        // 创建实体类包的子目录
        File tarPath = new File(targetPath.getPath() + targetDir);
        FileUtils.forceMkdir(tarPath);

        // 输出文件
        Writer out = null;
        try {
            out = new FileWriter( tarPath.getPath() + "/" + sim + StringUtils.defaultString(outExt));
            template.process(root, out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static void main(String[] args) {
        GenerateMain generateMain = new GenerateMain();
        try {
            generateMain.build();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
