package cn.com.sand.pay.test.gateway;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.com.sand.pay.online.sdk.http.HttpUtil;
import cn.com.sand.pay.online.sdk.util.ConfigurationManager;
import cn.com.sand.pay.online.sdk.util.DynamicPropertyHelper;
import cn.com.sand.pay.sandpay.scm.demo.FastPayApiUtil;

/**
 * 退款申请接口DEMO（仅供参考）
 *
 * @author sandpay
 */
public class FastPayRefundPayTest {

    @Before
    public void init() throws Exception {
        //装载配置
        ConfigurationManager.loadProperties(new String[]{"sandPayConfig"});
    }

    /**
     * 退款申请接口
     */
    @Test
    public void testAgOrderQuery() {
        try {
            String data = getSendData();
            //读取配置中公共URL
            String url = DynamicPropertyHelper.getStringProperty("sandpay.gateWay.url").get();
            //拼接本交易URL
            url += FastPayApiUtil.GATEWAY_REFUND_URL;
            //创建HTTP辅助工具
            HttpUtil httpUtil = new HttpUtil();
            //通过辅助工具发送交易请求，并获取响应报文
            String result = httpUtil.sendGateWayPost(url, data, null);
            System.out.println("result:" + result);
            // 验签签名

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 组报文
     */
    public static String getSendData() {
        // 数据域
        JSONObject dataJson = new JSONObject();

        // 报文体
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("orderCode", FastPayApiUtil.getOrderCode());            // 退款请求商户订单号
        bodyJson.put("oriOrderCode", "D20210122102004");        // 原商户订单号
        bodyJson.put("refundAmount", "000000000012");            // 退款金额（暂时同原订单金额）
        String backnoticeUrl = DynamicPropertyHelper.getStringProperty("sandpay.gateWay.backnotice.url").get();
        bodyJson.put("notifyUrl", backnoticeUrl);        // 异步通知地址
        bodyJson.put("refundReason", "");//退款原因

        // 报文头
        dataJson.put("head", FastPayApiUtil.getAgHeadJson("sandpay.trade.refund"));
        dataJson.put("body", bodyJson);

        String returnString = dataJson.toJSONString();
        return returnString;
    }

}
