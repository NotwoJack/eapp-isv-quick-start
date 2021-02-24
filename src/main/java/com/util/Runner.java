package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;

/**
 * <p>
 * All rights Reserved, Designed By www.nriat.com
 * <p>
 * 注意：本内容仅限于温州中津先进科技研究院内部传阅，禁止外泄以及用于其他的商业目
 *
 * @author yibo
 * @version V1.0
 * @since 2020/11/19 10:23
 */
//@Component
public class Runner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(AuthScope.ANY,
                new NTCredentials("jk", "raedu123", null, "rajyj.edu.cn"));

        PoolingHttpClientConnectionManager connPool = new PoolingHttpClientConnectionManager();
        connPool.setMaxTotal(4000);
        connPool.setDefaultMaxPerRoute(4000);
        RequestConfig config = RequestConfig.custom().setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM)).build();
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connPool).setDefaultRequestConfig(config).build();

        HttpClientContext context = HttpClientContext.create();
        context.setCredentialsProvider(credsProvider);

        // Execute a cheap method first. This will trigger NTLM authentication
        HttpGet httpget = new HttpGet("http://172.100.250.61/Citrix/Monitor/OData/v2/Data/Machines");
        CloseableHttpResponse response1 = client.execute(httpget, context);
        try {
            HttpEntity entity1 = response1.getEntity();
            System.out.println(entity1.toString());
            InputStream content = entity1.getContent();
            byte[] byteArr = new byte[content.available()];
            content.read(byteArr);
            String str = new String(byteArr);
            System.out.println(str);
        } finally {
            response1.close();
        }
    }
}
