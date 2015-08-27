package com.work.test.tools;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;

/**
 * Created by IntelliJ IDEA.
 * User: huji
 * Date: 14-6-16
 * Time: 下午3:56
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtil {
	public static Map<String, String> parseXml(String xml){
		Map<String, String> map = new TreeMap<String, String>();
		if(StringUtils.isEmpty(xml)){
			return map;
		}
		if(!StringUtils.isEmpty(xml)){
			Document document = null;
			try {
				document = DocumentHelper.parseText(xml);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			Element root = document.getRootElement();
			Element foo;
			Iterator it = root.elementIterator();
			while(it.hasNext()) {
				foo = (Element) it.next();
				String name = foo.getQualifiedName();
				String value = foo.getStringValue();
				if(StringUtils.isEmpty(value)){
					value = "null";
				}
				map.put(name,value);
			}
		}
		return map;
	}

	public static void main(String [] args ){
		String str = "<?xml version=\"1.0\" encoding=\"GBK\" ?>\n" +
				"<VertifyUserState2APReq>\n" +
				"\t<APTransactionID>交易流水号</APTransactionID>\n" +
				"\t<APId>企业代码</APId>\n" +
				"\t<ServiceId>业务代码</ServiceId>\n" +
				"\t<ServiceType>业务类型</ServiceType>\n" +
				"\t<ChannelId>渠道代码</ChannelId>\n" +
				"\t<APContentId>AP内容代码</APContentId>\n" +
				"\t<APUserId>合作方用户id</APUserId>\n" +
				"\t<Msisdn>手机号伪码</Msisdn>\n" +
				"\t<Province>省份</Province>\n" +
				"\t<OrderType>订购类型</OrderType>\n" +
				"\t<Backup1>备用字段1</Backup1>\n" +
				"\t<Backup2>备用字段2</Backup2>\n" +
				"\t<Actiontime>交易发起时间</Actiontime>\n" +
				"\t<method>合作方处理方法</method>\n" +
				"\t<signMethod>签名方法</signMethod>\n" +
				"\t<sign>签名结果</sign>\n" +
				"</VertifyUserState2APReq>";
		Map map = parseXml(str);
		Gson gson = new Gson();
		System.out.println(gson.toJson(map));
	}
}
