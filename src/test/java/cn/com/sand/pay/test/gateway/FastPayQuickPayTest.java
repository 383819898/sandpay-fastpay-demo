package cn.com.sand.pay.test.gateway;

import cn.com.sand.pay.online.sdk.http.HttpUtil;
import cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import cn.com.sand.pay.online.sdk.util.DynamicPropertyHelper;
import cn.com.sand.pay.sandpay.scm.demo.FastPayApiUtil;
import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.binding.StringFormatter;
import javafx.beans.binding.StringExpression;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

/**
 * 对账单申请接口DEMO（仅供参考）
 * @author sandpay
 */
public class FastPayQuickPayTest {

	@Before
	public void init() throws Exception {
		ConfigurationManager.loadProperties(new String[] { "sandPayConfig"});
	}

	/**
	 * 对账单申请接口
	 */
	@Test
	public void testAgClearFile() {
		try {
			String data = getSendData();
			//读取配置中公共URL
			String url =  DynamicPropertyHelper.getStringProperty("sandpay.gateWay.url").get();
			//拼接本交易URL
			url += FastPayApiUtil.GATEWAY_QUICKPAY_URL;
			//创建HTTP辅助工具
			HttpUtil httpUtil= new HttpUtil();
			//通过辅助工具发送交易请求，并获取响应报文
			String result = httpUtil.sendGateWayPost(url, data, "");
			System.out.println("result:" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 组报文
     */
    public static String getSendData(){
    	// 数据域
		JSONObject dataJson = new JSONObject();

		// 报文体
		JSONObject bodyJson = new JSONObject();
//		bodyJson.put("clearDate", "20210119"); //交易日期/结算日期
//		bodyJson.put("fileType", "1"); //文件返回类型
//		bodyJson.put("extend", "");

		bodyJson.put("userId","400071");
		bodyJson.put("orderCode", UUID.randomUUID());

		bodyJson.put("orderTime", DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		Integer youNumber = 10;
		bodyJson.put("totalAmount",String.format("%04d", youNumber));
		bodyJson.put("subject","123");
		bodyJson.put("body","123");
//		bodyJson.put("activityNo","营销活动编号");
//		bodyJson.put("benefitAmount","营销使用金额");
		bodyJson.put("currencyCode","156");
		bodyJson.put("notifyUrl","/api/callback/sandPayApply");
		bodyJson.put("frontUrl","/api/callback/sandPayApply");

		// 报文头
		dataJson.put("head", FastPayApiUtil.getAgHeadJson("sandPay.fastPay.quickPay.index"));
		dataJson.put("body", bodyJson);

		String returnString = dataJson.toJSONString();
		System.out.println("returnString = " + returnString);
		return returnString;
    }

	@Test
	public void test(){
		int youNumber = 1;

		// 0 代表前面补充0

		// 4 代表长度为4

		// d 代表参数为正数型

		String str = String.format("%012d", youNumber);

		System.out.println(str); // 0001


	}

}
