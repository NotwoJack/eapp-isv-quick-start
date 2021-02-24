package com.util;

import com.config.Constant;
import com.controller.CallbackController;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取钉钉accesstoken
 *
 * @author yibo
 * @since 2020/6/2 10:07
 */

public class AccessTokenUtil {
    private static final Logger bizLogger = LoggerFactory.getLogger("BIZ_CALLBACKCONTROLLER");
    private static final Logger mainLogger = LoggerFactory.getLogger(CallbackController.class);


    public static String getToken() {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setAppkey(Constant.APP_KEY);
            req.setAppsecret(Constant.APP_SECRET);
            req.setHttpMethod("GET");
            OapiGettokenResponse rsp = client.execute(req);
            String accessToken = rsp.getAccessToken();
            bizLogger.info("AccessToken: " + accessToken);
            return accessToken;

        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }


}