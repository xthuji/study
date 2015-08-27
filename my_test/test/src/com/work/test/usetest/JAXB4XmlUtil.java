package com.work.test.usetest;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.google.gson.Gson;
import com.work.test.bean.xml.Bar;
import com.work.test.bean.xml.Person;

public class JAXB4XmlUtil {
	private static Gson gson = new Gson();

	/**
	 * @param args
	 * @throws JAXBException
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws JAXBException,
			FileNotFoundException, UnsupportedEncodingException {
		Person p = new Person("Jack", "11");

		String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?>\n"
				+ "<Person>\n"
				+ "    <UserName>Jack</UserName>\n"
				+ "    <Age>11</Age>\n" + "</Person>";

		JAXBContext ctx = JAXBContext.newInstance(Person.class);
		toXmlFile(ctx, p);
		toXmlString(ctx, p);
		fromXmlFile(ctx);
		fromXmlString(ctx, xmlString);

	}

	public static void toXmlFile(JAXBContext ctx, Person p)
			throws JAXBException, FileNotFoundException {
		Marshaller ms = ctx.createMarshaller();
		ms.setProperty(Marshaller.JAXB_ENCODING, "GBK");// 编码格式
		ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串

		FileOutputStream fo = new FileOutputStream(new File("src/Person.xml"));
		ms.marshal(p, fo);
	}

	public static String toXmlString(JAXBContext ctx, Person p)
			throws JAXBException {
		Marshaller ms = ctx.createMarshaller();
		ms.setProperty(Marshaller.JAXB_ENCODING, "GBK");// 编码格式
		ms.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串

		StringWriter writer = new StringWriter();
		ms.marshal(p, writer);
		String resultString = writer.toString();
		System.out.println("toXmlString" + "\n" + resultString);
		return resultString;
	}

	public static Person fromXmlFile(JAXBContext ctx)
			throws FileNotFoundException, JAXBException {
		FileReader fo = new FileReader(new File("src/Person.xml"));
		Unmarshaller ums = ctx.createUnmarshaller();
		Person p = (Person) ums.unmarshal(fo);
		System.out.println("fromXmlFile" + "\n" + gson.toJson(p));
		return p;
	}

	public static Person fromXmlString(JAXBContext ctx, String xmlString)
			throws FileNotFoundException, JAXBException,
			UnsupportedEncodingException {
		InputStream fo = new ByteArrayInputStream(xmlString.getBytes("GBK"));
		Unmarshaller ums = ctx.createUnmarshaller();
		Person p = (Person) ums.unmarshal(fo);
		System.out.println("fromXmlString" + "\n" + gson.toJson(p));
		return p;
	}

	@Test
	public void testName() throws Exception {
		List<Bar> list = new ArrayList<Bar>();
		List<Bar> list2 = new ArrayList<Bar>();
		Bar bar = new Bar();
		bar.setName("bar");
		list.add(bar);
		list.add(bar);
		list2.add(bar);
		Person person2 = new Person();
		person2.Age = "33";
		person2.UserName = "parent";
		person2.bar = list2;
		Person person = new Person();
		person.Age = "11";
		person.UserName = "userName";
		person.bar = list;
		person.parent = person2;

		JAXBContext ctx = JAXBContext.newInstance(Person.class);
		toXmlString(ctx, person);
	}
	
	@Test
	public void testName2() throws Exception {
		String xmlString = "<?xml version=\"1.0\" encoding=\"GBK\" standalone=\"yes\"?><Person><UserName>userName</UserName><Age>11</Age><parent><UserName>parent</UserName><Age>33</Age><list><bar><name>bar</name></bar></list></parent><list><bar><name>bar</name></bar><bar><name>bar</name></bar></list></Person>";
		
		JAXBContext ctx = JAXBContext.newInstance(Person.class);
		fromXmlString(ctx, xmlString);
		
		
	}
}
