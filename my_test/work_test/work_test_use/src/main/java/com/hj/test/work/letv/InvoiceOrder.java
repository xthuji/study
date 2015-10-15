package com.hj.test.work.letv;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hj.test.tools.FileReadUtil;
import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.HttpUtils;
import com.hj.test.tools.PathUtil;
import com.hj.test.tools.SqlHelper;

public class InvoiceOrder {

    private static final Logger logger   = LoggerFactory.getLogger(InvoiceOrder.class);

    private static final String SQL = "SELECT * FROM (SELECT t1.order_id,t1.update_at,t2.operate_type,t3.update_time,t3.yn FROM zx_event_queue.shipments_queue AS t1 LEFT JOIN zx_invoice.operate_log AS t2 ON t1.order_id = t2.order_id LEFT JOIN zx_invoice.split_task t3 ON t1.order_id = t3.keyword1 WHERE t2.operate_type = 4 AND t1.execute_count >= 1 AND t1.`status` = 2 AND t2.yn = 1 AND t1.order_id = ? ORDER BY t3.update_time DESC LIMIT 1) temp WHERE yn = 0 AND update_at < update_time";
//    private static final String SQL = "SELECT t1.order_id,t1.update_at,t2.operate_type,t3.update_time,t3.yn FROM zx_event_queue.shipments_queue AS t1 LEFT JOIN zx_invoice.operate_log AS t2 ON t1.order_id = t2.order_id LEFT JOIN zx_invoice.split_task t3 ON t1.order_id = t3.keyword1 WHERE t2.operate_type = 4 AND t1.execute_count >= 1 AND t1.`status` = 2 AND t2.yn = 1 AND t1.order_id = ? ORDER BY t3.update_time DESC ";
    
    private static final int QUERY_SLEEP_SIZE = 17;
    private static final String SPLIT_STR = ",";

    private static final String rateUrl  = "http://srp.shop.letv.com/OpenTaxRate/getRateBySkuNo.do?SkuNo=";
    private static final String areaUrl  = "http://tmsbasews.shop.letv.com/areaws/getAreaInfoByAreaNo?areaNo=";
    private static final String orderUrl = "http://backorder.shop.letv.com//api/web/query/getBackgroundInfo.do?isShip=true&isItem=true&isLog=true&order_id=";
    private static final String unitUrl = "http://srp.shop.letv.com/OpenSku/querySkuForBill.do?skuNo=";

    private static final String INVOICE_ORDER_TXT = "invoice_order.txt";

    static String orderIds = "3994614472287";
//    static String orderIds = "399062104826994614750232, 3994615619076, 3994614129179, 3994610005585, 4008870407057, 4008878959471, 4008878494280, 4008871417341, 3994619880874, 4008872003398, 4008875126341";
    static boolean useFile = true;//是否读取文件中的订单号,忽略orderIds
    
    static boolean needArea = false;//需要地区名称
    static boolean needRate = true;//读取税率
    static boolean needUnit = true;//读取单位
    
    static boolean listLog = true;
    static boolean httpLog = false;
    static boolean orderLog = true;
    
    public static void main(String[] args) {
        
        Map<String, List<String>> logMap = new LinkedHashMap<String, List<String>>();
        logMap.put("invoiceNoList", new ArrayList<String>());//不要发票
        logMap.put("priceZerolist", new ArrayList<String>());//金额为零
        logMap.put("priceErrorlist", new ArrayList<String>());//订单金额错误
        logMap.put("invoicePriceErrorlist", new ArrayList<String>());//订单发票金额错误,天猫订单
        logMap.put("rateList", new ArrayList<String>());//发票税率
        logMap.put("unitList", new ArrayList<String>());//发票单位
        logMap.put("errorlist", new ArrayList<String>());//请求失败
        logMap.put("resetList", new ArrayList<String>());//需要重置的订单
        logMap.put("okList", new ArrayList<String>());//正常订单
        batchQuery(logMap);
        for (String key : logMap.keySet()) {
            List<String> list = logMap.get(key);
            if (listLog && CollectionUtils.isNotEmpty(list)) {
                logger.error("\n {}={}, list={}", key, list.size(), list);
            }
        }
    }

