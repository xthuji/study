package com.hj.test.bean.sms;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wangmingli
 * Date: 14-1-6
 * Time: 下午3:11
 */
public class SmsChannelConfBean {
    private String amount;

    private List<SmsChannelBean> list;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<SmsChannelBean> getList() {
        return list;
    }

    public void setList(List<SmsChannelBean> list) {
        this.list = list;
    }

    //    private Set<String> fee;
//
//    private Map<String,List<SmsChannelBean>> map;
//
//    public Set<String> getFee() {
//        return fee;
//    }
//
//    public void setFee(Set<String> fee) {
//        this.fee = fee;
//    }
//
//    public Map<String, List<SmsChannelBean>> getMap() {
//        return map;
//    }
//
//    public void setMap(Map<String, List<SmsChannelBean>> map) {
//        this.map = map;
//    }
}
