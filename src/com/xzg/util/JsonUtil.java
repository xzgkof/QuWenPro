package com.xzg.util;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.xzg.config.Config;
import com.xzg.entity.Order;
import com.xzg.entity.OrderDetial;
import com.xzg.http.HttpClientUtil;
import com.xzg.swing.Main;

public class JsonUtil {

	
	
	
	public static List<Order> getOrderList(String jsonStr) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);

		JSONArray array = (JSONArray) jsonObject.get("dataan");

		return JSONArray.toList(array, new Order(), new JsonConfig());
	}

	public static OrderDetial getOrderOrderDetial(String jsonStr) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		JSONObject odbean = (JSONObject) jsonObject.get("dataan");
		OrderDetial od = (OrderDetial) JSONObject.toBean(odbean,
				OrderDetial.class);
		return od;
	}

	public static String handler(List<Order> list, String token, String userId,
			String hshow) {
		String message = "{\"success\":false,\"message\":\"抱歉，该订单已被抢\"}";
		for (Iterator<Order> it = list.iterator(); it.hasNext();) {
			Order order = (Order) it.next();
			if (Double.valueOf(order.getNumber()).doubleValue() <= Main.jinE) {
				String param2 = Config.AddmcpeanutheheForm
						.replace("codeTem", "1234")
						.replace("mkid", order.getId())
						.replace("uidTem", userId).replace("tokenTem", token)
						.replace("hshow", hshow);
				try {
					message = HttpClientUtil.post(
							Config.Addmcpeanuthehe, TT.setConfig(param2));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return message;
	}
}
