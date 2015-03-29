package com.work.test.usetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class ReadFile {

	public static void main(String[] args) {
		String path = "a.txt";
		String filePaht = getRealPath(path);
		System.out.println(filePaht);

		readFile(filePaht);

	}

	private static void readFile(String filePaht) {
		File file = new File(filePaht);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
	}

	private static String getRealPath(String path) {
		String basePath = new ReadFile().getClass().getClassLoader()
				.getResource("").getPath();
		String pathString = basePath + path;
		
//		String pathString = null;
//		try {
//			pathString = new ReadFile().getClass().getClassLoader().getResource(path).toURI().getPath();
//		} catch (URISyntaxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return pathString;
	}

}
