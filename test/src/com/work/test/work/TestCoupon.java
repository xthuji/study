package com.work.test.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.work.test.tools.GsonUtils;
import com.work.test.tools.HttpUtils;
import com.work.test.usetest.ReadFile;

public class TestCoupon {
    private static final String AMOUNT = "amount";
    private static final String USR = "usr";
    static final Logger logger = LoggerFactory.getLogger(TestCoupon.class);
    
    @Test
    public void repairCoupon() throws Exception {
        String usr = "i130967237";
        String amount = "100";
        
        rechargeCoupon(usr, amount);
    }

    public static void rechargeCoupon(String usr, String amount) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(AMOUNT, amount);
        map.put(USR,usr);
        map.put("giftType","53");
        map.put("productId","614");
        map.put("desc","刘若英新书活动");
        // String url
        // ="http://127.0.0.1:27001/vp/coupon/rechargeCouponGift?amount=100&productId=614&usr=i137524965&giftType=53&desc=测试";
        String url = "http://192.168.7.39:27001/vp/coupon/rechargeCouponGift";
//        System.out.println(url);
//        System.out.println(GsonUtils.objectToJson(map));
        // url = HttpUtils.buildGetParams(url, map, "utf-8");
        String result = HttpUtils.httpsGet(url, map, "UTF-8", "59.151.122.199", 9998, 2000);
        if (StringUtils.isNotEmpty(result) && result.indexOf("\"code\":0") > -1 && result.indexOf("OK") > -1) {
//            logger.info("usr={},amount={}", usr, amount);
        } else {
            logger.error("usr={},amount={}", usr, amount);
        }
        // System.out.println(result);
        logger.info("url={},map={},result={}", url, map, result);
    }
    
    private static List<Map<String, String>> readFile(String filePaht) {
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
                list.add(buildMap(tempString));
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
    
    public static String getRealPath(String path) {
        String basePath = new ReadFile().getClass().getClassLoader()
                .getResource("").getPath();
        String pathString = basePath + path;
        return pathString;
    }
    
    private static Map<String, String> buildMap(String tempString) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(tempString)) {
            String[] strArray = tempString.split(",");
            map.put(USR, strArray[0].trim());
            map.put(AMOUNT, strArray[1].trim());
        }
        return map;
    }

    public static void main(String[] args) {
        String path = "coupon.txt";
        String filePaht = getRealPath(path);
        System.out.println(filePaht);
        List<Map<String,String>> list = readFile(filePaht);
        for (Map<String, String> map : list) {
            String usr = map.get(USR);
            String amount = map.get(AMOUNT);
//            logger.info("usr={},amount={}", usr, amount);
            rechargeCoupon(usr, amount);
        }
    }
    
}
