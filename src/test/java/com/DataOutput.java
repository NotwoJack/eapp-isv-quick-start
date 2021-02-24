package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.PO.Data;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.util.Dbutils;
import com.util.HttpUtil;
import com.util.Md5Utils;

public class DataOutput {

	String appkey = "2914b07b82a54cf09d0cefb47428a176"; // app key
	static String requstSecret = "780950b7f4b34c4bbe26a2353cefa813"; // 请求秘钥
	long time = System.currentTimeMillis();
	private HttpUtil httpUtil = HttpUtil.getInstance();

	public static void main(String[] xxaargs) {
		DataOutput test = new DataOutput();
		System.out.println("请求秘钥" + requstSecret);

		test.PopBasicInfo(requstSecret);
	}

	public void PopBasicInfo(String requestSecret) {
		Map<String, String> params = new HashMap<String, String>();
		//小学
		String url = "https://interface.zjzwfw.gov.cn/gateway/api/001003001029/dataSharing/ruiAnAdmission1Info.htm";
		//初中
//		String url = "https://interface.zjzwfw.gov.cn/gateway/api/001003001029/dataSharing/ruiAnAdmission2Info.htm";
		String sign = Md5Utils.hash(appkey + requestSecret + time);
		params.put("appKey", appkey);
		params.put("sign", sign);
		params.put("requestTime", +time + "");
		//注意这个日期是不包括的
		params.put("startDate", "20160831");
		params.put("endDate", "20170901");

		String result = httpUtil.sendHttpPost(url, params);
		//System.out.println(result);

		JSONObject jsonObject = JSON.parseObject(result);
		JSONArray datas = jsonObject.getJSONArray("datas");
		List<Data> dataList = JSONObject.parseArray(datas.toJSONString(), Data.class);

		PreparedStatement stmt;
		Connection conn=null;
		try {
			conn= Dbutils.getConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = dataList.size();
		for (Data data : dataList){
//		for(int i=0;i<datas.length;i++){
			//小学
			String sql = "insert into ruian_info_new values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//初中
//			String sql = "insert into ruian_info_new2 values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("剩余：" + size --);
			try {
				stmt = (PreparedStatement) conn.prepareStatement(sql);
				stmt.setString(1, data.getCzrkrkid());
				stmt.setString(2, data.getCzrkcsdssx());
				stmt.setString(3, data.getCzrkssssxq());
				stmt.setString(4, data.getCzrkgmsfhm());
				stmt.setString(5, data.getCzrkxm());
				stmt.setString(6, data.getCzrkcym());
				stmt.setString(7, data.getCzrkxb());
				stmt.setString(8, data.getCzrkmz());
				stmt.setString(9, data.getCzrkcsrq());
				stmt.setString(10, data.getCzrkcsdgj());
				stmt.setString(11, data.getCzrkjggj());
				stmt.setString(12, data.getCzrkjgssx());
				stmt.setString(13, data.getCzrkzz());
				stmt.setString(14, data.getCzrkjlxmc());
				stmt.setString(15, data.getCzrkjwhmc());
				stmt.setString(16, data.getCzrkjlx());
				stmt.setString(17, data.getCzrkjwh());
				stmt.setString(18, data.getCzrkxzjd());
				stmt.setString(19, data.getCzrksjxgxsj());
				stmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
