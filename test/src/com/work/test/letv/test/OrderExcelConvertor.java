package com.work.test.letv.test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.TabableView;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import com.work.test.excel.ExcelHelper;
import com.work.test.excel.JxlExcelHelper;
import com.work.test.tools.ExcelUtils;
import com.work.test.tools.TimeUtils;

public class OrderExcelConvertor {
    public static String productCode = "GWGMS11017";
    public static String productPrice = "1499";
    public static String department = "销售平台部/线下销售部";
    public static String customer = "乐视致新电子科技（天津）有限公司";
    public static Date now = new Date();
    
    @Test
    public void convertOrderExcel() throws Exception {
        
        List<DataBean1> list = new ArrayList<DataBean1>();
        String[] titleNames = new String[]{"收货人姓名","所在省","所在市","所在区","收货人地址","收货人手机","收货人电话","支付方式","发票类型","发票抬头","商品编码/套装编码","价格","订货数量","支付订单号","userid","支付时间"};
        String[] fieldNames = new String[]{"user", "province", "city", "town", "address", "mobile", "phone", "payType", "invoiceType", "invoiceTitle", "productCode", "price", "productNum", "payOrderId", "userId", "payTime"};
        try {
            File file = new File("E:\\excel\\superphone-2015-08-21.xls");
            ExcelHelper eh = JxlExcelHelper.getInstance(file);
            list = eh.readExcel(DataBean1.class, fieldNames, true);
            System.out.println("-----------------POI2007.xlsx-----------------");
            for (int i = 1; i<list.size(); i++) {
                DataBean1 data = list.get(i);
                String orderId = createOrderId(now, i);
                
                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String templatePath = "E:\\excel\\报表模板.xls";
        String targetpath = "E:\\excel\\export"+getTimeStr(now)+".xls";
        createExcel(list, templatePath, targetpath);
    }
    
    private String createOrderId(Date now, int index) {
        String prex = "SDWBDH";
        String time = getTimeStr(now);
        String num = replaceZero("100000", index);
        return prex + time + num;
    }

    private String getTimeStr(Date now) {
        String time = TimeUtils.formatDate(now, TimeUtils.SDF_YYYYMMDD);
        return time;
    }
    private String replaceZero(String pattern, int num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
        String result = df.format(num);
        return result;
    }
    private void createExcel(List<DataBean1> list, String templatePath, String targetPath) {
        List<DataBean2> newList = new ArrayList<DataBean2>();
        int index = 0;
        for (DataBean1 dataBean1 : list) {
            DataBean2 dataBean2 = new DataBean2();
            try {
                BeanUtils.copyProperties(dataBean2, dataBean1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            index++;
            dataBean2.setOrderId(createOrderId(now, index));
            dataBean2.setCustomer(customer);
            dataBean2.setInvoiceType("不需要发票");
            dataBean2.setOa("无");
            dataBean2.setContract("无");
            dataBean2.setDepartment(department);
            dataBean2.setIsFanLi("否");
            dataBean2.setIsShangmen("否");
            dataBean2.setProductCode(productCode);
            dataBean2.setPrice(productPrice);
            newList.add(dataBean2);
        } 
        Map data = new HashMap();
        data.put("list", newList);
        
        ExcelUtils.createExcel(templatePath, data, targetPath, true);
        
    }
}
