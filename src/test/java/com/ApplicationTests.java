package com;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("hello world");
	}

	@Test
	public void date() {
//		LocalDate now = LocalDate.parse("2020-02-01");
//		String startDate = null;
//		String endDate = null;
//		LocalDate semesterBegin = LocalDate.parse(now.getYear()+"-03-01");
//		LocalDate semesterEnd = LocalDate.parse(now.getYear()+"-09-01");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		if(now.isBefore(semesterBegin)){
//			//学期开始之前，表示还在上一个学期
//			startDate = semesterEnd.minusYears(1).atStartOfDay().format(df);
//			endDate = now.atStartOfDay().format(df);
//		}else if(now.isAfter(semesterEnd)){
//			//学期已经结束，表示到了下个学期
//			startDate = now.atStartOfDay().format(df);
//			endDate = semesterBegin.plusYears(1).atStartOfDay().format(df);
//		}else if(now.isAfter(semesterBegin)&&now.isBefore(semesterEnd)){
//			//学期中
//			startDate = semesterBegin.atStartOfDay().format(df);
//			endDate = now.atStartOfDay().format(df);
//		}
		String s = LocalDateTime.now().format(df);
		System.out.println(s);
//		LocalDateTime now = LocalDateTime.now().minusDays(1);
//		String now1 = LocalDate.now().toString();
//		String localDate = LocalDate.now().minusDays(1).toString();
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		String localDateTime = LocalDate.parse(now1).atStartOfDay().format(df);
	}

	@Test
	public void test(){
		//获取本周一
//		String thisMonday = LocalDate.now().with(DayOfWeek.MONDAY).toString();
		//获取上周一
//		String lastMonday = LocalDate.now().minusWeeks(1).with(DayOfWeek.MONDAY).toString();
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(2);
		String praisesPercentage = numberFormat.format((double) 0 / (0+0));
//		int a = (284 / 290);


		System.out.println(praisesPercentage);
//		String json = "{\"status\":\"success\"}";
//		String status = JSON.parseObject(json).getString("Data");

//		DingDeptDTO a = new DingDeptDTO();
//		a.setName("1");
//		DingDeptDTO b= new DingDeptDTO();
//		b.setName("2");
//		List<DingDeptDTO> c = new ArrayList<>();
////		c.add(a);
////		c.add(b);
//
//		List<String> collect = c.stream().map(DingDeptDTO::getName).collect(Collectors.toList());
//		System.out.println(collect);
//		List<Integer> list = new ArrayList<>();
//		list.add(4);
//		list.add(2);
//		list.add(3);
//		list.add(3);
//		list.add(4);
//		list = new ArrayList<>(new HashSet<>(list));
//		System.out.println(list);
//
//		System.out.println(list.toString());
//		List<String> aaaa = Arrays.asList(list.toString());
//
//		String className = "00001601201902cz";
//		Map<String,Integer> map = new HashMap<>();
//		map.put("A", 1);
//		map.remove("A");
//		System.out.println(map.get("A").toString());
//		System.out.println("7"+5);
//
//		String  b = "[106841999]";
//		List<Long> edUserDingtalkListOld = Arrays.stream(b
//				.replace("[", "").replace("]", "").split(","))
//				.map(s -> Long.parseLong(s.trim()))
//				.collect(Collectors.toList());
//
//		List<String> depts = Arrays.asList(b.replace("[", "").replace("]", "").replace(" ", "").split(","));
//		String grade = className.substring(8, 12);
//		List<String> classList = new ArrayList<>();
//		if(classList.isEmpty()){
//			String s = classList.get(0);
//		}else if(classList == null){
//			String s = classList.get(0);
//		}
	}

	@Test
	public void getToken() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
		OapiGettokenRequest req = new OapiGettokenRequest();
		req.setAppkey("dingp9g0ddkmkiv2zhpu");
		req.setAppsecret("KNLfyXok7Mc-fQtai1spgy9bjuIGiJHGYBQl5JuBlNkeajfcX6cEv65puUVu7cPm");
		req.setHttpMethod("GET");
		OapiGettokenResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}

	@Test
	public  void g() throws ApiException {
		DefaultDingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
		OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
		req.setTmpAuthCode("8722355917da3a65b09df66ce24fbe6f");
		OapiSnsGetuserinfoBycodeResponse response = client.execute(req,"dingoardkw2ca748ww21ib","YVEF_2BaUkf5c9wLfpMyg72GPXC9WyrlI8rEAVycCuBB5qL4RGKGuzhH07GOp4eG");
		System.out.println(response);


	}

	@Test
	public void getCallBack() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/get_call_back");
		OapiCallBackGetCallBackRequest request = new OapiCallBackGetCallBackRequest();
		request.setHttpMethod("GET");
		OapiCallBackGetCallBackResponse response = client.execute(request, AccessTokenUtil.getToken());
		System.out.println(response.getErrmsg());
		System.out.println(response.getCallBackTag());
	}

	@Test
	public void  getCallBackFailed() throws ApiException {
		DingTalkClient  client = new DefaultDingTalkClient("https://oapi.dingtalk.com/call_back/get_call_back_failed_result");
		OapiCallBackGetCallBackFailedResultRequest request = new OapiCallBackGetCallBackFailedResultRequest();
		request.setHttpMethod("GET");
		OapiCallBackGetCallBackFailedResultResponse response = client.execute(request,AccessTokenUtil.getToken());
		System.out.println(response.getErrmsg());
		System.out.println(response.getFailedList());
	}

	@Test
	public void  deleteCallBack() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(Constant.DELETE_CALLBACK);
		OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
		request.setHttpMethod("GET");
		OapiCallBackDeleteCallBackResponse deleteResponse = client.execute(request, AccessTokenUtil.getToken());
		System.out.println(deleteResponse.getErrmsg());
	}

	@Test
	public void getUserInfo() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
		OapiUserGetRequest request = new OapiUserGetRequest();
		//学生 1592443282027777147 1592449129546775028
		//家长 1592449515511-1870615889
		//教师 662445530124049154(自己，校长)
		//教师 020627336933348564(班主任)
		//教师 015218636136616774 非家校通讯录
		//孙超 3143175652761164
		request.setUserid("156253546326238767");
		request.setHttpMethod("GET");
		OapiUserGetResponse response = client.execute(request, AccessTokenUtil.getToken());
		String s = JSON.toJSONString(response.getDepartment());
		System.out.println(s);
		String s1 = JSON.toJSONString(response.getErrmsg());
		System.out.println(s1);

		JSONObject body = JSON.parseObject(response.getBody());
		//通过body中的tags内容有无来判断
		List<String> tag = new ArrayList<>();
		Map<String,List<String>> tags = (Map<String,List<String>>)JSON.parse(body.getString("tags"));
		if(!tags.isEmpty()) {
			if(tags.get("student")!=null){
				tag = tags.get("student");
			}else if(tags.get("guardian")!=null){
				tag = tags.get("guardian");
			}else if(tags.get("headMaster")!=null){
				tag = tags.get("headMaster");
			}
		}

	}

	@Test
	public void getDeptInfo() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
		OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
		request.setId("1");
		request.setHttpMethod("GET");
		OapiDepartmentGetResponse response = client.execute(request, AccessTokenUtil.getToken());
		String s = JSON.toJSONString(response.getName());
		System.out.println(s);
		String s1 = JSON.toJSONString(response.getErrmsg());
		System.out.println(s1);

	}
	@Test
	public void getDeptList() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
		OapiDepartmentListRequest request = new OapiDepartmentListRequest();
		request.setId("1");
		request.setHttpMethod("GET");
		String token = AccessTokenUtil.getToken();
		OapiDepartmentListResponse response = client.execute(request,token);
		String s = JSON.toJSONString(response.getDepartment());
		System.out.println(s);
		String s1 = JSON.toJSONString(response.getErrmsg());
		System.out.println(s1);
	}

	@Test
	public void fastjson(){
		String a = "{\"auth_user_info\":{\"userId\":\"662445530124049154\"},\"auth_corp_info\":{\"corp_type\":0,\"corpid\":\"ding80b2a462acf58b974ac5d6980864d335\",\"auth_level\":0,\"corp_city\":\"鹿城区\",\"auth_channel\":\"\",\"industry\":\"初中等教育\",\"full_corp_name\":\"中津欢乐小学\",\"corp_name\":\"中津欢乐小学\",\"corp_province\":\"温州\",\"invite_url\":\"https://wx.dingtalk.com/invite-page/index.html?bizSource=____source____&corpId=ding80b2a462acf58b974ac5d6980864d335&inviterUid=3CCEFD19F729A4377EC65F5B1B7BA9AC\",\"auth_channel_type\":\"\",\"invite_code\":\"\",\"is_authenticated\":false,\"license_code\":\"\",\"corp_logo_url\":\"\"},\"syncAction\":\"org_suite_auth\",\"auth_scope\":{\"errcode\":0,\"condition_field\":[],\"auth_user_field\":[\"jobnumber\",\"isLeader\",\"name\",\"position\",\"isAdmin\",\"avatar\",\"department\",\"userid\",\"deviceId\",\"isHide\"],\"auth_org_scopes\":{\"authed_user\":[],\"authed_dept\":[1]},\"errmsg\":\"ok\"},\"auth_info\":{\"agent\":[{\"agentid\":868584041,\"agent_name\":\"知达点评\",\"logo_url\":\"https://static-legacy.dingtalk.com/media/lADPDf0ivvxEoNDMyMzI_200_200.jpg\",\"appid\":52814,\"admin_list\":[\"015218636136616774\",\"020627336933348564\",\"662445530124049154\",\"024026470528642328\",\"3143175652761164\"]}]},\"permanent_code\":\"P42-5AQZHzvlnZBfDPKx4MauB4_Qz2vtYo0Jqbdzi-Zbjri9GMXWOS2Z9KsOqdAe\",\"syncSeq\":\"874DD3DFE04AA92FFD84968E24\"}";
		JSONObject jsonObject = JSON.parseObject(a);
		Integer agentId = (Integer) jsonObject.getJSONObject("auth_info").getJSONArray("agent").getJSONObject(0).get("agentid");
		Long aLong = Long.valueOf(agentId);
		System.out.println(aLong);
//		JSONObject obj = JSON.parseObject(jsonObject);
//		JSONArray user = obj.getJSONArray("UserId");
//		List<String> userIdList = JSONObject.parseArray(obj.getJSONArray("UserId").toJSONString(),String.class);
	}

	@Test
	public void getAuth() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/auth/scopes");
		OapiAuthScopesRequest request = new OapiAuthScopesRequest();
		request.setHttpMethod("GET");
		OapiAuthScopesResponse response = client.execute(request, AccessTokenUtil.getToken());
		String s = JSON.toJSONString(response.getAuthOrgScopes().getAuthedDept());
		System.out.println(s);
		String s2 = JSON.toJSONString(response.getAuthOrgScopes().getAuthedUser());
		System.out.println(s2);
	}

	@Test
	public void one(){
		List<Long> list = new ArrayList<>();
		list.add(1L);
		list.add(2L);
		System.out.println(list.toString());
	}

	@Test
	public void getStudentInfo() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/student/get");
		OapiEduStudentGetRequest req = new OapiEduStudentGetRequest();
		req.setClassId(360752040L);
		req.setStudentUserid("1592443282027777147");
		OapiEduStudentGetResponse rsp = client.execute(req, "2b3e524b4d5f358991a183a6774a5111");
