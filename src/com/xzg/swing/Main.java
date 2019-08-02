package com.xzg.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.xzg.config.Config;
import com.xzg.http.OperationFlow;
import com.xzg.util.HttpUtil;
import com.xzg.util.TT;

public class Main {
	public static boolean bool = false;
	public static String addr;
	public static int prot;
	public static boolean flag = true;
	public static long sleep = 1000;
	public static Map<String, String> confMap = new HashMap();
	public static int jinE = 10;
	public static String userId = "";
	public static String token = "";
	public static String hshow = "";
	public static String param = "";
	public static String result = null;
	public static JTextArea area;
	
	public static JTextField proxyfield;
	
	public static JTextField protfield;
	
	public static JButton pxybtn;

	public static void main(String[] args) {
		JFrame frame = new JFrame("趣问抢单工具");
		frame.setSize(600, 400);
		frame.setLayout(new BorderLayout());

		JTextField field = new JTextField();

		field.setPreferredSize(new Dimension(400, 23));

		JButton button1 = new JButton("确定");
		JLabel label = new JLabel("设置参数：");

		JPanel jpanel = new JPanel(new GridLayout(3, 3));
		jpanel.add(label);
		jpanel.add(field);
		jpanel.add(button1);

		JLabel spLabel = new JLabel("设置刷新频率：");
		JTextField field2 = new JTextField(sleep+"");
		JButton spbtn = new JButton("确定");
		jpanel.add(spLabel);
		jpanel.add(field2);
		jpanel.add(spbtn);

		JLabel jeLabel = new JLabel("金额大于等于：");
		JTextField field3 = new JTextField(jinE+"");
		JButton jebtn = new JButton("确定");
		jpanel.add(jeLabel);
		jpanel.add(field3);
		jpanel.add(jebtn);

		jebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.jinE = Integer.valueOf(field3.getText()).intValue();
			}
		});
		spbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.sleep = Long.valueOf(field2.getText()).longValue();
			}
		});
		JLabel proxylab = new JLabel("代理IP");
		proxyfield = new JTextField();
		JLabel protlab = new JLabel("端口：");
	    protfield = new JTextField();
	    pxybtn = new JButton("设置代理");
		JButton qxbtn = new JButton("取消代理");
		JButton pylsbtn = new JButton("代理列表");
		JButton pytestbtn = new JButton("测试代理");
         
		JPanel xxPanel = new JPanel(new GridLayout(2, 1));
		
		
		JPanel ProxyPanel = new JPanel(new GridLayout(1, 4));
		ProxyPanel.add(proxylab);
		ProxyPanel.add(proxyfield);
		ProxyPanel.add(protlab);
		ProxyPanel.add(protfield);
		
		JPanel ProxyPanelbtn = new JPanel(new GridLayout(1, 4));
		ProxyPanelbtn.add(pxybtn);
		ProxyPanelbtn.add(qxbtn);
		ProxyPanelbtn.add(pylsbtn);	
		ProxyPanelbtn.add(pytestbtn);
		
		xxPanel.add(ProxyPanel);
		xxPanel.add(ProxyPanelbtn);
		
		pytestbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	
				if(!proxyfield.getText().equals("") && !protfield.getText().equals("")){
					  boolean flag = TT.pattern(proxyfield.getText(), protfield.getText());
					  if(!flag){
						  JOptionPane.showMessageDialog(null, "地址或端口格式错误！", "提示",JOptionPane.INFORMATION_MESSAGE);
						  return;
					  }
				}else{
					JOptionPane.showMessageDialog(null, "地址端口不可为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				

				String xxx = HttpUtil.doPost("http://baidu.com", "1", true,
						proxyfield.getText(), Integer.valueOf(protfield.getText()));
				if(xxx != null){
				   JOptionPane.showMessageDialog(null, "代理可用", "Message", JOptionPane.PLAIN_MESSAGE); 
				}else{
					JOptionPane.showMessageDialog(null, "代理不可用", "提示",JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		
		
		
		
		pylsbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 new IPTables();
				
			}
		});
		
		

		pxybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				if(!proxyfield.getText().equals("") && !protfield.getText().equals("")){
					  boolean flag = TT.pattern(proxyfield.getText(), protfield.getText());
					  if(!flag){
						  JOptionPane.showMessageDialog(null, "地址或端口格式错误！", "提示",JOptionPane.INFORMATION_MESSAGE);
						  return;
					  }
				}else{
					JOptionPane.showMessageDialog(null, "地址端口不可为空！", "提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Main.bool = true;
				Main.addr = proxyfield.getText();
				Main.prot = Integer.valueOf(protfield.getText()).intValue();

				proxyfield.setEditable(false);
				protfield.setEditable(false);
				pxybtn.setEnabled(false);
			}
		});
		qxbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				proxyfield.setEditable(true);
				protfield.setEditable(true);
				pxybtn.setEnabled(true);
				Main.bool = false;
			}
		});
		proxyfield.setPreferredSize(new Dimension(150, 23));
		protfield.setPreferredSize(new Dimension(150, 23));

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = field.getText();
				if (!text.equals("")) {
					Main.confMap = TT.setConfig(text);

					System.out.println((String) Main.confMap.get("user_id"));
					Main.userId = (String) Main.confMap.get("user_id");
					Main.token = (String) Main.confMap.get("token");
					Main.hshow = (String) Main.confMap.get("hehe_show");

					Main.param = Config.getMcListForm
							.replace("uidTem", Main.userId)
							.replace("tokenTem", Main.token)
							.replace("hshow", Main.hshow);
				}
			}
		});
		area = new JTextArea();
		area.setLineWrap(true);
		area.setWrapStyleWord(true);

		area.setMargin(new Insets(10, 10, 10, 10));
		Font font = new Font("宋体", 0, 18);
		area.setFont(font);
		JScrollPane scroll = new JScrollPane(area);

		

		JPanel ppp = new JPanel(new GridLayout(2, 1));

		JButton button4 = new JButton("开始");
		JButton stopbtn = new JButton("停止");

		ppp.add(button4);
		ppp.add(stopbtn);

		stopbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.flag = false;
				/*Main.area.selectAll();
 				if (Main.area.getSelectedText() != null) {
 					Main.area.setCaretPosition(Main.area.getSelectedText()
 							.length());
 					Main.area.requestFocus();
 				}*/
    	    	 Main.area.append("\n" + "=====已停止抢单 =====");
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(param == ""){
					System.out.println(field.getText());
					JOptionPane.showMessageDialog(null, "未设置参数", "提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				Main.area.setText("正在抢单中，请耐心等候.................");
				Main.flag = true;
				Main.Qiangdan qd = new Main.Qiangdan();
				qd.start();
			}
		});
		frame.add(jpanel, "North");

		frame.add(scroll, "Center");
		frame.add(ppp, "East");
		frame.add(xxPanel, "South");

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
	}

	static class Qiangdan extends Thread {
		private String result;
		private String msg;
		private int index;

		public void run() {
			for (;;) {
				/*Main.area.selectAll();
				if (Main.area.getSelectedText() != null) {
					Main.area.setCaretPosition(Main.area.getSelectedText()
							.length());
					Main.area.requestFocus();
				}
				this.index += 1;
				try {
					this.result = HttpClientUtil.post(
							Config.getMcpeanutListhehe, Main.confMap);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (!TT.isjson(this.result) || this.result.indexOf("[]") != -1) {
					this.result = null;
				}
				
				System.out.println(result);
				
				Main.area.append("线程执行计数：" + this.index + "��" + this.result
						+ "\
						n");
				
				
				
				if (this.result != null) {
					this.msg = JsonUtil.handler(
							JsonUtil.getOrderList(this.result), Main.token,
							Main.userId, Main.hshow);
					
					
					//System.out.println(this.msg);

					
					if (this.msg != null && TT.isjson(this.msg)) {
						
						Main.area.append(Main.area.getText() + "\n" + this.msg);
						JSONObject jsonObject = JSONObject.fromObject(this.msg);
						Message m = (Message) JSONObject.toBean(jsonObject,
								Message.class);
						if (Boolean.valueOf(m.getSuccess()).booleanValue()) {
							break;
						}
					}
				}*/
				
				OperationFlow.Handler(10);
				
				if (!Main.flag) {
					break;
				}
				/*try {
					Thread.sleep(Main.sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
			}
		}
	}
}
