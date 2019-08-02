package com.xzg.http;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.methods.HttpGet;

import com.xzg.config.Config;
import com.xzg.http.HttpClientUtil.GetRunnable;
import com.xzg.swing.Main;
import com.xzg.util.ClientProxyHttpClientHttp;
import com.xzg.util.HttpUtil;
import com.xzg.util.JsonUtil;
import com.xzg.util.StringUnicodeTest;
import com.xzg.util.TT;

public class OperationFlow {

	   
	      public static void Handler(int conut){
	    	  
	    	  try {  
	    	  
	    	    ExecutorService executors = Executors.newFixedThreadPool(conut);
	            CountDownLatch countDownLatch = new CountDownLatch(conut);
	            
	            for (int i = 0; i < conut; i++) {
	                // 启动线程抓取
	                executors.execute(new GetRunnable(Config.getMcpeanutListhehe, TT.setConfig(Main.param),countDownLatch));
	                
	                Thread.sleep(Main.sleep);
	            } 
					countDownLatch.await();
					executors.shutdown();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            
	      }
	      
	      
	      static class GetRunnable implements Runnable {
	          private CountDownLatch countDownLatch;
	          private String url;
	          private Map<String,String> param;
	   
	          public GetRunnable(String url,Map<String,String> param,CountDownLatch countDownLatch) {
	              this.url = url;
	              this.countDownLatch = countDownLatch;
	              this.param = param;
	          }
	   
	          @Override
	          public void run() {
	        	  String result = null;
	              try {
	            	  
	            	  //httpclient线程池方式
	                  //String result = HttpClientUtil.post(url,param);   
	            	  //httpclient代理方式
	            	   if(Main.bool){
	            		    result = ClientProxyHttpClientHttp.doPostRequest(url, TT.setConfig(Main.param));
	            	   }else{
	            		   //原生方式 no proxy
	            		    result = HttpUtil.doPost(url, Main.param, Main.bool, Main.addr, Main.prot);
	            	   }
	            	   
	            	  
	            	   System.out.println("result="+result);
	            	   
	                  if(result != null && result.indexOf("[]") == -1 && TT.isjson(result)){
	                	   String msg = JsonUtil.handler(
	   							JsonUtil.getOrderList(result), Main.token,
								Main.userId, Main.hshow);
	                	   
	                	     System.out.println(msg);
	                	   
	                	     if(msg != null && TT.isjson(msg) && msg.indexOf("true") != -1){
	                	    	 
	                	    	Main.area.selectAll();
	             				if (Main.area.getSelectedText() != null) {
	             					Main.area.setCaretPosition(Main.area.getSelectedText()
	             							.length());
	             					Main.area.requestFocus();
	             				}
	             				
	                	    	 Main.area.append(Main.area.getText() + "\n" + "=====抢单成功 ====="+"\n"+msg);
	                	    	 Main.flag = false;
	                	     } 	    
	                  }
	                  
	              }catch(Exception e){
	            	  e.printStackTrace();
	              } finally {
	                  countDownLatch.countDown();
	              }
	          }
	          
	      }
	      
	      
}
