package com.hj.test.work.letv;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.annotation.elidable;

import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.HttpUtils;

public class TestOrderProductInfo {

    private static final Logger logger   = LoggerFactory.getLogger(TestOrderProductInfo.class);
    
    private static final String SPLIT_STR = ",";

    private static final String rateUrl  = "http://srp.shop.letv.com/OpenTaxRate/getRateBySkuNo.do?SkuNo=";
    private static final String areaUrl  = "http://tmsbasews.shop.letv.com/areaws/getAreaInfoByAreaNo?areaNo=";
    private static final String orderUrl = "http://backorder.shop.letv.com//api/web/query/getBackgroundInfo.do?isShip=true&isItem=true&isLog=true&order_id=";
    private static final String unitUrl = "http://srp.shop.letv.com/OpenSku/querySkuForBill.do?skuNo=";

//    String orderIds = "3994610160006,3990621048269,3990620782739,1509182348566,1509190864847,1509190981219,3994611775948,3994612081386,1509190958492,1509190921902,3994613285707,1509190921414,1509190920921,1509190964900,3994613952671,3994618399005 ,3994612327574,3994612812646,3994619819388,3994616258099,3994611841978,3994615215817,3994615176120,3994614341616,3994617764823,3994610381552,3994613396318,3994619962579,3994616674184,3994617877842,3994615927978,3994615483294,3994612301710,3994613979607,3994619386775,3994611782995,3994619431007,3994614442829,3994618433497,3994619035325,3994614359465,3994618817329,3994615636873,3994610534669,3994615292429,3994613176824,3994613508915,3994612585401,3994612264308,3994616568036,3994618535943,3994615398784,3994617253296,3994614064624,3994619063553,3994611568408,3994619359113,3994615926440,3994611299966,3994615123622,3994618168868,3994619870366,3994616336370,3994613221630,3994613889311,3994613448983,3994617413887,3994614267593,3994618489129,3994615937489,3994610724376,3994614253048,3994618728408,3994612233064,3994618522560,3994612425340,3994617330957,3994618547273,3994613582678,3994612361807,3994613067115,3994615000847,3994612249732,3994613268579,3994614371908,3994616276919,3994613016784,3994613737500,3994612174965,3994611444600,3994618633744,3994611683776,3994613356377,3994611905919,3994613718465,3994610831073,3994612542980,3994610445218,3994610827892,3994611026378,3994618160876,3994616235080,3994619888666,3994612750394,3994617241604,3994614668738,3994614281546,3994614295316,3994610537342,3994612468934,3994618051190,3994617242480,3994614229858,3994617514578,3994611720594,3994617532306,3994614401554,3994615446102,3994614946102,3994617067050,3994617925938,3994618643902,3994617101758,3994611089574,3994611214458,3994613809498,3994615036678,3994617856582,3994617813330,3994617053706,3994611092921,3994612487021,3994615915259,3994618565964,3994618778076,3994619396757,3994617952859,3994614975607,3994614493052,3994611873869,3994618384398,3994613555941,3994619078245,3994610619817,3994611274817,3994618538273,3994615530641,3994615894405,3994615558243,3994617277560,3994614154463,3994612012003,3994619107535,3994615948983,3994619752288,3994610831616,3994617354479,3994613414947,3994615913256,3994616064559,3994611482711,3994619818511,3994613767872,3994612524835,3994617242155,3994617204423,3994613386339,3994614629539,3994610582985,3994614689215,3994615368335,3994610552859,3994615129152,3994618277991,3994610160527,3994614307991,3994615767396,3994619001043,3994618421288,3994613668166,3994616114270,3994615950202,3994615739598,3994616766882,3994611647362,3994613809086,3994618753793,3994611565037,3994617363637,3994613766425,3994612035085,3994610863045,3994612933969,3994619457601,3994610802149,3994618856765,3994613622799,3994614302423,3994613310216,3994618959363,3994613084371,3994615101331,3994616960155,3994614624033,3994618244500,3994610081271,3994615218408,3994613995599,3994616942164,3994611384535,3994613366040,3994610156051,3994611604107,3994613037407,3994611735110,3994616590259,3994610513955,3994611314439,3994612242770,3994616600541,3994616983560,3994617613215,3994617524510,3994614146288,3994617150068,3994615097414,3994613812222,3994613349302,3994617375518,3994618924098,3994618541286,3994613475990,3994610776134,3994618469658,3994613891158,3994619927089,3994619854661,3994619919149,3994617299705,3994616353237,3994616555309,3994614110644,3994615705163,3994613310012,3994613284521,3994619055316,3994610274771,3994616613513,3994618671507,3994615261891,3994613381783,3994611747464,3994615825243,3994613503868,3994616811501,3994619822088,3994616461947,3994611068496,3994617689032,3994611291218,3994610961555,3994610961195,3994618000580,3994612881208,3994614407839,3994610335455,3994617164412,3994617618783,3994617625875,3994618866751,3994613226381,3994611407474,3994612471694,3994618765136,3994613264135,3994611688868,3994614881000,3994619721351,3994610504914,3994614595354,3994617422726,3994611411430,3994617085414,3994617808878,3994613673618,3994611828882,3994617544926,3994612928585,3994611582341,3994612919317,3994612207789,3994615028225,3994613106205,3994610160973,3994618204531,3994616337552,3994614544660,3994612510251,3994613580040,3994612832615,3994613997996,3994615822423,3994617694387,3994614214245,3994614633050,3994615813135,3994614430895,3994612130819,3994619047415,3994617115795,3994610635363,3994617347008,3994619217369,3994618506348,3994615324164,3994618674980,3994614542937,3994614728507,3994613804011,3994616605896,3994618204136,3994617308923,3994614367406,3994619609181,3994614094935,3994619888948,3994610359862,3994614207956,3994610979140,3994612156863,3994617533171,3994613462004,3994617764318,3994615775728,3994614635700,3994613655603,3994617009832,3994617419358,3994614785715,3994617128985,3994617580791,3994611594774,3994617257742,3994618677505,3994619279873,3994619657641,3994613639353,3994616141281,3994612019149,3994615163201,3994612109173,3994613791349,3994619243873,3994611296377";
//    String orderIds = "3990621048269";
    String orderIds = "3990621048269,3994610266174,3994613184022,3994614459930,3994614037485,3994616794070,3994613559791,3994613344400,3994617122368,3994616116432,3994616238112,3994613985405,3994618771500,3994612599478,3994616047309";
    
