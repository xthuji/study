package com.hj.test.work;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.hj.test.tools.GsonUtils;
import com.hj.test.tools.HttpUtils;

public class TestTing {

    @Test
    public void postHttp() throws Exception {
        String url = "http://59.151.100.64:21001/rt/downloadTing/auth?";
//        String str = "schemeId=52&usrName=i454319337&devId=862751027201164&bookId=30010051&chapterId=4&quality=1&timestamp=1427791038900&sign=WQwkfoCl2LvvAOCLNAUSoKdDky20Stpb427nXFJ09ZDnt7Edo7L1Ef6Om1Yx40DaYoWsOKuhhVo20qRXoBK/7yiNdQ3s8qWWDIewv27gXhWZovK4AxHYV2Y0Y+/c0Py2+bElUJt1bJ6fEJEBXPXMVAuzkWPjpwPKCSbNSPDAGzM=";
        Map<String, String> params = new HashMap<String, String>();
        params.put("sign", "WQwkfoCl2LvvAOCLNAUSoKdDky20Stpb427nXFJ09ZDnt7Edo7L1Ef6Om1Yx40DaYoWsOKuhhVo20qRXoBK/7yiNdQ3s8qWWDIewv27gXhWZovK4AxHYV2Y0Y+/c0Py2+bElUJt1bJ6fEJEBXPXMVAuzkWPjpwPKCSbNSPDAGzM=");
        params.put("timestamp", "1427791038900");
        params.put("schemeId", "52");
        params.put("chapterId", "4");
        params.put("quality", "1");
        params.put("bookId", "30010051");
        params.put("devId", "862751027201164");
        params.put("usrName", "i454319337");
        
        System.out.println(GsonUtils.objectToJson(params));
        String string = HttpUtils.httpPostWithOutProxy(url, params);
        System.out.println(string);
    }
}
