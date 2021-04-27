/**
 * 
 */
package com.mybatis.hongten.bean.install;

/**
 * 注释
 * @author hongten<br>
 * @date 2013-3-10
 */
public class Annotation {

	/**
	 * 作者名称
	 */
	private String authorName;
	/**
	 * 作者邮箱
	 */
	private String authorMail;
	/**
	 * 日期
	 */
	private String date;
	/**
	 * 版本
	 */
	private String version;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorMail() {
		return authorMail;
	}

	public void setAuthorMail(String authorMail) {
		this.authorMail = authorMail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	
}
