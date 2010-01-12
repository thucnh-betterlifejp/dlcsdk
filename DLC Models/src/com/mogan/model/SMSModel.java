package com.mogan.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mogan.model.netAgent.HTMLNodeFilter;
import com.mogan.model.netAgent.NetAgent;
import com.mogan.sys.ProtoModel;
import com.mogan.sys.ServiceModelFace;

public class SMSModel extends ProtoModel implements ServiceModelFace {
	private final static String LOG_SERVER_URL = "LOG_SERVER_URL";
	private final static String SEND_SERVER_URL = "SEND_SERVER_URL";
	private final static String SEND_TEXT = "SEND_TEXT";
	private final static String QUERY_LOG = "QUERY_LOG";
	private final static String ACCOUNT = "ACCOUNT";
	private final static String PWD = "PWD";

	@Override
	public JSONArray doAction(Map parameterMap) throws Exception {
		// TODO Auto-generated method stub
		JSONArray jArray = new JSONArray();
		System.out.println("[INFO]SMSModel ACTION start. " + this.getAct());
		if (this.getAct().equals("SEND_TEXT")) {
			JSONArray numberJarray = JSONArray.fromObject(parameterMap
					.get("NUMBER_JARRAY"));
			String msg = (String) parameterMap.get("MSG");
			jArray = sendText(numberJarray, msg);
		} else if (this.getAct().equals("QUERY_LOG")) {
			String dateStr = (String) parameterMap.get("DATE_STR");
			jArray = queryLog(dateStr);
		}
		return jArray;
	}

