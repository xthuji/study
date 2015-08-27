package com.unicom.core;

import com.unicom.po.Po;

/**
 * @ClassName: Filter
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author qibaichao
 * @date Sep 2, 2013 1:00:34 PM
 */
public class Filter extends Po {

	/**
	 * 此处为属性说明
	 */
	private static final long	serialVersionUID	= 3647468241598238318L;
	private String				field;
	private String				value;

	public Filter(String field, String value) {
		this.field = field;
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}