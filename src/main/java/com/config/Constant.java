package com.config;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 应用的SuiteKey，登录开发者后台，点击应用管理，进入应用详情可见
     * CorpId
     */
    //机关二幼
//    public static final String SUITE_KEY="ding47985e542563873b35c2f4657eb6378f";
    //中津测试
//    public static final String SUITE_KEY="ding6014f671da40ac3135c2f4657eb6378f";
    //中津欢乐小学
    public static final String  SUITE_KEY="ding80b2a462acf58b974ac5d6980864d335";
    //中津欢乐小学 小程序
//    public static final String SUITE_KEY="ding80b2a462acf58b974ac5d6980864d335";
//北大附
//    public static final String SUITE_KEY="ding45d109897a0d472635c2f4657eb6378f";
//水心
//    public static final String SUITE_KEY="ding58a1f6fcdacf13bd35c2f4657eb6378f";
//    /**
//     * 应用的SuiteSecret，登录开发者后台，点击应用管理，进入应用详情可见
//     */
    //机关二幼
//    public static final String SUITE_SECRET="xJ7ro1GumojJso_wnRNZue_iJQWIjecxCxVbfkuQLrKaEPyAj6SwHshPDggviPvi";
    //中津测试
//    public static final String SUITE_SECRET="aDzibvUNw86ZrJH2KIYQ1EXni5BPUNVLc1fg1GBuP64QyGkG4gfcNm2F-JvA4rLu";
    //中津欢乐小学
    public static final String SUITE_SECRET="_e5s86wwxO4nG_TKtmw1jKXJOureYhhQepMcBJD3A24xDrnlhO06yFMa60Kt6GFM";
    //中津欢乐小学 小程序
//    public static final String SUITE_SECRET="i5Pc2dWBjFyrtbP1Jj6-ySVkDBB8z4YNHHfkq4g2MNMUn_Y6uFpEAppXuE9LAOkQ";
//北大附
//    public static final String SUITE_SECRET="tlfkd7NOELRDDVLk6_Ajhi8DmcktvrdahDyMx9geEolVEi02UzQn_u5jdiwjvTIz";
//水心
//    public static final String SUITE_SECRET="rE9DH9GiU4l6fduYCYRRBOoLIqb-tCwZbpox6JKP4Ay0uEVWh7EHURiMlC1ysL8t";
//    /**
//     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
//     */
    //机关二幼
//    public static final String APP_KEY="ding6yveofsgammewp0w";
    //中津测试
//    public static final String APP_KEY="dingx4hmrl0gyhsbiob9";
    //中津欢乐小学
    public static final String APP_KEY="dingbvstcqniwnjgi4y7";
    //中津欢乐小学小程序
//    public static final String APP_KEY="dingc2wmdxz0nbj41hcj";
//北大附
//    public static final String APP_KEY="dingwut2vmp5oiiwkc4y";
//水心
//    public static final String APP_KEY="dingrm8nogyalwnzdt2k";
//    /**
//     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
//     */
    //机关二幼
//    public static final String APP_SECRET="xJ7ro1GumojJso_wnRNZue_iJQWIjecxCxVbfkuQLrKaEPyAj6SwHshPDggviPvi";
    //中津测试
//    public static final String APP_SECRET="aDzibvUNw86ZrJH2KIYQ1EXni5BPUNVLc1fg1GBuP64QyGkG4gfcNm2F-JvA4rLu";
    //中津欢乐小学
    public static final String APP_SECRET="_e5s86wwxO4nG_TKtmw1jKXJOureYhhQepMcBJD3A24xDrnlhO06yFMa60Kt6GFM";
//中津欢乐小学 小程序
//    public static final String APP_SECRET="i5Pc2dWBjFyrtbP1Jj6-ySVkDBB8z4YNHHfkq4g2MNMUn_Y6uFpEAppXuE9LAOkQ";
//北大附
//    public static final String APP_SECRET="tlfkd7NOELRDDVLk6_Ajhi8DmcktvrdahDyMx9geEolVEi02UzQn_u5jdiwjvTIz";
//水心
//    public static final String APP_SECRET="rE9DH9GiU4l6fduYCYRRBOoLIqb-tCwZbpox6JKP4Ay0uEVWh7EHURiMlC1ysL8t";
//    /**
//     * 回调URL加解密用。应用的数据加密密钥，登录开发者后台，点击应用管理，进入应用详情可见
//     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成，ISV(服务提供商)推荐使用注册套件时填写的EncodingAESKey
//     */
    public static final String ENCODING_AES_KEY = "abcdefghijabcdefghijabcdefghijabcdefghij123";

    /**
     * 回调URL签名用。应用的签名Token, 登录开发者后台，点击应用管理，进入应用详情可见
     * 加解密需要用到的token，ISV(服务提供商)推荐使用注册套件时填写的token，普通企业可以随机填写
     */
    public static final String TOKEN = "123456";

//    public static final String AccessToken = "4877634b9c3333b59b6c62ccc2a5b65f";

    public static final String CALLBACK_URL_HOST = "http://zj.nriat.com:8056";

    public static final String DELETE_CALLBACK = "https://oapi.dingtalk.com/call_back/delete_call_back";

    public static final String REGISTER_CALLBACK = "https://oapi.dingtalk.com/call_back/register_call_back";

    public static final String UPDATE_CALLBACK = "https://oapi.dingtalk.com/call_back/update_call_back";


}
