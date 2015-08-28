package com.hj.test.usetest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hj.test.tools.ExcelUtils;

/**
 * 使用ExcleUtils工具生成Excle文件
 * @author huji
 *
 */
public class ExcelTest {

	public static void main(String[] args) throws Exception {
		Map data = new HashMap();
		List details = new ArrayList();
        Map newRec = new HashMap();
        // 订单号  ${row.order_id}
        newRec.put("order_id", "1234567890");
        // 订单状态 ${row.status}
        newRec.put(
            "status",
            "已支付");
        // 退款单标记 ${row.refund_flag}
        newRec.put("refund_flag", "可退款");
        // 退款状态 ${row.refund_status}
        newRec.put(
            "refund_status",
            "已退款");
        // 退款单号 ${row.refund_id}
        newRec.put("refund_id", "123456789001");
        // 退款支付单号  ${row.order_original_flow_id}
        newRec.put(
            "order_original_flow_id",
            "12345678900101");
        // 支付流水单号  ${row.payment_seq_id}
        newRec.put("payment_seq_id", "123456789001001");
        // 交易日期 ${row.update_time}
        newRec.put("update_time", new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date()));
        // 收款金额 ${row.total_amount_after}
        newRec.put("total_amount_after", 101.00F);
        details.add(newRec);
        details.add(newRec);
        
        data.put("list", details);
        // 期间：${time}
        String time = new SimpleDateFormat("yyyy年MM月").format(new Date());
        data.put("time", time);
        // 制表时间：${createDay}
        data.put("createDay", new SimpleDateFormat("yyyy年MM月dd日 HH:mm").format(new Date()));
        
		ExcelUtils.createExcel("报表模板.xls", data, "报表.xls");
	}

}
