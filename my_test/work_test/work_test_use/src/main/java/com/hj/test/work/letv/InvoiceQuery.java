package com.hj.test.work.letv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hj.test.excel.StringUtil;
import com.hj.test.tools.FileReadUtil;
import com.hj.test.tools.HttpUtils;
import com.hj.test.tools.HttpUtils.HttpConstant;
import com.hj.test.tools.PathUtil;

/**
 * 查询发票信息
 * @author huji
 * @version V1.0 
 * @since 1.0 2015年9月30日-下午1:43:26
 */
public class InvoiceQuery {

    static Logger logger = LoggerFactory.getLogger(InvoiceQuery.class);
    
    static String SPLIT_STR = ",";
    
//    static String url = "http://test.invoice.shop.letv.com/invoiceItem/query?orderId=";
    static String url = "http://invoice.shop.letv.com/invoiceItem/query?orderId=";
    static String orderIds = "4008878842659";
    private static final String INVOICE_ORDER_TXT = "invoice_order.txt";
    static boolean useFile = true;
    
    public static void main(String[] args) {
        Object[] orderArrray = orderIds.split(SPLIT_STR);
        if (useFile) {
            orderArrray = FileReadUtil.readFileList(PathUtil.getRealPath(INVOICE_ORDER_TXT)).toArray();
        }
        Set<String> list = new LinkedHashSet<String>(100);
        List<Object> list1 = new ArrayList<Object>();
        List<Object> list2 = new ArrayList<Object>();
        List<Object> list3 = new ArrayList<Object>();
        for (int i = 0; i < orderArrray.length; i++) {
            if (i%3==0) {
                list1.add(orderArrray[i]);
            } else if (i%3==1) {
                list2.add(orderArrray[i]);
            } else {
                list3.add(orderArrray[i]);
            }
        }
        
//        queryInvoice(orderArrray, list);
        new ThreadBean(list1.toArray(), list).start();
        new ThreadBean(list2.toArray(), list).start();
        new ThreadBean(list3.toArray(), list).start();
        logger.info("\n array size={}",orderArrray.length);
    }

    private static void queryInvoice(Object[] orderArrray, Set<String> list) {
        for (int i = 0; i < orderArrray.length; i++) {
            String orderId = ((String) orderArrray[i]).trim();
            if (StringUtils.isNotBlank(orderId)) {
                query(orderId, list);
            }
        }
        logger.info("\n orderArray size={}",orderArrray.length);
        logger.info("\n orderList={}, \n list={}",list.size(), list);
    }
    
    private static void query(String orderId, Set<String> list){
        Map<String, String> map = new HashMap<String, String>();
        //test
//        map.put("Cookie", "_i_c_c_cookie_=\"MWU4ZTRjNjU2M2FhY2MwY2Y1MDlhNTcxNjJiYzkxYmQ=\"; _i_u_cookie_=\"eyJ1c2VySWQiOjE1NzE4LCJ1c2VyTmFtZSI6Imh1amlAbGV0di5jb20iLCJjbk5hbWUiOiLog6HlkIkifQ==\"");
        //online
        map.put("Cookie", "_i_u_cookie_=\"eyJ1c2VySWQiOjE1NzE4LCJ1c2VyTmFtZSI6Imh1amlAbGV0di5jb20iLCJjbk5hbWUiOiLog6HlkIkifQ==\"");
        String result = null;
        try {
            result = HttpUtils.httpGet(url + orderId, null, "UTF-8", HttpConstant.LOCALHOST, 1, 20000, map);
        } catch (Exception e) {
            logger.error("http error, order={}",orderId);
            list.add(orderId);
        }
        logger.info("orderId={},result={}",orderId,result);
        if (StringUtil.isBlank(result) || result.indexOf("\"code\":500")<0) {
            list.add(orderId);
        }
    }
    
    public static class ThreadBean extends Thread {

        private Object[] data;
        private Set<String> list;

        public ThreadBean() {
            super();
        }
        public ThreadBean(Object[] data, Set<String> list) {
            super();
            this.data = data;
            this.list = list;
        }
        public Object[] getData() {
            return data;
        }
        public void setData(Object[] data) {
            this.data = data;
        }
        public Set<String> getList() {
            return list;
        }
        public void setList(Set<String> list) {
            this.list = list;
        }

        @Override
        public void run() {
            queryInvoice(data, list);
        }
        
    }
}