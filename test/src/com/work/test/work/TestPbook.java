package com.work.test.work;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.NumberUtils;

import com.google.gson.reflect.TypeToken;
import com.work.test.tools.GsonUtils;

public class TestPbook {

    @Test
    public void testLogistics() throws Exception {
        String result = "{\"logistic\": {\"company_name\": \"申通\", \"delivery_code\": \"220250122507\", \"state\": 0, \"traceList\": [{\"context\": \"快件已经到达【四川中心】上一站是【新华】扫描员是【xxx】\", /*内容*/ \"time\": \"2012-08-28 16:33:19\"}] } }";
        Map<String, LogisticsDetail> map = GsonUtils.jsonToBean(result, new TypeToken<Map<String, LogisticsDetail>>(){}.getType());
        LogisticsDetail logistic = map.get("logistic");
        System.out.println(GsonUtils.objectToJson(logistic));
        logistic.company_name = null;
        System.out.println(GsonUtils.objectToJson(logistic));
    }
    
    @Test
    public void testName() throws Exception {
        float floatValue = NumberUtils.parseNumber("", float.class);
        System.out.println(floatValue);
    }

    public class LogisticsDetail {

        private String company_name;// 申通
        private String delivery_code;// 运单号
        private String state;// 快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单
        private List<TraceInfo> traceList;// 物流信息
        
        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getDelivery_code() {
            return delivery_code;
        }

        public void setDelivery_code(String delivery_code) {
            this.delivery_code = delivery_code;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<TraceInfo> getTraceList() {
            return traceList;
        }

        public void setTraceList(List<TraceInfo> traceList) {
            this.traceList = traceList;
        }


        /**
         * 物流运输信息
         * @author huji
         * @version 1.0
         * @date 2015年4月29日 下午2:47:34
         */
        public class TraceInfo {
            private String context;// 物流信息
            private String time;// 时间

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

        }
    }

}
