package com.xzg.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

public class HttpUtil
{
  public static String errMsg = null;
  
  /* Error */
  public static String doGet(String urlStr, boolean bool, String addr, int prot)
  {
	return null;
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 7
    //   12: iload_1
    //   13: ifeq +49 -> 62
    //   16: new 21	java/net/Proxy
    //   19: dup
    //   20: getstatic 23	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   23: new 29	java/net/InetSocketAddress
    //   26: dup
    //   27: aload_2
    //   28: iload_3
    //   29: invokespecial 31	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   32: invokespecial 34	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   35: astore 8
    //   37: new 37	java/net/URL
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 39	java/net/URL:<init>	(Ljava/lang/String;)V
    //   45: astore 9
    //   47: aload 9
    //   49: aload 8
    //   51: invokevirtual 42	java/net/URL:openConnection	(Ljava/net/Proxy;)Ljava/net/URLConnection;
    //   54: checkcast 46	java/net/HttpURLConnection
    //   57: astore 4
    //   59: goto +23 -> 82
    //   62: new 37	java/net/URL
    //   65: dup
    //   66: aload_0
    //   67: invokespecial 39	java/net/URL:<init>	(Ljava/lang/String;)V
    //   70: astore 8
    //   72: aload 8
    //   74: invokevirtual 48	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   77: checkcast 46	java/net/HttpURLConnection
    //   80: astore 4
    //   82: aload 4
    //   84: ldc 51
    //   86: invokevirtual 53	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   89: aload 4
    //   91: sipush 15000
    //   94: invokevirtual 56	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   97: aload 4
    //   99: ldc 60
    //   101: invokevirtual 61	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   104: aload 4
    //   106: invokevirtual 64	java/net/HttpURLConnection:connect	()V
    //   109: aload 4
    //   111: invokevirtual 67	java/net/HttpURLConnection:getResponseCode	()I
    //   114: sipush 200
    //   117: if_icmpne +187 -> 304
    //   120: aload 4
    //   122: invokevirtual 71	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   125: astore 5
    //   127: new 75	java/io/BufferedReader
    //   130: dup
    //   131: new 77	java/io/InputStreamReader
    //   134: dup
    //   135: aload 5
    //   137: ldc 79
    //   139: invokespecial 81	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   142: invokespecial 84	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   145: astore 6
    //   147: new 87	java/lang/StringBuffer
    //   150: dup
    //   151: invokespecial 89	java/lang/StringBuffer:<init>	()V
    //   154: astore 8
    //   156: aconst_null
    //   157: astore 9
    //   159: goto +19 -> 178
    //   162: aload 8
    //   164: aload 9
    //   166: invokevirtual 90	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   169: pop
    //   170: aload 8
    //   172: ldc 94
    //   174: invokevirtual 90	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   177: pop
    //   178: aload 6
    //   180: invokevirtual 96	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   183: dup
    //   184: astore 9
    //   186: ifnonnull -24 -> 162
    //   189: aload 8
    //   191: invokevirtual 100	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   194: astore 7
    //   196: goto +108 -> 304
    //   199: astore 8
    //   201: aload 8
    //   203: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   206: aload 6
    //   208: ifnull +18 -> 226
    //   211: aload 6
    //   213: invokevirtual 108	java/io/BufferedReader:close	()V
    //   216: goto +10 -> 226
    //   219: astore 11
    //   221: aload 11
    //   223: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   226: aload 5
    //   228: ifnull +18 -> 246
    //   231: aload 5
    //   233: invokevirtual 111	java/io/InputStream:close	()V
    //   236: goto +10 -> 246
    //   239: astore 11
    //   241: aload 11
    //   243: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   246: aload 4
    //   248: invokevirtual 114	java/net/HttpURLConnection:disconnect	()V
    //   251: goto +98 -> 349
    //   254: astore 10
    //   256: aload 6
    //   258: ifnull +18 -> 276
    //   261: aload 6
    //   263: invokevirtual 108	java/io/BufferedReader:close	()V
    //   266: goto +10 -> 276
    //   269: astore 11
    //   271: aload 11
    //   273: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   276: aload 5
    //   278: ifnull +18 -> 296
    //   281: aload 5
    //   283: invokevirtual 111	java/io/InputStream:close	()V
    //   286: goto +10 -> 296
    //   289: astore 11
    //   291: aload 11
    //   293: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   296: aload 4
    //   298: invokevirtual 114	java/net/HttpURLConnection:disconnect	()V
    //   301: aload 10
    //   303: athrow
    //   304: aload 6
    //   306: ifnull +18 -> 324
    //   309: aload 6
    //   311: invokevirtual 108	java/io/BufferedReader:close	()V
    //   314: goto +10 -> 324
    //   317: astore 11
    //   319: aload 11
    //   321: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   324: aload 5
    //   326: ifnull +18 -> 344
    //   329: aload 5
    //   331: invokevirtual 111	java/io/InputStream:close	()V
    //   334: goto +10 -> 344
    //   337: astore 11
    //   339: aload 11
    //   341: invokevirtual 103	java/lang/Exception:printStackTrace	()V
    //   344: aload 4
    //   346: invokevirtual 114	java/net/HttpURLConnection:disconnect	()V
    //   349: aload 7
    //   351: areturn
    // Line number table:
    //   Java source line #25	-> byte code offset #0
    //   Java source line #26	-> byte code offset #3
    //   Java source line #27	-> byte code offset #6
    //   Java source line #28	-> byte code offset #9
    //   Java source line #32	-> byte code offset #12
    //   Java source line #33	-> byte code offset #16
    //   Java source line #34	-> byte code offset #37
    //   Java source line #35	-> byte code offset #47
    //   Java source line #36	-> byte code offset #59
    //   Java source line #38	-> byte code offset #62
    //   Java source line #40	-> byte code offset #72
    //   Java source line #46	-> byte code offset #82
    //   Java source line #49	-> byte code offset #89
    //   Java source line #52	-> byte code offset #97
    //   Java source line #55	-> byte code offset #104
    //   Java source line #58	-> byte code offset #109
    //   Java source line #59	-> byte code offset #120
    //   Java source line #61	-> byte code offset #127
    //   Java source line #63	-> byte code offset #147
    //   Java source line #64	-> byte code offset #156
    //   Java source line #65	-> byte code offset #159
    //   Java source line #66	-> byte code offset #162
    //   Java source line #67	-> byte code offset #170
    //   Java source line #65	-> byte code offset #178
    //   Java source line #69	-> byte code offset #189
    //   Java source line #72	-> byte code offset #196
    //   Java source line #74	-> byte code offset #201
    //   Java source line #77	-> byte code offset #206
    //   Java source line #79	-> byte code offset #211
    //   Java source line #80	-> byte code offset #216
    //   Java source line #81	-> byte code offset #221
    //   Java source line #85	-> byte code offset #226
    //   Java source line #87	-> byte code offset #231
    //   Java source line #88	-> byte code offset #236
    //   Java source line #89	-> byte code offset #241
    //   Java source line #93	-> byte code offset #246
    //   Java source line #75	-> byte code offset #254
    //   Java source line #77	-> byte code offset #256
    //   Java source line #79	-> byte code offset #261
    //   Java source line #80	-> byte code offset #266
    //   Java source line #81	-> byte code offset #271
    //   Java source line #85	-> byte code offset #276
    //   Java source line #87	-> byte code offset #281
    //   Java source line #88	-> byte code offset #286
    //   Java source line #89	-> byte code offset #291
    //   Java source line #93	-> byte code offset #296
    //   Java source line #94	-> byte code offset #301
    //   Java source line #77	-> byte code offset #304
    //   Java source line #79	-> byte code offset #309
    //   Java source line #80	-> byte code offset #314
    //   Java source line #81	-> byte code offset #319
    //   Java source line #85	-> byte code offset #324
    //   Java source line #87	-> byte code offset #329
    //   Java source line #88	-> byte code offset #334
    //   Java source line #89	-> byte code offset #339
    //   Java source line #93	-> byte code offset #344
    //   Java source line #96	-> byte code offset #349
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	352	0	urlStr	String
    //   0	352	1	bool	boolean
    //   0	352	2	addr	String
    //   0	352	3	prot	int
    //   1	344	4	connection	HttpURLConnection
    //   4	326	5	is	InputStream
    //   7	303	6	br	BufferedReader
    //   10	340	7	result	String
    //   35	15	8	proxy	Proxy
    //   70	3	8	url	URL
    //   154	36	8	sbf	StringBuffer
    //   199	3	8	e	Exception
    //   45	3	9	obj	URL
    //   157	28	9	temp	String
    //   254	48	10	localObject	Object
    //   219	3	11	e	Exception
    //   239	3	11	e	Exception
    //   269	3	11	e	Exception
    //   289	3	11	e	Exception
    //   317	3	11	e	Exception
    //   337	3	11	e	Exception
    // Exception table:
    //   from	to	target	type
    //   12	196	199	java/lang/Exception
    //   211	216	219	java/lang/Exception
    //   231	236	239	java/lang/Exception
    //   12	206	254	finally
    //   261	266	269	java/lang/Exception
    //   281	286	289	java/lang/Exception
    //   309	314	317	java/lang/Exception
    //   329	334	337	java/lang/Exception
  }
  
