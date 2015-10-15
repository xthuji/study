package com.hj.test.work.letv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hj.test.excel.StringUtil;
import com.hj.test.tools.FileReadUtil;
import com.hj.test.tools.HttpUtils;
import com.hj.test.tools.PathUtil;
import com.hj.test.tools.HttpUtils.HttpConstant;

/**
 * 重置发票任务
 * @author huji
 * @version V1.0 
 * @since 1.0 2015年10月15日-下午2:41:22
 */
public class ImcReset {

    static Logger logger = LoggerFactory.getLogger(ImcReset.class);
    private static final String SPLIT_STR = ",";
    private static final String INVOICE_ORDER_TXT = "invoice_order.txt";
    
    static String url = "http://imc.shop.letv.com/shipmentsQueue/toReset?OrderId=";
    static String orderIds = "3994612722133, 3994613194341, 3994612781044, 3994611991295, 3994611442833, 3994612458144, 3994610720223";
    static boolean useFile = true;//是否读取文件中的订单号,忽略orderIds
    
    
    public static void main(String[] args) throws InterruptedException {
        Object[] orderIdArray = orderIds.split(SPLIT_STR);
        if (useFile) {
            orderIdArray = FileReadUtil.readFileList(PathUtil.getRealPath(INVOICE_ORDER_TXT)).toArray();
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < orderIdArray.length; i++) {
            String orderId = ((String)orderIdArray[i]).trim();
            Map<String, String> map = new HashMap<String, String>();
            map.put("Cookie", "_i_c_c_cookie_=\"MWU4ZTRjNjU2M2FhY2MwY2Y1MDlhNTcxNjJiYzkxYmQ=\"; _i_u_cookie_=\"eyJ1c2VySWQiOjE1NzE4LCJ1c2VyTmFtZSI6Imh1amlAbGV0di5jb20iLCJjbk5hbWUiOiLog6HlkIkifQ==\"");
            String result = HttpUtils.httpGet(url + orderId, null, "UTF-8", HttpConstant.LOCALHOST, 1, 1000, map);
            logger.info("orderId={},result={}",orderId,result);
            if (StringUtil.isBlank(result) || result.indexOf("\"code\":200")<0) {
                list.add(orderId);
            }
            Thread.currentThread().sleep(1000);
        }
        logger.error("errorList, size={},list={}", list.size(), list);
    }
}
