//package com;
//
//import com.seewo.open.sdk.DefaultSeewoClient;
//import com.seewo.open.sdk.SeewoClient;
//import com.seewo.open.sdk.auth.Account;
//import com.seewo.open.sdk.gen.DeviceListDeviceStatusFromUnitParam;
//import com.seewo.open.sdk.gen.DeviceListDeviceStatusFromUnitRequest;
//import com.seewo.open.sdk.gen.DeviceListDeviceStatusFromUnitResult;
//import org.junit.Test;
//
///**
// * <p>
// * All rights Reserved, Designed By www.nriat.com
// * <p>
// * 注意：本内容仅限于温州中津先进科技研究院内部传阅，禁止外泄以及用于其他的商业目
// *
// * @author yibo
// * @version V1.0
// * @since 2020/11/18 11:03
// */
//public class SeewoTest {
//
//    @Test
//    public void test(){
//        //初始化客户端
//        SeewoClient seewoClient = new DefaultSeewoClient(new Account("47a8ed86625e42cd853b50b3138924ce", "ORqBvnhqZS0xBOaICvIMV96P0ekFucwy"));
//        DeviceListDeviceStatusFromUnitParam param = new DeviceListDeviceStatusFromUnitParam();
//        //请求体，MimeType为 application/json
//        DeviceListDeviceStatusFromUnitParam.JSONRequestBody requestBody = DeviceListDeviceStatusFromUnitParam.JSONRequestBody.builder()
//                .inviteCode("inviteCode")
//                .build();
//        param.setRequestBody(requestBody);
//        param.setRequestBody(requestBody);
//        DeviceListDeviceStatusFromUnitRequest request = new DeviceListDeviceStatusFromUnitRequest(param);
//        System.out.println("入参：" +request);
//        //如果想要调用沙箱环境，请通过设置 request 对象的 serverUrl 属性，如：
//        //request.setServerUrl("https://openapi.test.seewo.com")
////执行请求，如果想获取到com.seewo.open.sdk.HttpResponse对象，请调用 seewoClient.execute 方法
//        DeviceListDeviceStatusFromUnitResult result = seewoClient.invoke(request);
//        System.out.println("出参：" +result);
//    }
//}
