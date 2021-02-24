package com;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.config.Constant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.util.AccessTokenUtil;
import com.util.DingDeptDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmallApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("hello world");
	}

	@Test
	public  void getDepts() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/dept/list");
		OapiEduDeptListRequest req = new OapiEduDeptListRequest();
		req.setSuperId(360245361L);
		req.setPageNo(1L);
		req.setPageSize(30L);
		OapiEduDeptListResponse rsp = client.execute(req, AccessTokenUtil.getToken());
		System.out.println(rsp.getBody());
	}

	@Test
	public void pingying(){
		Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		String[] newArray={"曾欣","戴耀演","杜一菲","黄安晴","蒋铠似","马天妤","俞博严","王湉然","吴奕恺","伍忆心","赵涨","仇若辰","戴思睿","姜童予","金芷萱","梁家涵","林珈而","卢可欣","卢一诺","滕博涵","滕茜","叶瑾萱","叶若萱","蔡万航","陈昱恒","董泽","姜佳航","姜佳豪","林以诺","林誉博","沈渤程","施羽宸","叶熙泽","徐佳奕","朱政宇"};
		List<String> list = Arrays.asList(newArray);
		Collections.sort(list, com);

	}

	@Test
	public void sss(){
		List<DingDeptDTO> dingDeptDTOL = new ArrayList<>();
		List<String> collect = dingDeptDTOL.stream().map(DingDeptDTO::getName).map(d -> d.split(",")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());


		String json = JSON.toJSONString(dingDeptDTOL);
		DingDeptDTO dingDeptDTO = new DingDeptDTO();
		dingDeptDTO.setName(json);
		String json2 = JSON.toJSONString(dingDeptDTO);
		JSONObject jsonObject = JSON.parseObject(json2);
		JSONArray data = jsonObject.getJSONArray("name");
		System.out.println(data);

	}

	@Test
	public void getUserRelation() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/user/relation/get");
		OapiEduUserRelationGetRequest req = new OapiEduUserRelationGetRequest();
		req.setClassId(360752040L);
		req.setFromUserid("1592443282027777147");
		OapiEduUserRelationGetResponse rsp = client.execute(req, "2b3e524b4d5f358991a183a6774a5111");
		System.out.println(rsp.getBody());
	}

	@Test
	public void jianquan(){
		String noce = null;
		StringBuffer buffer = new StringBuffer();
		Random r = new Random(1);
		for (int i = 0; i < 5; i++) {
			int ran1 = r.nextInt(100);
			buffer.append(String.valueOf(ran1));
		}
		noce = buffer.toString();
		String curTime = String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000);
		String checkSum = getCheckSum("4028a80e71f760fe0171f760fec70000", noce, curTime);
		Map<String, String> headerMap = new HashMap<>();
		headerMap.put("appKey", "cnbot_wzzj202005");
		headerMap.put("Nonce", noce);
		headerMap.put("CurTime", curTime);
		headerMap.put("CheckSum", checkSum);
		System.out.println(checkSum);
	}


	// 计算并获取CheckSum
	public static String getCheckSum(String appSecret, String nonce, String curTime) {
		return encode("sha1", appSecret + nonce + curTime);
	}
	// 计算并获取md5值
	public static String getMD5(String requestBody) {
		return encode("md5", requestBody);
	}
	private static String encode(String algorithm, String value) {
		if (value == null) {
			return null;
		}
		try {
			MessageDigest messageDigest
					= MessageDigest.getInstance(algorithm);
			messageDigest.update(value.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
