package com.hj.test.work.letv;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class RPCServerClientTest {

	/**
	 * RPC调用AXIS2 webservice
	 * 
	 * @param endpoint
	 *            服务地址 如：http://192.168.0.1:2597/aixs2/services/jqservice?wsdl
	 * @param localPart
	 *            方法名 如<xs:element name="Receive">
	 * @param opArgs
	 *            方法参数 如Object[] opArgs = new Object[] { param };
	 * @param namespaceURI
	 *            命名空间 如 ：targetNamespace="http://server.test.com.cn">
	 * @param opReturnType
	 *            返回类型 如字符串：Class[] opReturnType = new Class[] { String[].class
	 *            };
	 */
	public static String axis2RPCInvoke(String endpoint, String localPart,
			Object[] opArgs, String namespaceURI, Class[] opReturnType) {
		Object[] ret = null;
		try {
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference(endpoint);
			options.setTo(targetEPR);
			QName opQName = new QName(namespaceURI, localPart);
			ret = serviceClient.invokeBlocking(opQName, opArgs, opReturnType);
			System.out.println(((String[]) ret[0])[0]);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return ((String[]) ret[0])[0];
	}

	public static void main(String[] args) throws AxisFault {
//		axis2RPCInvoke("http://172.16.5.22:9090/axis2/services/MyService?wsdl",
//				"Receive", new Object[] { "122" }, "http://lincb.com",
//				new Class[] { String[].class });
		callWebService("");
	}
	
	public static String callWebService(String xml) throws AxisFault{
    	
        String rerurnData="";
    	try {
			String url = "http://223.72.238.227:7003/services/WKWebService?wsdl";
			//String url = ToolUtil.getProperties("info.properties", "url");
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			EndpointReference targetEPR = new EndpointReference(url);
			options.setTransportInProtocol("SOAP");
			options.setAction("services");
			options.setTo(targetEPR);
			int delay=Integer.valueOf("2000000").intValue();
			options.setTimeOutInMilliSeconds(delay);
			QName opAddEntry = new QName("http://impl.service.tax.inspur","doService");
			//String xml ="11111112222222222333333333334444444444555555555666666666";
			 rerurnData = (String) serviceClient.invokeBlocking(opAddEntry,new Object[] { xml.toString() }, new Class[] { String.class })[0];
			System.out.println("rerurnData---"+rerurnData);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    	return rerurnData;
    	
    }

}
