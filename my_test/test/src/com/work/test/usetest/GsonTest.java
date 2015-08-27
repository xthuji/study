package com.work.test.usetest;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.work.test.bean.xml.Person;

public class GsonTest {

	static Gson gson = new Gson();

	public static void main(String[] args) {
		Person person = new Person();
		person.Age = "11";
		person.UserName = "p";
		String info = gson.toJson(person);
		System.out.println(info);
		List<Person> objList = gson.fromJson(info,
				new TypeToken<List<Person>>() {
				}.getType());
		System.out.println(gson.toJson(objList));
	}
}
