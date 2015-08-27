package com.work.test.bean.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
public class Person {
	// @XmlAttribute /**使用这个，会成<person userName>*/
	public String UserName;// 必须指定为public的，否则转换不了
	@XmlElement(name = "Age")
	public String Age;
	@XmlElement(name = "parent")
	public Person parent;
	
	@XmlElement(name = "bar")
	@XmlElementWrapper(name = "list")
	public List<Bar> bar;

	public Person() {
		super();
	}

	public Person(String username, String age) {
		super();
		this.UserName = username;
		this.Age = age;
	}
}
