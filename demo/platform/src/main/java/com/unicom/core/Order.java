package com.unicom.core;

import org.springframework.util.StringUtils;

import com.unicom.po.Po;

/**
 * @Author qibaichao
 * @ClassName Order
 * @Date Sep 9, 2013
 * @Description:排序类
 */
public class Order extends Po {

	public static final String	DEFAULT_ORDER_DESC	= "desc";
	public static final String	DEFAULT_ORDER_ASC	= "asc";
	private static final long	serialVersionUID	= 634561146077661620L;
	private String				field;
	private String				sort;

	public Order() {
	}

	public Order(String field) {
		this.field = field;
	}

	public Order(String field, String sort) {
		this.field = field;
		this.sort = sort;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSort() {
		if (StringUtils.isEmpty(sort)) {
			sort = DEFAULT_ORDER_DESC;
		}
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getTogger() {
		if (DEFAULT_ORDER_DESC.equals(sort)) {
			return DEFAULT_ORDER_ASC;
		}
		return DEFAULT_ORDER_DESC;
	}

}