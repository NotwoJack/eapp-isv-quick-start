package com;

import com.util.HttpUtil;
import com.util.Md5Utils;

import java.util.HashMap;
import java.util.Map;

public class Odpsinsert {

    String appkey = "2914b07b82a54cf09d0cefb47428a176";  // app key
    static String requstSecret = "25a54bd8fe474ca39dd43cc83a4aa668"; // app密钥

    //密钥 2c954806eae345719e26cfa288da71d4
    //刷新密钥，用于获取请求密钥 48h 过期
    //"refreshSecret":"911291e6f7f74a379bda988ebd5ba0fa"
    long time = System.currentTimeMillis();
    private HttpUtil httpUtil = HttpUtil.getInstance();

    public static void main(String[] xxaargs) {
        Odpsinsert test = new Odpsinsert();
        //System.out.println("请求秘钥" + requstSecret);
        //String codeUrl = "https://wzjyj.wzer.net:8443/cas/oauth2.0/authorize?response_type=code&client_id=bj_wanghu&redirect_uri=http://oa.wzer.net/defaultroot/LogintoModule!logintoa.action&response_type=code";
        //String getUrl="https://wzjyj.wzer.net:8443/cas/oauth2.0/accessToken?grant_type=authorization_code&client_id=bj_wanghu&client_secret=BJ!@654wh999&redirect_uri=http://oa.wzer.net/defaultroot/LogintoModule!logintoa.action&code=";
        //test.printToken(codeUrl,getUrl);
        test.PopBasicInfo(requstSecret);
    }

    public void PopBasicInfo(String requestSecret) {
        Map<String, String> params = new HashMap<String, String>();
        //第一次获取刷新密钥和请求密钥使用
//        String url = "http://59.202.38.178/gateway/app/refreshTokenByKey.htm";

        //第二次，使用刷新密钥去获取请求密钥
        String url = "http://59.202.38.178/gateway/app/refreshTokenBySec.htm";
        String sign = Md5Utils.hash(appkey + requestSecret + time);
        params.put("appKey", appkey);
        params.put("sign", sign);
        params.put("requestTime", +time + "");
        String result = httpUtil.sendHttpPost(url, params);
        System.out.println(result);
    }

    public void printToken(String codeUrl,String getUrl){
        String code=httpUtil.sendHttpsGet(codeUrl);
        System.err.println(code);
        getUrl=getUrl+code;
        String result = httpUtil.sendHttpsGet(getUrl);
        System.err.println(result);
    }

}