	/**
	 * <p>
	 * <font size=7 color=red>發送簡訊，ACTION = SEND_TEXT</font>
	 * </p>
	 * numberJarray必須為JSON的格式<BR /> 範例 :
	 * [{"NUMBER":"0988123654","NAME":"dian","CHECK_KEY":"0001"},{"NUMBER":"0999147852","NAME":"mary","CHECK_KEY":"0002"}]<BR /> 三項必須屬性<li>NUMBER-號碼</li>
	 * <li>NAME-接收者名稱</li><li>CHECK_KEY-交易代號</li> <BR /><BR /> 收訊電話號碼 - 國際簡訊規格 : (+或是00)(國碼 )(去0之手機號碼).<BR /> 例 : +8613913560366 or 008613913560366<BR
	 * /> 國際簡訊自動以國際簡訊計費<BR /> 台灣與大陸(不含香港、澳門)之手機門號無論是否帶入國碼都可為有效號碼<BR /> <BR /> 接收者名稱<BR /> (最好有，以便客戶查詢發訊結果時知道收訊人的姓名)<BR /> 如果無，SMSe自動以收訊號碼當作收訊人姓名<BR />
	 * <BR />
	 * 
	 * @param numberJarray
	 *            - 傳送的對向
	 * @param msg
	 *            - 要傳送的訊息
	 * @return
	 */
	public JSONArray sendText(JSONArray numberJarray, String msg) {
		try {
			msg=URLEncoder.encode(msg, "big5");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONArray jArray = new JSONArray();
		NetAgent nAgent = new NetAgent();
		/*
		 * yhy-貴公司的帳號 dc2a-貴公司的密碼 od_sob-資料庫的keyword(20字內)請自行利用 movetel-收訊電話號碼 數字 +號 ;號 name-收訊人姓名 bf-小於SMSe系統時間或無時會立即發送 sb-69個全形字一則，
		 * zchttp-執行完成後要返回的HTTP位置 http_sob-
		 */
		StringBuffer numbers = new StringBuffer();
		StringBuffer names = new StringBuffer();
		StringBuffer checkKey = new StringBuffer();
		for (int i = 0; i < numberJarray.size(); i++) {
			JSONObject jObj = numberJarray.getJSONObject(i);
			if (numbers.length() > 0) {
				numbers.append(";");
			}
			if (jObj.has("NUMBER")) {
				numbers.append(jObj.getString("NUMBER"));
			}

			if (names.length() > 0) {
				names.append(";");
			}
			if (jObj.has("NAME")) {
				names.append(jObj.getString("NAME"));
			}

			if (checkKey.length() > 0) {
				checkKey.append(";");
			}
			if (jObj.has("CHECK_KEY")) {
				checkKey.append(jObj.getString("CHECK_KEY"));
			}
		}

		String url = this.getProperty(SEND_SERVER_URL)
				+ this.getProperty(SEND_TEXT) + "?yhy="
				+ this.getProperty(ACCOUNT) + "&dc2a=" + this.getProperty(PWD)
				+ "&od_sob=" + checkKey + "&movetel=" + numbers + "&name="
				+ names + "&sb=" + msg ;
		
		System.out.println(url);
		nAgent.getDataWithGet(url);
		System.out.println(nAgent.getResponseBody());
		jArray.add("1");
		return jArray;
	}

	/**
	 * <p>
	 * <font size=7 color=red>發送簡訊，ACTION = QUERY_LOG</font>
	 * </p>
	 * <li>DELIVRD…….成功</li> <li>UNDELIV…….空號</li> <li>TELERR………號碼錯誤</li> <li>EXPIRED……..失敗</li> <li>ERR……………無法送達</li>
	 * [{"DATE":"2009/12/24 下午 04:09:25","NUMBER":"0910054930","STATUS":"DELIVRD","CHECK_KEY":"0001"},{"DATE":"2009/12/24 下午 04:09:27","NUMBER":
	 * "0999054930"
	 * ,"STATUS":"號碼錯誤","CHECK_KEY":"0002"},{"DATE":"2009/12/24 下午 04:12:41","NUMBER":"0910054930","STATUS":"DELIVRD","CHECK_KEY":"0001"},{
	 * "DATE":"2009/12/24 下午 04:12:41"
	 * ,"NUMBER":"0999054930","STATUS":"號碼錯誤","CHECK_KEY":"0002"},{"DATE":"2009/12/24 下午 04:13:38","NUMBER":"0910054930"
	 * ,"STATUS":"DELIVRD","CHECK_KEY"
	 * :"0001"},{"DATE":"2009/12/24 下午 04:13:38","NUMBER":"0999054930","STATUS":"號碼錯誤","CHECK_KEY":"0002"},{"DATE":"2009/12/24 下午 04:16:02"
	 * ,"NUMBER":"0910054930"
	 * ,"STATUS":"DELIVRD","CHECK_KEY":"0001"},{"DATE":"2009/12/24 下午 04:16:02","NUMBER":"0910054930","STATUS":"DELIVRD","CHECK_KEY"
	 * :"0002"},{"DATE":"2009/12/24 下午 04:52:45"
	 * ,"NUMBER":"0910054930","STATUS":"DELIVRD","CHECK_KEY":"0002"},{"DATE":"2009/12/24 下午 04:52:45","NUMBER"
	 * :"0910054930","STATUS":"DELIVRD","CHECK_KEY":"0002"}] dateStr日期條件格式為2009/12/25
	 * 
	 * @param dateStr
	 *            -日期條件
	 * @return 回傳內容為JSON格式，陣列形態，每個項目由 <li>DATE-發送時間</li> <li>NUMBER-發送號碼</li> <li>STATUS-送送狀態</li> <li>CHECK_KEY-檢查碼</li>
	 */
	public JSONArray queryLog(String dateStr) {
		JSONArray jArray = new JSONArray();
		NetAgent nAgent = new NetAgent();
		String url = this.getProperty(LOG_SERVER_URL)
				+ this.getProperty(QUERY_LOG) + "?yhy="
				+ this.getProperty(ACCOUNT) + "&dc2a=" + this.getProperty(PWD)
				+ "&bf=" + dateStr + "&sms_sb=1";
		System.out.println(url);
		nAgent.getDataWithGet(url);

		JSONObject jObj = new JSONObject();
		try {
			nAgent.setResponseBody(new String(nAgent.getResponseBody()
					.getBytes("ISO-8859-1"), "BIG5"));
			NodeList nodes = nAgent.filterItem(new HTMLNodeFilter("p"));

			for (int i = 0; i < nodes.size(); i = i + 2) {
				jObj = new JSONObject();
				System.out.println(i + ":"
						+ nodes.elementAt(i).toPlainTextString());
				System.out
						.println(i
								+ ":"
								+ nodes.elementAt(i).toPlainTextString().split(
										"\\*").length);
				jObj.put("DATE", nodes.elementAt(i).toPlainTextString().split(
						"\\*")[0]);
				jObj.put("NUMBER", nodes.elementAt(i).toPlainTextString()
						.split("\\*")[1]);
				jObj.put("STATUS", nodes.elementAt(i).toPlainTextString()
						.split("\\*")[2]);
				jObj.put("CHECK_KEY", nodes.elementAt(i).toPlainTextString()
						.split("\\*")[3]);
				jArray.add(jObj);
			}

			System.out.println(nAgent.getResponseBody());
			System.out.println(jArray);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jArray.add(nAgent.getResponseBody());
		return jArray;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SMSModel sm = new SMSModel();
		Properties p = new Properties();
		p.put(SEND_SERVER_URL, "http://smsmo.smse.com.tw");
		p.put(LOG_SERVER_URL, "http://sms.smse.com.tw");
		p.put(SEND_TEXT, "/STANDARD/sms_fu.asp");
		p.put(QUERY_LOG, "/STANDARD/TVRVRE_FU_B.ASP");
		p.put(ACCOUNT, "MORGAN");
		p.put(PWD, "24266676");
		sm.setProperties(p);

		JSONArray jArray = new JSONArray();
		JSONObject jObj = new JSONObject();
		jObj.put("NUMBER", "0927101500");
		jObj.put("NAME", "dian");
		// jObj.put("CHECK_KEY", "0001");
		jArray.add(jObj);

		jObj = new JSONObject();
		jObj.put("NUMBER", "0956062199");
		// jObj.put("NAME", "dianx");
		jObj.put("CHECK_KEY", "0002");
		jArray.add(jObj);

		
		jObj = new JSONObject();
		jObj.put("NUMBER", "0910054930");
		// jObj.put("NAME", "dianx");
		jObj.put("CHECK_KEY", "0002");
		jArray.add(jObj);
		
		
		jObj = new JSONObject();
		jObj.put("NUMBER", "0927523232");
		// jObj.put("NAME", "dianx");
		jObj.put("CHECK_KEY", "0002");
		jArray.add(jObj);
		try {
			System.out.println(jArray);
			System.out.println(URLEncoder.encode("ページ", "big5"));
			sm.sendText(jArray, URLEncoder.encode("ページ中文測試123abcddddd网页网址", "UTF-8"));
			sm.queryLog("2009/12/24");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// sm.sendText(jArray,"測試簡訊");

	}

}