package com.app.core.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 *
 * @author dongwk
 * @date 2020-09-10 10:39
 **/
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MapResultPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();  
	}

	@Override
	public Object plugin(Object target) {
        return Plugin.wrap(target, this);  
	}

	@Override
	public void setProperties(Properties properties) {
		
	}
}