  public static String doPost(String httpUrl, String param, boolean bool, String addr, int prot)
  {
    HttpURLConnection connection = null;
    InputStream is = null;
    OutputStream os = null;
    BufferedReader br = null;
    String result = null;
    try
    {
      if (!bool)
      {
        URL url = new URL(httpUrl);
        
        connection = (HttpURLConnection)url.openConnection();
      }
      else
      {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(addr, prot));
        URL obj = new URL(httpUrl);
        connection = (HttpURLConnection)obj.openConnection(proxy);
      }
      connection.setRequestMethod("POST");
      
      connection.setConnectTimeout(15000);
      
      connection.setReadTimeout(30000);
      
      connection.setDoOutput(true);
      
      connection.setDoInput(true);
      
      connection.setUseCaches(false);
      
      os = connection.getOutputStream();
      
      os.write(param.getBytes());
      if (connection.getResponseCode() == 200)
      {
        is = connection.getInputStream();
        
        br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        
        StringBuffer sbf = new StringBuffer();
        String temp = null;
        while ((temp = br.readLine()) != null)
        {
          sbf.append(temp);
          sbf.append("\r\n");
        }
        result = sbf.toString();
      }
    }
    catch (Exception e)
    {
      if (e.getMessage().equals("Read timed out")) {
        errMsg = "Read timed out��������Token";
      } else if (e.getMessage().equals("connect timed out")) {
        errMsg = "Read timed out��������Token";
      } else {
        errMsg = e.getMessage();
      }
      e.printStackTrace();
    }
    return result;
  }
}
