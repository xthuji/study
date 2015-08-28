package com.hj.test.bean.sms;

/**
 * Created by IntelliJ IDEA.
 * User: wangmingli
 * Date: 14-1-6
 * Time: 下午2:52
 */
public class SmsChannelBean {
    //标识
    private String id;
    //尾号
    private String spNumber;
    //指令
    private String spContent;
    //金额
    private String fee;
    //日限
    private String dayLimit;
    //月限
    private String monthLimit;
    //显示顺序 顺序是0、1、2
    private String showType;
    //通道类型
    private String style;
    //0 次数、1时间(s)
    private String unit;
    //针对单位次数的数字，如unit=1，num=5；5次错误将设置成错误通道
    private String num;
    //消息
    private String message;
    private String message2;
    
    private String serveOrderId;

    public String getServeOrderId() {
        return serveOrderId;
    }

    public void setServeOrderId(String serveOrderId) {
        this.serveOrderId = serveOrderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpNumber() {
        return spNumber;
    }

    public void setSpNumber(String spNumber) {
        this.spNumber = spNumber;
    }

    public String getSpContent() {
        return spContent;
    }

    public void setSpContent(String spContent) {
        this.spContent = spContent;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(String dayLimit) {
        this.dayLimit = dayLimit;
    }

    public String getMonthLimit() {
        return monthLimit;
    }

    public void setMonthLimit(String monthLimit) {
        this.monthLimit = monthLimit;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }
}
