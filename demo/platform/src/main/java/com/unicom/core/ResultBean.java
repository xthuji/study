package com.unicom.core;

import java.io.Serializable;

/**
 * @author wangxiaobo
 */
public class ResultBean implements Serializable {

	/**
     * 
     */
	private static final long	serialVersionUID	= 8400341083688596453L;

	/**
     * 
     */
	private String				status;

	/**
     * 
     */
	private String				message;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 成功
	 */
	public void success() {
		this.status = Result.success.toString();
	}

	/**
	 * 失败
	 * 
	 * @param message
	 */
	public void failure(String message) {
		this.status = Result.failure.toString();
		this.message = message;
	}
}