    private static void batchQuery(Map<String, List<String>> logMap) {
        Object[] orderArrray = orderIds.split(SPLIT_STR);
        if (useFile) {
            orderArrray = FileReadUtil.readFileList(PathUtil.getRealPath(INVOICE_ORDER_TXT)).toArray();
        }
        for (int i = 0; i < orderArrray.length; i++) {
            String orderId = ((String) orderArrray[i]).trim();
            if (StringUtils.isNotBlank(orderId)) {
                Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
                queryOrder(orderId, resultMap, logMap);
                if (i%QUERY_SLEEP_SIZE==0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        logger.error("sleep error",e);
                    }
                }
            }
        }
        logger.info("\n orderArrray size={}",orderArrray.length);
    }

    private static void queryOrder(String orderId, Map<String, Object> resultMap, Map<String, List<String>> logMap) {
        List<String> invoiceNoList = logMap.get("invoiceNoList");
        List<String> priceErrorlist = logMap.get("priceErrorlist");
        List<String> priceZerolist = logMap.get("priceZerolist");
        List<String> errorlist = logMap.get("errorlist");
        List<String> invoicePriceErrorlist = logMap.get("invoicePriceErrorlist");
        List<String> rateList = logMap.get("rateList");
        List<String> unitList = logMap.get("unitList");
        List<String> resetList = logMap.get("resetList");
        List<String> okList = logMap.get("okList");
        
        resultMap.put("orderId", orderId);
        
        // http 请求
        String result = httpGet(orderUrl + orderId, null);
        if (StringUtils.isNotEmpty(result) && !result.contains("Exception")) {
            ResultBean bean = GsonUtils.jsonToBean(result, ResultBean.class);
            List<Map<String,Object>> resultList = bean.result;
            for (Map<String, Object> map : resultList) {
                double onAmount = (Double) map.get("onAmount");
                double orderAmount = (Double) map.get("orderAmount");
                double shippingFee = (Double) map.get("shippingFee");
                double invoiceAmount = (Double) map.get("invoiceAmount");
                double dicount = orderAmount - onAmount;// 折扣
                int isInvoice = getIntValue(map.get("isInvoice"));
                int invoiceType = getIntValue(map.get("invoiceType"));
                resultMap.put("receiver", map.get("receiver"));
                resultMap.put("userId", map.get("userId"));
                resultMap.put("orderAmount", orderAmount);
                resultMap.put("onAmount", onAmount);
                resultMap.put("dicount", dicount);
                resultMap.put("productsAmount", map.get("productsAmount"));
                resultMap.put("discountAmount", map.get("discountAmount"));
                resultMap.put("shippingFee", shippingFee);
                resultMap.put("paidAmount", map.get("paidAmount"));
                resultMap.put("refundAmount", map.get("refundAmount"));
                resultMap.put("isInvoice", isInvoice);
                resultMap.put("invoiceTitle", map.get("invoiceTitle"));
                resultMap.put("invoiceType", invoiceType);
                resultMap.put("orderWay", getIntValue(map.get("orderWay")));
                resultMap.put("orderChannel", map.get("orderChannel"));
                resultMap.put("orderType", getIntValue(map.get("orderType")));
                resultMap.put("orderStatusId", getIntValue(map.get("orderStatusId")));
                resultMap.put("invoiceAmount", invoiceAmount);
                resultMap.put("createAt", map.get("createAt"));
                if (needArea) {
                    String province = getAreaName(map.get("provinceId"));
                    String city = getAreaName(map.get("cityId"));
                    String district = getAreaName(map.get("districtId"));
                    resultMap.put("province", province);
                    resultMap.put("city", city);
                    resultMap.put("district", district);
                }
                resultMap.put("address", map.get("address"));
                resultMap.put("homeInstallation", getIntValue(map.get("homeInstallation")));
                resultMap.put("mobile", map.get("mobile"));
                resultMap.put("eMail", map.get("eMail"));
                
                List<Map<String, Object>> productList = new ArrayList<Map<String,Object>>();
                List<Map<String, Object>> itemsList = (List<Map<String, Object>>)map.get("itemsList");
                double orderItemsPrice = 0d;
                for (Map<String, Object> item : itemsList) {
                    Map<String, Object> itemMap = new LinkedHashMap<String, Object>();
                    String productId = item.get("productId").toString();
                    int isVirtual = getIntValue(item.get("isVirtual"));
                    double finalPrice = (Double) item.get("finalPrice");
                    int quantity = getIntValue(item.get("quantity"));
                    int quantityInGroup = getIntValue(item.get("quantityInGroup"));
                    itemMap.put("promotionId", item.get("promotionId"));
                    itemMap.put("productId", productId);
                    itemMap.put("productName", item.get("productName"));
                    if (isVirtual == 0) {
                        orderItemsPrice += finalPrice*quantity*quantityInGroup;
                        itemMap.put("isSuite", false);
                        if (needRate) {
                            Double rate = getProductRate(productId);
                            if (null == rate || rate == 0) {//税率有问题的
                                rateList.add(orderId);
                            }
                            itemMap.put("rate", rate);
                        }
                        if (needUnit) {
                            String unit = getProductUnit(productId);
                            if (StringUtils.isEmpty(unit)) {//单位有问题的
                                unitList.add(orderId);
                            }
                            itemMap.put("unit", unit);
                        }
                    } else {
                        itemMap.put("isSuite", true);
                    }
                    itemMap.put("productPrice", item.get("productPrice"));
                    itemMap.put("finalPrice", finalPrice);
                    itemMap.put("discount", item.get("discount"));
                    itemMap.put("quantity", quantity);
                    itemMap.put("quantityInGroup", quantityInGroup);
                    itemMap.put("isMain", getIntValue(item.get("isMain")));
                    itemMap.put("isVirtual", isVirtual);
                    itemMap.put("isGift", getIntValue(item.get("isGift")));
                    itemMap.put("createAt", item.get("createAt"));
                    itemMap.put("productType", item.get("productType"));
                    itemMap.put("productTwolevelType", item.get("productTwolevelType"));
                    itemMap.put("productThreelevelType", item.get("productThreelevelType"));
                    
                    productList.add(itemMap);
                }
                resultMap.put("productList", productList);
                resultMap.put("orderItemsPrice", orderItemsPrice);
                BigDecimal invoiceTotal = getBigDecimal(orderItemsPrice+shippingFee-dicount);
                resultMap.put("invoiceTotal", invoiceTotal.doubleValue());
                if (isInvoice == 1 || invoiceType == 1) {//不开发票的
                    invoiceNoList.add(orderId);
                } else if (onAmount == 0) {//订单金额为零
                    priceZerolist.add(orderId);
                } else if (!valueEquals(getBigDecimal(onAmount), invoiceTotal)) {//订单金额对不上
                    resultMap.put("orderPriceError", true);
                    priceErrorlist.add(orderId);
                } else if (!valueEquals(getBigDecimal(invoiceAmount), invoiceTotal)) {//发票金额对不上，天猫订单
                    invoicePriceErrorlist.add(orderId);
                } else {
                    String sql = SQL;
                    String[] params = {orderId};
                    java.sql.ResultSet rb = SqlHelper.executeQuery(sql, params);
                    try {
                        if(rb != null && rb.next()){
                            resetList.add(orderId);
                        } else {
                            List<Map<String,String>> allData = SqlHelper.getAllData(rb);
                            logger.info("sql result={}",allData);
                            okList.add(orderId);
                        }
                    } catch (SQLException e) {
                        logger.error("sql query error, orderId={}",orderId);
                        okList.add(orderId);
                    }
                }
            }
            if (orderLog) {
                logger.info("\n orderResult={}", GsonUtils.objectToJson(resultMap));
            }
        } else {
            errorlist.add(orderId);
            logger.error("orderId={}", orderId);
        }
    }

    /**
     * 值相等
     * @author huji
     * @param onAmount
     * @param invoiceTotal
     * @return
     */
    private static boolean valueEquals(BigDecimal onAmount, BigDecimal invoiceTotal) {
        return subtract(onAmount, invoiceTotal).compareTo(BigDecimal.ZERO)==0;
    }

    private static BigDecimal getBigDecimal(double invoice_total) {
        return new BigDecimal(invoice_total).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static BigDecimal subtract(BigDecimal invoiceAmount, BigDecimal invoiceTotal) {
        return invoiceTotal.subtract(invoiceAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private static String getProductUnit(String productId) {
        String unit = null;
        try {
            String result = httpGet(unitUrl + productId, null);
            Map<String, String> resultMap = (Map<String, String>) GsonUtils.jsonToMap(result).get("result");
            unit = resultMap.get("unit");
        } catch (Exception e) {
        }
        return unit;
    }

    private static Double getProductRate(String productId) {
        Double rate = 0d;
        try {
            String result = httpGet(rateUrl + productId, null);
            rate = (Double) GsonUtils.jsonToMap(result).get("Rate");
        } catch (Exception e) {
        }
        return rate;
    }

    private static String getAreaName(Object obj) {
        String areaJson = httpGet(areaUrl + getIntValue(obj), null);
        return (String) GsonUtils.jsonToMap(areaJson).get("areaName");
    }

    private static int getIntValue(Object obj) {
        return ((Double)obj).intValue();
    }

    private static String httpGet(String url, Map<String, String> map) {
        String result = HttpUtils.httpsGet(url, map , "UTF-8", false, 2000);
//        logger.info("url={},\n map={},\n result={}", url, map, result);
        if (httpLog) {
            logger.info("url={},\n result={}", url, result);
        }
        return result;
    }

    public class ResultBean{
        String message;
        List<Map<String, Object>> result;
        int status;
    }
}
