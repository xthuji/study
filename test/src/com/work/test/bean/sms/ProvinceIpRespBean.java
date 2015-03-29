package com.work.test.bean.sms;

/**
 * Created by IntelliJ IDEA.
 * User: huji
 * Date: 14-7-29
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class ProvinceIpRespBean {
    private String code;
    private String msg;
    private ProvinceIpBean body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ProvinceIpBean getBody() {
        return body;
    }

    public void setBody(ProvinceIpBean body) {
        this.body = body;
    }
}
