package com.work.test.usetest;

import java.util.Map;

import com.google.gson.Gson;
import com.work.test.tools.UrlMapConvert;

public class URL$Map {

	public static void main(String[] args) {
//		String string = "http://192.168.6.20/huji/zybook3/app/app.php?ca=Classlist_new.Recommend&pk=2K1&usr=xing2014&rgt=7&p2=999941&p3=700003&p4=501603&p5=14&p9=0&order=download&parentId=21&tag=%E6%B8%B8%E6%88%8F%E7%AB%9E%E6%8A%80&cid=4316&key=TC12";
		String string = "http://192.168.6.20/huji/zybook3/app/app.php?ca=Classlist_new.Recommend&usr=i347740915&rgt=7&p2=999941&p3=700003&p4=501603&p5=14&p9=0&ky=5K1&pk=8K1&id=5349&name=%E7%8E%B0%E4%BB%A3%E8%A8%80%E6%83%85&tag=%E7%8E%B0%E4%BB%A3%E8%A8%80%E6%83%85&parentId=5218&key=100C%E7%8E%B0%E4%BB%A3%E8%A8%80%E6%83%85&cid=5349&order=download";
		Map<String, String> url2Map = UrlMapConvert.url2Map(string);
		Gson gson = new Gson();
		System.out.println(gson.toJson(url2Map));
		
	}
}
