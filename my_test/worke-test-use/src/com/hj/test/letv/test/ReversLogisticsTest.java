package com.hj.test.letv.test;

import org.junit.Test;

import com.hj.test.tools.HttpUtils;

public class ReversLogisticsTest {
    private String url       = "http://10.58.72.248:8080/services/reverseLogistics";
    private String mediaType = "application/json";
    private String content =
            "{\"content\": {\"waybillNo\": \"2014112700001\", \"packageNo\": \"2014112700001\", \"orderNo\": \"2014500642951\", \"expressTime\": \"2015-08-13 18:17:49\", \"packageWeight\": \"0\", \"protectPrice\": \"10\", \"count\": 1, \"serviceType\": 1, \"carrierId\": \"\", \"carrierNo\": \"\", \"carrierName\": \"增联\", \"storeNo\": \"3\", \"storeName\": \"3仓库\", \"state\": \"北京\", \"city\": \"东城区\", \"town\": \"内环到三环里\", \"address\": \"东四北大街107号科林大厦A618\", \"zipCode\": \"\", \"tel\": \"18610810057\", \"name\": \"段红兵\", \"remark\": \"\", \"productInfos\": [{\"skuNo\": \"AZWX001\", \"skuName\": \"乐视TV•超级电视 Letv X60\", \"skuNum\": 1, \"skuLevel\": 1, \"skuPrice\": \"5\"}] }, \"sign\": \"YWQ0YWRiMjJkOGEzZmNjN2I1MDgyNDJhZmUyNDViZjI=\"}";


    @Test
    public void postJson() throws Exception {
        String result = HttpClientUtil.postForObject(url, mediaType, content);
        System.out.println(result);
    }
    
    @Test
    public void postJson2() throws Exception {
        String result = HttpUtils.httpPost(url,content,"utf-8",false,1000);
        System.out.println(result);
    }
    
    @Test
    public void sign() throws Exception {
        String orderNo = "2014112700001"+"devEncryptionKey";
//        String sign = SignatureVerificationUtil.generateSign(orderNo, "utf-8");
//        System.out.println(sign);
    }
}
