package com.xzg.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

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

		// 121.40.90.189 8001 》》高
		// 117.191.11.113 8080   可用 》》高
		// 120.79.187.118 3128   浙江省杭州市	阿里云BGP数据中心
		// 116.114.19.211 443   内蒙古呼和浩特市	帝联信息科技联通CDN节点 》》高
		// 182.61.160.79 80      北京市	驰骏网络科技有限公司
		// 116.114.19.204 443   内蒙古呼和浩特市	帝联信息科技联通CDN节点  》》高可用
		// 123.207.68.166	1080	广东省广州市	腾讯云广州数据中心 
		// 182.61.109.24 8888
		// 119.28.203.242	8000	广东省广州市海珠区	深圳市腾讯计算机系统有限公司IDC机房(BGP)
		// 60.205.159.195	3128	北京市	阿里云BGP服务器
		// 106.14.82.38 8080  上海市	阿里云BGP数据中心
		// 115.159.31.195	8080	上海市	深圳市腾讯计算机系统有限公司BGP数据中心(BGP)

		 
		String xxx = HttpUtil.doPost("http://baidu.com", "1", true,
				"115.159.31.195", 8080);  

		System.out.println(xxx);
		
		 String content = "192.168.1.234";
			 
		String pattern = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
			      
		boolean isMatch = Pattern.matches(pattern, content);
		System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
		
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
	
	public static boolean pattern(String host,String prot){
		
		String pattern1 = "^((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}$";
	    String pattern2 = "^\\d+$";
		
		boolean ishost = Pattern.matches(pattern1, host);
		boolean isprot = Pattern.matches(pattern2, prot);
		
		if(ishost && isprot){
			return true;
		}else{
			return false;
		}

	}
}
