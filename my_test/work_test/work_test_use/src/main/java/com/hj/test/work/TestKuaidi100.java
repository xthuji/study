package com.hj.test.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.HttpUtils;

public class TestKuaidi100 {
    static final Logger logger = LoggerFactory.getLogger(TestKuaidi100.class);
    enum ENV{
        TEST(1,"test"),GREY(2,"grey"),PROD(3,"prod");
        private int value;
        private String name;
        private ENV(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }
    static ENV env = ENV.GREY;//环境
    String urlHost = "http://59.151.100.64:32001";

    String urlHost_test = "http://localhost:8080";
    String urlHost_pre = "http://59.151.100.64:32001";
    String urlHost_prod = "http://pub.open.ireader.com/ucenter";
    
    String url_deliverGoods = "/pbook/cs/deliverGoods";
    String url_logisticsNotify = "/pbook/third/logisticsNotify";
    
    @Before
    public void setHost() throws Exception {
        if (ENV.TEST.equals(env)) {
            urlHost = urlHost_test;
        } else if (ENV.GREY.equals(env)) {
            urlHost = urlHost_pre;
        } else if (env.PROD.equals(env)) {
            urlHost = urlHost_prod;
        }
    }
    
    @Test
    public void deliverGoods() throws Exception {
//        String url = "http://localhost:8080/pbook/cs/deliverGoods";
//        String url = "http://59.151.100.64:32001/pbook/cs/deliverGoods";
        String url = urlHost + url_deliverGoods;
        Map<String, String> map = new HashMap<String, String>();
        map.put("orderId", "IR143616442566892394");
        map.put("deliveryCode","229173994135");
        map.put("companyId","shentong");
        map.put("company","顺丰快递");
        System.out.println(url);
        System.out.println(GsonUtils.objectToJson(map));
//        String result = HttpUtils.httpPost(url, map, "utf-8", false, 2000);
        url = HttpUtils.buildGetParams(url, map, "utf-8");
        String result = HttpUtils.httpGet(url);
        System.out.println(result);
        logger.info("url={}",url);
    }
    
    @Test
    public void logisticsNotify() throws Exception {
//        String url = "http://localhost:8080/pbook/third/logisticsNotify";
//        String url = "http://59.151.100.64:32001/pbook/third/logisticsNotify";
        String url = urlHost + url_logisticsNotify;
        Map<String, String> map = new HashMap<String, String>();
        map.put("sign", "06B51922C5C0B8B66050BA72CDFCBD64");
        map.put("param",
                "{\"status\": \"polling\", \"billstatus\": \"got\", \"message\": \"\", \"lastResult\": {\"message\": \"ok\", \"state\": \"0\", \"status\": \"200\", \"condition\": \"F00\", \"ischeck\": \"0\", \"com\": \"shunfeng\", \"nu\": \"11\", \"data\": [{\"context\": \"上海分拨中心\", \"time\": \"2012-08-28 16:33:19\", \"ftime\": \"2012-08-28 16:33:19\", \"status\": \"在途\", \"areaCode\": \"310000000000\", \"areaName\": \"上海市\"}, {\"context\": \"上海分拨中心\", \"time\": \"2012-08-27 23:22:42\", \"ftime\": \"2012-08-27 23:22:42\", \"status\": \"在途\", \"areaCode\": \"310000000000\", \"areaName\": \"上海市\"}] } }");
        String result = HttpUtils.httpPost(url, map, "utf-8", false, 2000);
        System.out.println(result);
    }
    
    @Test
    public void testKuaidi100Post() throws Exception {
        String url = "http://www.kuaidi100.com/poll";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("schema", "json");
        paramMap.put("param", "{\"key\":\"YjTezKro4194\",\"company\":\"shunfeng\",\"number\":\"305980661497\",\"from\":\"\",\"to\":\"山西省阳泉市城区\",\"parameters\":{\"callbackurl\":\"http://59.151.100.64:32001/pbook/logisticsNotify\",\"salt\":\"ZYWLJY0629\",\"resultv2\":\"1\"}}");
        String result = HttpUtils.httpPost(url, paramMap, "utf-8", false, 1000);
        System.out.println(result);
    }

}
