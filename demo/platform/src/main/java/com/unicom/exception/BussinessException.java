/**
 * BussinessException.java 2009-11-11
 * 
 * 万程 版权所有(c) 2009 。
 */
package com.unicom.exception;

/**
 * @Author qibaichao
 * @ClassName BussinessException
 * @Date Sep 3, 2013
 * @Description:
 */
public class BussinessException extends Exception {

	private static final long	serialVersionUID	= 42717324355691506L;
	private Exception			exception;
	private String				message;
	private String				errorCode;
	private String				keyValue;

	/**
	 * <b>构造方法。</b>
	 * <p>
	 * <b>详细说明：</b>
	 * </p>
	 * <!-- 在此添加详细说明 --> 空（父类的构造方法）
	 */
	public BussinessException() {
		super();
	}

	/**
	 * <b>构造方法。</b>
	 * <p>
	 * <b>详细说明：</b>
	 * </p>
	 * <!-- 在此添加详细说明 --> 需要Exception对象e
	 * 
	 * @param e
	 */
	public BussinessException(Exception e) {
		this.exception = e;
	}

	/**
	 * <b>构造方法。</b>
	 * <p>
	 * <b>详细说明：</b>
	 * </p>
	 * <!-- 在此添加详细说明 --> 需要String类型的keyValue和errorCode
	 * 
	 * @param keyValue
	 * @param errorCode
	 */
	public BussinessException(String keyValue, String errorCode) {
		this.keyValue = keyValue;
		this.errorCode = errorCode;
	}

	/**
	 * <b>构造方法。</b>
	 * <p>
	 * <b>详细说明：</b>
	 * </p>
	 * <!-- 在此添加详细说明 --> 需要String类型的errorCode
	 * 
	 * @param errorCode
	 */
	public BussinessException(String errorCode) {
		this.errorCode = errorCode;
		this.message = errorCode;
	}

	/**
	 * <b>构造方法。</b>
	 * <p>
	 * <b>详细说明：</b>
	 * </p>
	 * <!-- 在此添加详细说明 --> 需要String类型的msg和Exception的e
	 * 
	 * @param msg
	 * @param e
	 */
	public BussinessException(String msg, Exception e) {
		this.exception = e;
		this.message = msg;
	}

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
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
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the keyValue
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue
	 *            the keyValue to set
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
}
