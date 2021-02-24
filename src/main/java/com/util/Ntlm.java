package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;

public class Ntlm {

    Gson gson = new Gson();

    public static void test(String urlStr) {

//        String urlStr = "http://www.xxx.com/api/query?FormData=";

        String formData = "";

        try {
            urlStr += URLEncoder.encode(formData, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        System.out.println(urlStr);

        String domain = "rajyj.edu.cn"; // May also be referred as realm
        String userName = "jk";
        String password = "raedu123";
        String responseText = null;
        try {
            /** Get请求 */
            responseText = getAuthenticatedResponse(urlStr, domain, userName, password);
            /** Post请求 */
//            responseText = postAuthenticatedResponse(urlStr, domain, userName, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("response: " + responseText);

    }

    private static String getAuthenticatedResponse(final String urlStr, final String domain, final String userName,
                                                   final String password) throws IOException {

        StringBuilder response = new StringBuilder();

        Authenticator.setDefault(new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(domain + "\\" + userName, password.toCharArray());
            }
        });
        URL urlRequest = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        System.out.println(conn.getContentLengthLong());
        System.out.println(conn.getResponseMessage());
        InputStream stream = conn.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String str = "";
        while ((str = in.readLine()) != null) {
            response.append(str);
        }
        in.close();

        return response.toString();
    }

    private static String postAuthenticatedResponse(final String urlStr, final String domain, final String userName,
                                                    final String password) throws IOException {

        StringBuilder response = new StringBuilder();

        Authenticator.setDefault(new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(domain + "\\" + userName, password.toCharArray());
            }
        });

        URL urlRequest = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");

        OutputStream out = conn.getOutputStream();
        out.flush();
        InputStream in = conn.getInputStream();
        // read .....
        System.out.println("Responce Code:    " + conn.getResponseCode());
        System.out.println("Responce Message: " + conn.getResponseMessage());

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str = "";
        while ((str = br.readLine()) != null) {
            response.append(str);
        }
        out.close();
        in.close();

        return response.toString();
    }

}

