package cn.com.sand.pay.test.gateway;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import cn.com.sand.pay.online.sdk.http.HttpUtil;
import cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import cn.com.sand.pay.online.sdk.util.DynamicPropertyHelper;
import cn.com.sand.pay.sandpay.scm.demo.FastPayApiUtil;

/**
 * 订单查询接口DEMO（仅供参考）
 * @author sandpay
 */
public class FastPayQueryOrderTest {
	
	public static final Logger logger = LoggerFactory.getLogger(FastPayQueryOrderTest.class);
	
	@Before
	public void init() throws Exception {
		ConfigurationManager.loadProperties(new String[] { "sandPayConfig"});
	}
	
	/**
	 * 单笔订单查询
	 */
	@Test
	public void testAgCreateOrder() {
		try {
			String data = getSendData();
			//读取配置中公共URL
			String url =  DynamicPropertyHelper.getStringProperty("sandpay.gateWay.url").get();
			//拼接本交易URL
			url += FastPayApiUtil.GATEWAY_QUERYORDER_URL;
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
		bodyJson.put("orderCode", "D20210122102004"); //商户订单号
		bodyJson.put("extend", ""); //扩展域

		// 报文头
		dataJson.put("head", FastPayApiUtil.getAgHeadJson("sandpay.trade.query"));
		dataJson.put("body", bodyJson);
		
		String returnString = dataJson.toJSONString();
		return returnString;
    }
    
}