//		rsp.getResult()
		System.out.println(rsp.getBody());
	}

	@Test
	public void getClassIdList() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/roles/get");
		OapiEduRolesGetRequest req = new OapiEduRolesGetRequest();
		req.setUserid("1554708863159");
		OapiEduRolesGetResponse rsp = client.execute(req,AccessTokenUtil.getToken());
		System.out.println(rsp.getBody());

	}

	@Test
	public void getDeptUserList() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/listbypage");
		OapiUserListbypageRequest request = new OapiUserListbypageRequest();
		request.setDepartmentId(359583674L);
		request.setOffset(0L);
		request.setSize(20L);
		request.setOrder("entry_desc");
		request.setHttpMethod("GET");
		OapiUserListbypageResponse rsp = client.execute(request,"74b064e0015231cf9a98d01a7a1c3a1e");
		System.out.println(rsp.getBody());
	}


//105070162","105266130"
	@Test
	public void getParentinfo() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/guardian/get");
		OapiEduGuardianGetRequest req = new OapiEduGuardianGetRequest();
		req.setClassId(126730283L);
		req.setGuardianUserid("156253546326238767");
		OapiEduGuardianGetResponse rsp = client.execute(req, AccessTokenUtil.getToken());
		System.out.println(rsp.getBody());
	}

	@Test
	public void getParentUserInfo() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/roles/get");
		OapiEduRolesGetRequest req = new OapiEduRolesGetRequest();
		req.setUserid("156253546326238767");
		OapiEduRolesGetResponse rsp = client.execute(req, AccessTokenUtil.getToken());
		System.out.println(rsp.getBody());
	}

	@Test
	public void getEduUserRelationList() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/user/relation/list");
		OapiEduUserRelationListRequest req = new OapiEduUserRelationListRequest();
		req.setClassId(403695648L);
		req.setPageNo(0L);
		req.setPageSize(30L);
		OapiEduUserRelationListResponse rsp = client.execute(req, "d1a910001a213fcc94e40694482f984f");
		System.out.println(rsp.getBody());
	}

	@Test
	public void getClassInfo() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/edu/class/get");
		OapiEduClassGetRequest req = new OapiEduClassGetRequest();
		req.setClassId(130793206L);
		OapiEduClassGetResponse rsp = client.execute(req, AccessTokenUtil.getToken());
		System.out.println(rsp.getBody());

	}

	@Test
	public void excelread(){
		ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:\\Users\\yibo\\Desktop\\食谱.xlsx"));
//		List<Map<String, Object>> read = reader.read(1, 2, Integer.MAX_VALUE);
		List<List<Object>> read = reader.read(2);

		for(int i =0;i<read.size();i=i+2){
			List<Object> list = read.get(i);
			System.out.println("ok");
		}

		System.out.println("ok");
	}

	@Test
	public void json(){
		String s = "{\"is_adviser\":0}";
		Object is_adviser = JSONObject.parseObject(s).get("is_adviser");
		System.out.println(is_adviser);
	}

	@Test
	public void auth() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/auth/scopes");
		OapiAuthScopesRequest request = new OapiAuthScopesRequest();
		request.setHttpMethod("GET");
		OapiAuthScopesResponse response = client.execute(request, AccessTokenUtil.getToken());
		System.out.println(response.getAuthOrgScopes().getAuthedDept());
	}

	@Test
	public void corp() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/service/get_auth_info");
		OapiServiceGetAuthInfoRequest req = new OapiServiceGetAuthInfoRequest();
		req.setAuthCorpid("ding80b2a462acf58b974ac5d6980864d335");
		OapiServiceGetAuthInfoResponse response = client.execute(req,"suite99rslfjbhz7j49cx","OsiMhV0GvyE1jO0fy8VfPRNnk9SAqdxdl19ABVCg-i0VxPu4oP6d_hLiIH6PjQN5", "PZMoLAER6BPqjIfNuDx7V8WUEZmrklEshWWPFNwWFpjBsGzjtEzNMl79TF0lvd1Y4xCDWK46r3PxdiNgzvngNc");
		System.out.println(response);
	}

	@Test
	public void getUserIdByUnionId() throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getUseridByUnionid");
		OapiUserGetUseridByUnionidRequest request = new OapiUserGetUseridByUnionidRequest();
		request.setUnionid("wy6Kvzxfakj2gNCJAveFygiEiE");
		request.setHttpMethod("GET");
		OapiUserGetUseridByUnionidResponse response = client.execute(request, "707be061805c3afab8982d8295bb1fcb");
		System.out.println(response);
	}

	@Test
	public void datatiem(){
		 String month = LocalDate.now().format(DateTimeFormatter.ofPattern("M"));
		 String day = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));
		 String formatDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
		System.out.println("A");
	}

//	@Test
//	public void excelWriter(){
//		// 通过工具类创建writer，默认创建xls格式
//		ExcelWriter writer = ExcelUtil.getWriter();
//		//创建xlsx格式的
//		//ExcelWriter writer = ExcelUtil.getWriter(true);
//		// 一次性写出内容，使用默认样式，强制输出标题
//		writer.write(rows, true);
//		//out为OutputStream，需要写出到的目标流
//		writer.flush(out);
//		// 关闭writer，释放内存
//		writer.close();
//	}
}
