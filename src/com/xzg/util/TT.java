package com.xzg.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import com.xzg.entity.Message;

public class TT {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "抱歉，该订单被抢！";

		String tem = new String(str.getBytes(), "UTF-8");

		System.out.println(tem);

		String msg = "{\"success\":false,\"message\":\"抱歉，该订单被抢！\"}";
		JSONObject jsonObject = JSONObject.fromObject(msg);

		Message m = (Message) JSONObject.toBean(jsonObject, Message.class);

		System.out.println(!Boolean.valueOf(m.getSuccess()).booleanValue());

		String pram = "responsible_id=1&store_id=0&token=8a7b2c4a87ac5b0ee4b3089bdabad49b26ee5a79&hehe_show=135****43203o5736&user_id=420877";

		// 121.40.90.189 8001
		// 117.191.11.113 8080   可用
		// 120.79.184.62 8080  阿里云BGP数据中心
		// 182.92.219.211 80  阿里巴巴
		// 117.127.16.207 80        江西省九江市	广电网
		// 120.79.187.118 3128   浙江省杭州市	阿里云BGP数据中心
		// 203.130.46.108 9090   北京市	 网宿科技
		// 47.106.192.167 8000  浙江省杭州市	阿里云
		// 116.114.19.211 443   内蒙古呼和浩特市	帝联信息科技联通CDN节点
		// 182.61.160.79 80      北京市	驰骏网络科技有限公司
		// 116.114.19.204 443   内蒙古呼和浩特市	帝联信息科技联通CDN节点
		
		 
		String xxx = HttpUtil.doPost("http://baidu.com", "1", true,
				"116.114.19.204", 443);  

		System.out.println(xxx);
	}

	public static Properties updateProp(String pram) {
		Map<String, String> map = new HashMap();

		String[] array = pram.trim().split("&");
		String[] arrayOfString1;
		int j = (arrayOfString1 = array).length;
		for (int i = 0; i < j; i++) {
			String str = arrayOfString1[i];
			String[] tem = str.split("=");
			map.put(tem[0], tem[1]);
		}
		Properties prop = new Properties();
		try {
			FileInputStream in = new FileInputStream("src/user.properties");
			prop.load(in);

			System.out.println(prop.get("userid"));

			Object out = new FileOutputStream("src/user.properties");
			prop.setProperty("userid", (String) map.get("user_id"));
			prop.setProperty("token", (String) map.get("token"));
			prop.setProperty("hshow", (String) map.get("hehe_show"));
			prop.store((OutputStream) out, "Update file");
			in.close();
			((OutputStream) out).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> setConfig(String param) {
		Map<String, String> map = new HashMap();

		String[] array = param.trim().split("&");
		String[] arrayOfString1;
		int j = (arrayOfString1 = array).length;
		for (int i = 0; i < j; i++) {
			String str = arrayOfString1[i];
			String[] tem = str.split("=");
			map.put(tem[0], tem[1]);
		}
		return map;
	}

	public static boolean isjson(String string) {
		try {
			JSONObject jsonStr = JSONObject.fromObject(string);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}
