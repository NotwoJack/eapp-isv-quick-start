package com.controller;

import com.config.Constant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.DingTalkSignatureUtil;
import com.dingtalk.api.request.OapiServiceGetCorpTokenRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiServiceGetCorpTokenResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.config.ApiUrlConstant;
import com.taobao.api.ApiException;

import com.util.Ntlm;
import com.util.OlingoSampleApp;
import com.util.ServiceResult;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.*;


/**
 * ISV E应用Quick-Start示例代码
 * 实现了最简单的免密登录（免登）功能
 */
@RestController
public class IndexController {
	private static final Logger bizLogger = LoggerFactory.getLogger(IndexController.class);

	/**
	 * 欢迎页面
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}


	/**
	 * 钉钉用户登录，显示当前登录的企业和用户
	 * @param corpId			授权企业的CorpId
	 * @param requestAuthCode	免登临时code
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult login(@RequestParam(value = "corpId") String corpId,
									@RequestParam(value = "authCode") String requestAuthCode) {
		Long start = System.currentTimeMillis();
		//获取accessToken,注意正是代码要有异常流处理
		OapiServiceGetCorpTokenResponse oapiServiceGetCorpTokenResponse = getOapiServiceGetCorpToken(corpId);
		String accessToken = oapiServiceGetCorpTokenResponse.getAccessToken();

		//获取用户信息
		OapiUserGetuserinfoResponse oapiUserGetuserinfoResponse = getOapiUserGetuserinfo(accessToken,requestAuthCode);

		//3.查询得到当前用户的userId
		// 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
		String userId = oapiUserGetuserinfoResponse.getUserid();


		//返回结果
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("userId",userId);
		resultMap.put("corpId",corpId);
		ServiceResult serviceResult = ServiceResult.success(resultMap);
		return serviceResult;
	}

	@RequestMapping(value = "/readAPI", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResult readAPI(@RequestParam(value = "serviceUrl") String serviceUrl) throws IOException, ODataException {

		OlingoSampleApp.readAPI(serviceUrl);

		//返回结果
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("userId","userId");
		ServiceResult serviceResult = ServiceResult.success(resultMap);
		return serviceResult;
	}

	@RequestMapping(value = "/readAPI2", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResult readAPI2(@RequestParam(value = "serviceUrl") String serviceUrl) throws IOException, ODataException {

		OlingoSampleApp.readAPI2(serviceUrl);

		//返回结果
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("userId","userId");
		ServiceResult serviceResult = ServiceResult.success(resultMap);
		return serviceResult;
	}

	@RequestMapping(value = "/readAPI3", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResult readAPI3(@RequestParam(value = "serviceUrl") String serviceUrl) throws IOException, ODataException {

		Ntlm.test(serviceUrl);

		//返回结果
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("userId","userId");
		ServiceResult serviceResult = ServiceResult.success(resultMap);
		return serviceResult;
	}

	@RequestMapping(value = "/testNTLMConnection", method = RequestMethod.GET)
	@ResponseBody
	public void testNTLMConnection(@RequestParam(value = "serviceUrl") String serviceUrl) throws Exception {
		// Method 1 : authentication in URL
//		jcifs.Config.registerSmbURLHandler();
//		URL urlRequest = new URL("http://rajyj.edu.cn%5Cjk:raedu123@http://172.100.250.61/Citrix/Monitor/OData/v2/Data/Machines/");

		// or Method 2 : authentication via System.setProperty()
		jcifs.Config.setProperty("http.auth.ntlm.domain", "rajyj.edu.cn");
		jcifs.Config.setProperty("jcifs.smb.client.domain", "rajyj.edu.cn");
		jcifs.Config.setProperty("jcifs.smb.client.username", "jk");
		jcifs.Config.setProperty("jcifs.smb.client.password", "raedu123");
		jcifs.Config.setProperty("jcifs.netbios.hostname", "172.100.250.61");
		jcifs.Config.setProperty("java.protocol.handler.pkgs", "jcifs");
		 URL urlRequest = new URL(serviceUrl);

		HttpURLConnection conn = (HttpURLConnection) urlRequest.openConnection();

		StringBuilder response = new StringBuilder();

		try {
			InputStream stream = conn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(stream));

			String str = "";
			while ((str = in.readLine()) != null) {
				response.append(str);
			}
			in.close();

			System.out.println(response);
		} catch(IOException err) {
			System.out.println(err);
		} finally {
			Map<String, String> msgResponse = new HashMap<String, String>();

			for (int i = 0;; i++) {
				String headerName = conn.getHeaderFieldKey(i);
				String headerValue = conn.getHeaderField(i);
				if (headerName == null && headerValue == null) {
					break;
				}
				msgResponse.put(headerName == null ? "Method" : headerName, headerValue);
			}

			System.out.println(msgResponse);
		}
	}

	@RequestMapping(value = "/testNTLMConnection2", method = RequestMethod.GET)
	@ResponseBody
	public void testNTLMConnection2(@RequestParam(value = "serviceUrl") String serviceUrl) throws Exception {
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
		HttpGet httpget = new HttpGet(serviceUrl);
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


	/**
	 * ISV获取企业访问凭证
	 * @param corpId	授权企业的corpId
	 */
	private OapiServiceGetCorpTokenResponse getOapiServiceGetCorpToken(String corpId) {
		if (corpId == null || corpId.isEmpty()) {
			return null;
		}

		long timestamp = System.currentTimeMillis();
		//正式应用应该由钉钉通过开发者的回调地址动态获取到
		String suiteTicket = getSuiteTicket(Constant.SUITE_KEY);
		String signature = DingTalkSignatureUtil.computeSignature(Constant.SUITE_SECRET, DingTalkSignatureUtil.getCanonicalStringForIsv(timestamp, suiteTicket));
		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("timestamp", String.valueOf(timestamp));
		params.put("suiteTicket", suiteTicket);
		params.put("accessKey", Constant.SUITE_KEY);
		params.put("signature", signature);
		String queryString = DingTalkSignatureUtil.paramToQueryString(params, "utf-8");
		DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_GET_CORP_TOKEN + "?" + queryString);
		OapiServiceGetCorpTokenRequest request = new OapiServiceGetCorpTokenRequest();
		request.setAuthCorpid(corpId);
		OapiServiceGetCorpTokenResponse response;
		try {
			response = client.execute(request);
		} catch (ApiException e) {
			bizLogger.info(e.toString(),e);
			return null;
		}
		if (response == null || !response.isSuccess()) {
			return null;
		}
		return response;
	}



	/**
	 * 通过钉钉服务端API获取用户在当前企业的userId
	 * @param accessToken	企业访问凭证Token
	 * @param code			免登code
	 * @
	 */
	private OapiUserGetuserinfoResponse getOapiUserGetuserinfo(String accessToken, String code) {
		DingTalkClient client = new DefaultDingTalkClient(ApiUrlConstant.URL_GET_USER_INFO);
		OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
		request.setCode(code);
		request.setHttpMethod("GET");

		OapiUserGetuserinfoResponse response;
		try {
			response = client.execute(request, accessToken);
		} catch (ApiException e) {
			e.printStackTrace();
			return null;
		}
		if (response == null || !response.isSuccess()) {
			return null;
		}
		return response;
	}




	/**
	 * suiteTicket是一个定时变化的票据，主要目的是为了开发者的应用与钉钉之间访问时的安全加固。
	 * 测试应用：可随意设置，钉钉只做签名不做安全加固限制。
	 * 正式应用：开发者应该从自己的db中读取suiteTicket,suiteTicket是由开发者在开发者平台设置的应用回调地址，由钉钉定时推送给应用，
	 * 由开发者在回调地址所在代码解密和验证签名完成后获取到的.正式应用钉钉会在开发者代码访问时做严格检查。
	 * @return suiteTicket
	 */
	private String getSuiteTicket(String suiteKey){
		//正式应用必须由应用回调地址从钉钉推送获取
		return "temp_suite_ticket_only4_test";

	}
}


