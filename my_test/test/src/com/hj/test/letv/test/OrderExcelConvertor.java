package com.hj.test.letv.test;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.hj.test.excel.ExcelHelper;
import com.hj.test.excel.JxlExcelHelper;
import com.hj.test.tools.ExcelUtils;
import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.TimeUtils;

public class OrderExcelConvertor {
    
    private static final Log logger = LogFactory.getLog(OrderExcelConvertor.class);
    
    public static String productCode = "GWGMS11017";
    public static String productPrice = "1499";
    public static String department = "销售平台部/线下销售部";
    public static String customer = "乐视致新电子科技（天津）有限公司";
    public static Date now = new Date();
    
    /**
     * 转换excel文件
     * @throws Exception
     */
    @Test
    public void convertOrderExcel() throws Exception {
        
        List<DataBean1> list = new ArrayList<DataBean1>();
        String[] fieldNames = getFieldNames(DataBean1.class);
        try {
            File file = new File("E:\\excel\\superphone-2015-08-26.xls");
            ExcelHelper eh = JxlExcelHelper.getInstance(file);
            list = eh.readExcel(DataBean1.class, fieldNames, true);
            for (int i = 1; i<list.size(); i++) {
                DataBean1 data = list.get(i);
                String orderId = createOrderId(now, i);
                
                logger.info(orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String templatePath = "E:\\excel\\报表模板2.xls";
        String targetpath = "E:\\excel\\export"+getTimeStr(now)+".xls";
        createExcel(list, templatePath, targetpath);
    }
    
    public void testGetBeanField() throws Exception {
        String[] fieldNames = getFieldNames(DataBean2.class);
        logger.info(GsonUtils.objectToJson(fieldNames));
    }

    /**
     * 获取bean的属性名称
     * @param clazz
     * @return
     */
    private String[] getFieldNames(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int size = fields.length;
        String[] fieldNames = new String[size];
        for (int i = 0; i < size; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }
    
    /**
     * 转换list为数组
     * @param stringList
     * @return
     */
    private String[] toStringArray(List<String> stringList) {
        int size = stringList.size();
        String[] titles = new String[size];
        titles = stringList.toArray(titles);
        return titles;
    }
    
    /**
     * 创建订单
     * @param now
     * @param index
     * @return
     */
    private String createOrderId(Date now, int index) {
        String prex = "SDWBDH";
        String time = TimeUtils.formatDate(now, TimeUtils.SDF_YYYYMMDD);
        String num = replaceZero("100000", index);
        return prex + time + num;
    }

    /**
     * 获取时间字符串	yyyyMMddHHmmss格式
     * @param now	当前日期
     * @return
     */
    private String getTimeStr(Date now) {
        String time = TimeUtils.getFormatString(TimeUtils.Y_H_M_H_M_S_2, now);
        return time;
    }
    /**
     * 按格式补零
     * @param pattern	补零格式
     * @param num	数值
     * @return
     */
    private String replaceZero(String pattern, int num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
        String result = df.format(num);
        return result;
    }
    /**
     * 创建excel
     * @param list	数据list
     * @param templatePath	模版文件路径
     * @param targetPath	目标文件路径
     */
    private void createExcel(List<DataBean1> list, String templatePath, String targetPath) {
        List<DataBean2> newList = buildDataList(list); 
        writeExcelUseTemplate(templatePath, targetPath, newList);
//        writeExcel(templatePath, targetPath, newList);
        
    }

    /**
     * 使用 模版方式 写excel文件
     * @param templatePath	模版文件路径
     * @param targetPath	目标文件路径
     * @param newList	数据list
     */
    private void writeExcelUseTemplate(String templatePath, String targetPath, List<DataBean2> newList) {
        Map data = new HashMap();
        data.put("list", newList);
        
        ExcelUtils.createExcel(templatePath, data, targetPath, true);
    }

    /**
     * 使用 非模版方式 写excel文件
     * @param templatePath	模版文件路径
     * @param targetPath	目标文件路径
     * @param newList	数据list
     */
    private void writeExcel(String templatePath, String targetPath, List<DataBean2> newList) {
        File file1 = new File(targetPath);
        File file2 = new File(templatePath);
        ExcelHelper eh1 = JxlExcelHelper.getInstance(file1);
        ExcelHelper eh2 = JxlExcelHelper.getInstance(file2);
        try {
            if (!file1.exists()) {
                file1.createNewFile();
            }
//            eh1.writeExcel(DataBean2.class, newList);
            eh1.writeExcel(DataBean2.class, newList, getFieldNames(DataBean2.class), toStringArray(eh2.readExcelTitle(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建目标数据list
     * @param list	原始数据
     * @return
     */
    private List<DataBean2> buildDataList(List<DataBean1> list) {
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
        return newList;
    }
}
