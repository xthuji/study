package com.hj.test.bean.sms;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Desc：游戏短信配置
 * User: wangmingli
 * Date: 14-7-7
 * Time: 下午5:32
 */
public class GameSmsConfBean {
    //省份
    private String province;
    //1移动 2联通 3电信
    private String mobileType;
    //金额，因json转对象时，要保证fee的顺序，所以使用LinkedHashSet
    private LinkedHashSet<String> fee;
    //短信金额通道
    private List<SmsChannelConfBean> smsChannelContent;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public LinkedHashSet<String> getFee() {
        return fee;
    }

    public void setFee(LinkedHashSet<String> fee) {
        this.fee = fee;
    }

    public List<SmsChannelConfBean> getSmsChannelContent() {
        return smsChannelContent;
    }

    public void setSmsChannelContent(List<SmsChannelConfBean> smsChannelContent) {
        this.smsChannelContent = smsChannelContent;
    }
}
