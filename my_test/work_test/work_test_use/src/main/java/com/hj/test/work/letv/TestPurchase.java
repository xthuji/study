package com.hj.test.work.letv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableCellFormat;

import com.hj.test.excel.ExcelTools;
import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.SqlHelper;
import com.hj.test.tools.TimeUtils;

public class TestPurchase {
    public static void main(String[] args) throws IOException {
//        String sql = "select o.order_id,o.order_status_id,o.order_way,o.province_id,o.city_id,o.district_id,o.invoice_type,o.invoice_title, oi.product_name,oi.final_price,oi.quantity, o.paid_amount, oi.pcode,oi.product_type, o.home_installation,oi.product_id,o.financial_no,o.payment_method_id,o.oa_apply_no,o.contract_no,o.valid_name,o.sales_dept, coi.customer_name, o.storage_id,o.order_buss_flag,o.rebates_amount,o.order_desc from custom_order_info coi left join orders o on coi.order_id = o.order_id left join order_items oi on o.order_id = oi.order_id where o.order_way = 1 and coi.uid=?";
        String sql = "select o.*,oi.product_id,oi.quantity * oi.quantity_in_group as quantity,oi.final_price,oi.product_name,oi.pcode,oi.product_type,coi.uid,coi.customer_name,cd.department_name from custom_order_info coi left join orders o on coi.order_id = o.order_id left join order_items oi on o.order_id = oi.order_id LEFT JOIN customer_department cd ON o.sales_dept = cd.department_id where o.order_way = 1 and coi.uid=?";
        String[] params = {"11901"};
        java.sql.ResultSet rb = SqlHelper.executeQuery(sql, params);
        if(rb == null)return;
        String timeString = TimeUtils.GetCurrentTime(TimeUtils.sdfStr);
        File file = new File("D:/result"+timeString+".xls");
        if (!file.exists()) {
            file.createNewFile();
        }
        
        List<Map<String,String>> allData = SqlHelper.getAllData(rb);
        System.out.println("allDataSize:\n" + allData.size());
        
        ArrayList<Map<String, String>> dataTest = new ArrayList<Map<String,String>>();
        dataTest.add(allData.get(0));
//        dataTest.addAll(allData);
        System.out.println("dataTest:\n" + GsonUtils.objectToJson(dataTest));
        try {
            createExcel(file, allData);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            SqlHelper.close(rb, SqlHelper.getPs(), SqlHelper.getConn());
        }
        System.out.println("success!");
    }

