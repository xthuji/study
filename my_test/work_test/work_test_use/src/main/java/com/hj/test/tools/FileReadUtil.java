package com.hj.test.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class FileReadUtil {

    public static final String FIELD_STR = "FIELD|";
    
    public static List<String> readFileList(String filePaht) {
        List<String> list = new ArrayList<String>();
        
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
                if (StringUtils.isNotBlank(tempString)) {
                    list.add(tempString);
                }
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
        return list;
    }
    public static List<Map<String, String>> readFileMap(String filePaht) {
        List<Map<String, String>> list = new ArrayList<Map<String,String>>();
        
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
                if (StringUtils.isNotBlank(tempString)) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    String[] dataArray = tempString.split(",");
                    String key = dataArray[0];
                    map.put(key, null);
                    for (int i = 0; i < dataArray.length; i++) {
                        map.put(FIELD_STR+i, dataArray[i]);
                    }
                    list.add(map);
                }
                
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
        return list;
    }
}
