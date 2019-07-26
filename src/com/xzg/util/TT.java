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

		String xxx = HttpUtil.doPost("http://baidu.com", "1", true,
				"117.191.11.113", 8080);

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