    boolean needArea = false;
    boolean needRate = true;
    boolean needUnit = false;
    
    boolean listLog = true;
    boolean httpLog = true;
    boolean orderLog = true;
    
    @Test
    public void queryOrderInfo() throws Exception {
        Map<String, List<String>> logMap = new LinkedHashMap<String, List<String>>();
        logMap.put("invoiceNoList", new ArrayList<String>());//不要发票
        logMap.put("priceZerolist", new ArrayList<String>());//金额为零
        logMap.put("priceErrorlist", new ArrayList<String>());//订单金额错误
        logMap.put("invoicePriceErrorlist", new ArrayList<String>());//订单发票金额错误,天猫订单
        logMap.put("rateList", new ArrayList<String>());//发票税率
        logMap.put("unitList", new ArrayList<String>());//发票单位
        logMap.put("errorlist", new ArrayList<String>());//请求失败
        logMap.put("okList", new ArrayList<String>());//正常订单
        batchQuery(logMap);
        for (String key : logMap.keySet()) {  
            List<String> list = logMap.get(key);
            if (listLog || CollectionUtils.isNotEmpty(list)) {
                logger.error("\n {}={}, list={}", key, list.size(), GsonUtils.objectToJson(list));
            }
        }
    }

    private void batchQuery(Map<String, List<String>> logMap) {
        String[] orderArrray = orderIds.split(SPLIT_STR);
        for (int i = 0; i < orderArrray.length; i++) {
            String orderId = orderArrray[i].trim();
            if (StringUtils.isNotBlank(orderId)) {
                Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
                queryOrder(orderId, resultMap, logMap);
                if (i%97==0) {
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

    private void queryOrder(String orderId, Map<String, Object> resultMap, Map<String, List<String>> logMap) {
        List<String> invoiceNoList = logMap.get("invoiceNoList");
        List<String> priceErrorlist = logMap.get("priceErrorlist");
        List<String> priceZerolist = logMap.get("priceZerolist");
        List<String> errorlist = logMap.get("errorlist");
        List<String> invoicePriceErrorlist = logMap.get("invoicePriceErrorlist");
        List<String> rateList = logMap.get("rateList");
        List<String> unitList = logMap.get("unitList");
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
                            if (null == rate || rate == 0) {
                                rateList.add(orderId);
                            }
                            itemMap.put("rate", rate);
                        }
                        if (needUnit) {
                            String unit = getProductUnit(productId);
                            if (StringUtils.isEmpty(unit)) {
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
                if (isInvoice == 1 || invoiceType == 1) {
                    invoiceNoList.add(orderId);
                } else if (onAmount == 0) {
                    priceZerolist.add(orderId);
                } else if (!valueEquals(getBigDecimal(onAmount), invoiceTotal)) {
                    resultMap.put("orderPriceError", true);
                    priceErrorlist.add(orderId);
                } else if (!valueEquals(getBigDecimal(invoiceAmount), invoiceTotal)) {
                    invoicePriceErrorlist.add(orderId);
                } else {
                    okList.add(orderId);
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
    private boolean valueEquals(BigDecimal onAmount, BigDecimal invoiceTotal) {
        return subtract(onAmount, invoiceTotal).compareTo(BigDecimal.ZERO)==0;
    }

    private BigDecimal getBigDecimal(double invoice_total) {
        return new BigDecimal(invoice_total).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal subtract(BigDecimal invoiceAmount, BigDecimal invoiceTotal) {
        return invoiceTotal.subtract(invoiceAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private String getProductUnit(String productId) {
        String unit = null;
        try {
            String result = httpGet(unitUrl + productId, null);
            Map<String, String> resultMap = (Map<String, String>) GsonUtils.jsonToMap(result).get("result");
            unit = resultMap.get("unit");
        } catch (Exception e) {
        }
        return unit;
    }

    private Double getProductRate(String productId) {
        Double rate = 0d;
        try {
            String result = httpGet(rateUrl + productId, null);
            rate = (Double) GsonUtils.jsonToMap(result).get("Rate");
        } catch (Exception e) {
        }
        return rate;
    }

    private String getAreaName(Object obj) {
        String areaJson = httpGet(areaUrl + getIntValue(obj), null);
        return (String) GsonUtils.jsonToMap(areaJson).get("areaName");
    }

    private int getIntValue(Object obj) {
        return ((Double)obj).intValue();
    }

    private String httpGet(String url, Map<String, String> map) {
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
