package com.hj.test.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.hj.test.bean.xml.Person;

public class Xml_Bean_Convertor {
	
	private static Gson gson = new Gson();

	/**
	 * 
	 * @param obj
	 *            XML注解对象
	 * @param encode
	 *            输出的字符串编码
	 * @param isFormat
	 *            是否格式化XML
	 * @return
	 */
	public static <T> String objectToXmlStr(T obj, String encode,
			boolean isFormat) {
		String result = "";
		StringWriter stringWriter = null;
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_ENCODING, encode);// 编码格式

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);// 是否格式化生成的xml串
//			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE); 
			stringWriter = new StringWriter();

			marshaller.marshal(obj, stringWriter);

			result = stringWriter.toString();

		} catch (JAXBException e) {
			e.printStackTrace();
		} finally {

			if (stringWriter != null) {
				try {
					stringWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param clazz
	 *            XML注解过的CLASS
	 * @param xml
	 *            标准XML格式字符串
	 * @param <T>
	 *            对象类型
	 * @return 返回对象
	 */
	public static <T> T xmlToObject(Class clazz, String xml, String encode) {

		if (StringUtils.isEmpty(xml)) {
			return null;
		}

		InputStream in = null;

		try {
			in = new ByteArrayInputStream(xml.getBytes(encode));

			JAXBContext context = JAXBContext.newInstance(clazz);

			Unmarshaller um = context.createUnmarshaller();

			return (T) um.unmarshal(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>\n" +
				"<Person>\n" +
				"    <UserName>Jack</UserName>\n" +
				"    <Age>11</Age>\n" +
				"</Person>";
		Person person = xmlToObject(Person.class, xmlString, "GBK");
		System.out.println(gson.toJson(person));
		String xml = objectToXmlStr(person, "GBK", true);
		System.out.println(xml);
		
//		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><VertifyUserState2APRsp><APTransactionID>aaaa</APTransactionID><ResultCode>0000</ResultCode><ResultMSG>tttt</ResultMSG><RspTime>mmmm</RspTime></VertifyUserState2APRsp>";
//
//		ConfirmResBean bean = xmlToObject(ConfirmResBean.class, xml, "GBK");
//		System.out.println(gson.toJson(bean));

	}
}