    private static void createExcel(File file, List<Map<String,String>> allData) throws FileNotFoundException, IOException, Exception {
        OutputStream os = new FileOutputStream(file);
         
        jxl.write.WritableWorkbook wbook = Workbook.createWorkbook(os);
        jxl.write.WritableSheet wsheet = wbook.createSheet("Sheet1", 1);
        //EXCEL标题
        String[] titles = new String[] {"订单号", "订单状态","订单来源","所在省","所在市","所在区","发票类型","发票抬头","商品名称","销价","订货数量","实际支付金额","商品编码","商品类型","是否上门安装","商品ID","财务编号","支付方式","价格申请oa号","合同编号","创建人","所属一级部门","二级部门","线下客户名称","发货仓库","是否为返利订单","返利金额","备注"};
        wsheet.setColumnView(0,20); // 设置列的宽度
        wsheet.setColumnView(1,10); 
        wsheet.setColumnView(2,15); 
        //wsheet.setColumnView(3,10); 
//          wsheet.setColumnView(4,20); 
        wsheet.setColumnView(3,12); // 设置列的宽度
        wsheet.setColumnView(4,12); // 设置列的宽度
        wsheet.setColumnView(5,20); // 设置列的宽度
        //wsheet.setColumnView(8,20); // 设置列的宽度
//          wsheet.setColumnView(9,20); 
        wsheet.setColumnView(6,20);
        wsheet.setColumnView(7,20);// 设置列的宽度
        wsheet.setColumnView(8,20); // 设置列的宽度
        wsheet.setColumnView(9,20); // 设置列的宽度
        wsheet.setColumnView(10,10); // 设置列的宽度
        wsheet.setColumnView(11,10); // 设置列的宽度
        wsheet.setColumnView(12,20); // 设置列的宽度
        wsheet.setColumnView(13,20); // 设置列的宽度
        wsheet.setColumnView(14,25); // 设置列的宽度
        wsheet.setColumnView(15,30); // 设置列的宽度
        wsheet.setColumnView(16,20); // 设置列的宽度
        wsheet.setColumnView(17,20); 
        wsheet.setColumnView(18,20);
        wsheet.setColumnView(19,20);// 设置列的宽度
        wsheet.setColumnView(20,18); // 设置列的宽度
        wsheet.setColumnView(21,18); // 设置列的宽度
        wsheet.setColumnView(22,20);
        wsheet.setColumnView(23,20);// 设置列的宽度
        wsheet.setColumnView(24,20); // 设置列的宽度
        wsheet.setColumnView(25,20);// 设置列的宽度
        wsheet.setColumnView(26,20); // 新增返利金额列
        wsheet.setColumnView(27,30); // 设置列的宽度
        Map<String,WritableCellFormat> format = new HashMap<String,WritableCellFormat>();
        
        ExcelTools.addCells(wsheet, titles, 0,null);//为EXCEL中添加数据（标题）
        int i = 0;
        for (Map<String,String> rb : allData) {
            
                String[] values = new String[titles.length];
                values[0] = rb.get("ORDER_ID") == null ? "" : String.valueOf(rb.get("ORDER_ID")).replaceAll(" ", "");
                values[1] = rb.get("ORDER_STATUS_ID") == null ? "" : String.valueOf(rb.get("ORDER_STATUS_ID")).replaceAll(" ", "");
                values[2] = rb.get("ORDER_WAY") == null ? "" : String.valueOf(rb.get("ORDER_WAY")).replaceAll(" ", "");
//              values[3] = rb.get("RECEIVER") == null ? "" : String.valueOf(rb.get("RECEIVER")).replaceAll(" ", "");
//              values[4] = rb.get("ADDRESS") == null ? "" : String.valueOf(rb.get("ADDRESS")).replaceAll(" ", "");
                values[3] = rb.get("PROVINCE_ID") == null ? "" : String.valueOf(rb.get("PROVINCE_ID")).replaceAll(" ", "");
                values[4] = rb.get("CITY_ID") == null ? "" : String.valueOf(rb.get("CITY_ID")).replaceAll(" ", "");
                values[5] = rb.get("DISTRICT_ID") == null ? "" : String.valueOf(rb.get("DISTRICT_ID")).replaceAll(" ", "");
//              values[8] = rb.get("MOBILE") == null ? "" : String.valueOf(rb.get("MOBILE")).replaceAll(" ", "");
//              values[9] = rb.get("TELPHONE") == null ? "" : String.valueOf(rb.get("TELPHONE")).replaceAll(" ", "");
                values[6] = rb.get("INVOICE_TYPE") == null ? "" : String.valueOf(rb.get("INVOICE_TYPE")).replaceAll(" ", "");
                values[7] = rb.get("INVOICE_TITLE") == null ? "" : String.valueOf(rb.get("INVOICE_TITLE")).replaceAll(" ", "");
                values[8] = rb.get("PRODUCT_NAME") == null ? "" : String.valueOf(rb.get("PRODUCT_NAME")).replaceAll(" ", "");
                values[9] = rb.get("FINAL_PRICE") == null ? "" : String.valueOf(rb.get("FINAL_PRICE")).replaceAll(" ", "");
                values[10] = rb.get("QUANTITY") == null ? "" : String.valueOf(rb.get("QUANTITY")).replaceAll(" ", "");
                values[11] = rb.get("PAID_AMOUNT") == null ? "" : String.valueOf(rb.get("PAID_AMOUNT")).replaceAll(" ", "");
                values[12] = rb.get("PCODE") == null ? "" : String.valueOf(rb.get("PCODE")).replaceAll(" ", "");
                values[13] = rb.get("PRODUCT_TYPE") == null ? "" : String.valueOf(rb.get("PRODUCT_TYPE")).replaceAll(" ", "");
                values[14] = rb.get("HOME_INSTALLATION") == null ? "" : String.valueOf(rb.get("HOME_INSTALLATION")).replaceAll(" ", "");
                values[15] = rb.get("PRODUCT_ID") == null ? "" : String.valueOf(rb.get("PRODUCT_ID")).replaceAll(" ", "");
                values[16] = rb.get("FINANCIAL_NO") == null ? "" : String.valueOf(rb.get("FINANCIAL_NO")).replaceAll(" ", "");
                values[17] = rb.get("PAYMENT_METHOD_ID") == null ? "" : String.valueOf(rb.get("PAYMENT_METHOD_ID")).replaceAll(" ", "");
                values[18] = rb.get("OA_APPLY_NO") == null ? "" : String.valueOf(rb.get("OA_APPLY_NO")).replaceAll(" ", "");
                values[19] = rb.get("CONTRACT_NO") == null ? "" : String.valueOf(rb.get("CONTRACT_NO")).replaceAll(" ", "");
                values[20] = rb.get("NAME") == null ? "" : String.valueOf(rb.get("NAME")).replaceAll(" ", "");
                if(rb.get("DEPARTMENT_NAME") == null){//部门名称为空
                    values[21] = "";
                    values[22] = "";
                }else{
                    String value = String.valueOf(rb.get("DEPARTMENT_NAME")).replaceAll(" ", "");
                    String[] values1 = value.split("/");
                    values[21] = values1[0];
                    values[22] = values1[1];
                }
                //values[25] = rb.get("SALES_DEPT_NAME") == null ? "" : String.valueOf(rb.get("SALES_DEPT_NAME")).replaceAll(" ", "");
                values[23] = rb.get("CUSTOMER_NAME") == null ? "" : String.valueOf(rb.get("CUSTOMER_NAME")).replaceAll(" ", "");
                values[24] = rb.get("STORAGE_ID") == null ? "" : String.valueOf(rb.get("STORAGE_ID")).replaceAll(" ", "");
                values[25] = rb.get("ORDER_BUSS_FLAG") == null ? "" : String.valueOf(rb.get("ORDER_BUSS_FLAG")).replaceAll(" ", "");
                values[26] = rb.get("REBATES_AMOUNT") == null ? "" : String.valueOf(rb.get("REBATES_AMOUNT")).replaceAll(" ", "");
                values[27] = rb.get("ORDER_DESC") == null ? "" : String.valueOf(rb.get("ORDER_DESC")).replaceAll(" ", "");
                //为EXCEL中添加数据
                ExcelTools.addCells(wsheet, values, i + 1,format);
            i++;
        }
        //将生成好的EXCEL文件流输出到页面
        wbook.write();
        wbook.close();
        os.flush();
        os.close();
    }


}
