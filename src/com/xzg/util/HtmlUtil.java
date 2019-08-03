package com.xzg.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 抓取网页工具类
 * @author xiezg
 *
 */
public class HtmlUtil {
		  public static void main(String[] args) throws IOException {

			  //目标页面  
		        String url = "http://www.89ip.cn/index_1.html";  
		        //使用Jsoup连接目标页面,并执行请求,获取服务器响应内容  
		        Document doc = Jsoup.connect(url).get();  
		        //tr
		        System.out.println(doc);
		}
		  
		  
		public static Vector<Vector<Object>> get89Ip(int index){
			   
			   Vector<Vector<Object>> vData = new Vector<Vector<Object>>();
  
			   try {	   
					   //目标页面  
				        String url = "http://www.89ip.cn/index_"+index+".html";  
				        //使用Jsoup连接目标页面,并执行请求,获取服务器响应内容  
				        Document doc = Jsoup.connect(url).get();  
				        //tr
				        Elements trs =  doc.getElementsByTag("tr");
		
				        for(Iterator<Element> it = trs.iterator();it.hasNext();){
				        	Element tr =  it.next();
				        	Elements tds = tr.getElementsByTag("td");

				        	Vector<Object> row = new Vector<Object>();
				        	
				        	for(int i=0;i<tds.size();i++){
				        		 if(i==3){ continue;}
				        		 row.add(tds.get(i).text().trim());
				        	}
				        	
				        	vData.add(row);    	       	
				        	
				        }		
			   } catch (Exception e) {
					e.printStackTrace();
				}
			
			return vData;
		}  
}
