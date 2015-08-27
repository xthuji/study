package com.unicom.po;

import java.io.Serializable;

/**
 * @ClassName: Po
 * @Description: 頁面展示基礎po
 * @author qibaichao
 * @date Sep 2, 2013 10:44:28 AM
 */
public class Po implements Serializable {

	private static final long	serialVersionUID	= -5212766449726968132L;
	
	private String				errorMsg;

	private int					limit;

	private int					offset;

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
