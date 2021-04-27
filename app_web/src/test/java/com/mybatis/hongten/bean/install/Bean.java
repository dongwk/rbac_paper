/**
 * 
 */
package com.mybatis.hongten.bean.install;

import lombok.Data;

/**
 * bean类
 * 
 * @author hongten<br>
 * @date 2013-3-10
 */
@Data
public class Bean {

	/** bean 名称 */
	private String name;
	/** bean 首字母小写名称 */
	private String lowerName;
	/** bean 路径 */
	private String beanUrl;
	/** dao 路径 */
	private String beanDaoUrl;
	/** dao 实现路径 */
	private String beanDaoImplUrl;
	/** service 路径 */
	private String beanServiceUrl;
	/** service 实现路径 */
	private String beanServiceImplUrl;
	/** po 实现路径 */
	private String beanPoUrl;
	/** controller 实现路径 */
	private String beanControllerUrl;

}
