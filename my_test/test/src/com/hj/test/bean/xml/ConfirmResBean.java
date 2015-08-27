package com.hj.test.bean.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA.
 * User: huji
 * Date: 14-7-4
 * Time: 下午6:28
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "VertifyUserState2APRsp")
public class ConfirmResBean {

	public String APTransactionID ;
	public String ResultCode ;
	public String ResultMSG ;
	public String RspTime ;

	

	public ConfirmResBean() {
		super();
	}

}
