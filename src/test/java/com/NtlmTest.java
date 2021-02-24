//package com;
//
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
///**
// * <p>
// * All rights Reserved, Designed By www.nriat.com
// * <p>
// * 注意：本内容仅限于温州中津先进科技研究院内部传阅，禁止外泄以及用于其他的商业目
// *
// * @author yibo
// * @version V1.0
// * @since 2020/11/18 15:05
// */
//public class NtlmTest {
//    @Test
//    public void test() {
//
//        String urlStr = "http://www.xxx.com/api/query?FormData=";
//
//        String formData = "";
//
//        try {
//            urlStr += URLEncoder.encode(formData, "UTF-8");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
//        System.out.println(urlStr);
//
//        String domain = ""; // May also be referred as realm
//        String userName = "user";
//        String password = "password";
//        String responseText = null;
//        try {
//            /** Get请求 */
//            responseText = getAuthenticatedResponse(urlStr, domain, userName, password);
//            /** Post请求 */
//            responseText = postAuthenticatedResponse(urlStr, domain, userName, password);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("response: " + responseText);
//
//    }
//}
