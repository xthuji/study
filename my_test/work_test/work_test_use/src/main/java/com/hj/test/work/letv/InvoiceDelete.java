package com.hj.test.work.letv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hj.test.excel.StringUtil;
import com.hj.test.tools.FileReadUtil;
import com.hj.test.tools.HttpUtils;
import com.hj.test.tools.HttpUtils.HttpConstant;
import com.hj.test.tools.PathUtil;

/**
 * 删除发票
 * @author huji
 * @version V1.0 
 * @since 1.0 2015年9月30日-下午1:43:09
 */
public class InvoiceDelete {


    static Logger logger = LoggerFactory.getLogger(InvoiceDelete.class);
    
    static String SPLIT_STR = ",";
    
    static String url = null;
    static String test_url = "http://test.invoice.shop.letv.com/orders/resetOrder?orderCode=";
    static String online_url = "http://invoice.shop.letv.com/orders/resetOrder?orderCode=";
    static String orderIds = "3994610938907,3994617334087,3994611233231,3994618461375,3994612938509,3994611221945,3994618139581,3994615367746,3994611230130,3994611192220,3994616396236,3994614114664,3994611421556,3994612236223,3994614437075,3994616617392,3998543706933,4008878001600";
    private static final int BATCH_SIZE = 4;
    private static final int THREAD_SIZE = 5;
    private static final String INVOICE_ORDER_TXT = "invoice_order.txt";
    static boolean useFile = false;//是否使用文件中的订单号
    static boolean is_test = false;//是否测试环境
    
    public static void main(String[] args) {
        url = is_test?test_url:online_url;
        Object[] orderArrray = orderIds.split(SPLIT_STR);
        if (useFile) {
            orderArrray = FileReadUtil.readFileList(PathUtil.getRealPath(INVOICE_ORDER_TXT)).toArray();
        }
        Set<String> set = new LinkedHashSet<String>();
        
        List<List<Object>> dataLists = splitData(orderArrray);
        
        logger.info("\ndataLists={}",dataLists);
        // 创建线程，开始执行
        int threadNum = 0;
        for (List<Object> datas : dataLists) {
            if (CollectionUtils.isNotEmpty(datas)) {
                new ThreadBean(datas.toArray(), set, threadNum+"").start();
                logger.info("threadNum={}",threadNum++);
            }
        }
//        deleteInvoice(orderArrray, list);
        logger.info("\n array size={}",orderArrray.length);
    }

    /**
     * 批量删除发票
     * @author huji
     * @param orderArrray
     * @param set
     */
    private static void deleteInvoice(Object[] orderArrray, Set<String> set, String threadName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < orderArrray.length; i++) {
            String orderId = ((String) orderArrray[i]).trim();
            if (StringUtils.isNotBlank(orderId)) {
                sb.append(orderId).append(SPLIT_STR);
                int index = i+1;
                if ((index>=BATCH_SIZE && i%BATCH_SIZE==0) || (index==orderArrray.length)) {
                    try {
                        String orders = sb.toString().substring(0,sb.length()-1);
                        //删除发票
                        delete(orders, set, threadName);
                        Thread.currentThread().sleep(20);
                        sb = new StringBuilder();
                    } catch (InterruptedException e) {
                        logger.error("sleep error",e);
                    }
                }
            }
        }
        logger.info("\n [thread{}],orderArray size={}",threadName,orderArrray.length);
        logger.info("\n [thread{}],orderSet={}, \n set={}",threadName,set.size(), set);
    }
    
    /**
     * 调用删除接口
     * @author huji
     * @param orderId
     * @param set
     */
    private static void delete(String orderId, Set<String> set, String threadName){
        Map<String, String> map = new HashMap<String, String>();
        if (is_test) {
            //test
            map.put("Cookie", "_i_c_c_cookie_=\"MWU4ZTRjNjU2M2FhY2MwY2Y1MDlhNTcxNjJiYzkxYmQ=\"; _i_u_cookie_=\"eyJ1c2VySWQiOjE1NzE4LCJ1c2VyTmFtZSI6Imh1amlAbGV0di5jb20iLCJjbk5hbWUiOiLog6HlkIkifQ==\"");
        }else {
            //online
            map.put("Cookie", "_i_c_c_cookie_=\"YTBlOTY0OTYyMWQ2NjhjMGM2ZTExNzYyZGRjMmY5MmQ=\"; _i_u_cookie_=\"eyJ1c2VySWQiOjE1NzE4LCJ1c2VyTmFtZSI6Imh1amlAbGV0di5jb20iLCJjbk5hbWUiOiLog6HlkIkifQ==\"");
        }
        String result = null;
        try {
            result = HttpUtils.httpGet(url + orderId, null, "UTF-8", HttpConstant.LOCALHOST, 1, 20000, map);
        } catch (Exception e) {
            logger.error("\n [thread{}], http error, order={}",threadName, orderId);
            set.add(orderId);
        }
        logger.info("\n [thread{}], orderId={},result={}",threadName, orderId, result);
        if (StringUtil.isBlank(result) || result.indexOf("\"code\":200")<0) {
            set.add(orderId);
        }
    }
    
    /**
     * 拆分数据
     * @author huji
     * @param orderArrray
     * @return
     */
    private static List<List<Object>> splitData(Object[] orderArrray) {
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        for (int i = 0; i < THREAD_SIZE; i++) {
            dataList.add(new ArrayList<Object>());
        }
        for (int i = 0; i < orderArrray.length; i++) {
            int index = i%THREAD_SIZE;
            Object obj = orderArrray[i];
            switch (index) {
                case 0:
                    dataList.get(index).add(obj);
                    break;
                case 1:
                    dataList.get(index).add(obj);
                    break;
                case 2:
                    dataList.get(index).add(obj);
                    break;
                case 3:
                    dataList.get(index).add(obj);
                    break;
                case 4:
                    dataList.get(index).add(obj);
                    break;
                case 5:
                    dataList.get(index).add(obj);
                    break;
                case 6:
                    dataList.get(index).add(obj);
                    break;
                case 7:
                    dataList.get(index).add(obj);
                    break;
                case 8:
                    dataList.get(index).add(obj);
                    break;
                case 9:
                    dataList.get(index).add(obj);
                    break;
                default:
                    dataList.get(0).add(obj);
                    break;
            }
        }
        return dataList;
    }

    public static class ThreadBean extends Thread {

        private String name;
        private Object[] data;
        private Set<String> set;

        public ThreadBean() {
            super();
        }
        public ThreadBean(Object[] data, Set<String> set, String name) {
            super();
            this.data = data;
            this.set = set;
            this.name = name;
        }

        @Override
        public void run() {
            deleteInvoice(data, set, name);
        }
        
    }
}
