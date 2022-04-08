package cn.com.sand.pay.test.gateway;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.sand.pay.online.sdk.http.HttpUtil;
import cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import cn.com.sand.pay.online.sdk.util.DynamicPropertyHelper;
import cn.com.sand.pay.sandpay.scm.demo.FastPayApiUtil;

/**
 * 对账单申请接口DEMO（仅供参考）
 * @author sandpay
 */
public class FastPayClearFileTest {
	
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
			url += FastPayApiUtil.GATEWAY_CLEARFILE_URL;
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
		bodyJson.put("clearDate", "20210119"); //交易日期/结算日期
		bodyJson.put("fileType", "1"); //文件返回类型
		bodyJson.put("extend", "");
		
		// 报文头
		dataJson.put("head", FastPayApiUtil.getAgHeadJson("sandpay.trade.download"));
		dataJson.put("body", bodyJson);
		
		String returnString = dataJson.toJSONString();
		return returnString;
    }
    
}