package com.xzg.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.xzg.util.HtmlUtil;


public class IPTables{
	
	     public static int page = 1;
	
	     public static String[] columnNames;
	     
	     public static String[][] heros;
	     
	      public IPTables(){
	    	   JFrame f = new JFrame("代理IP列表");
	           f.setSize(500, 400);
	           //f.setLocation(200, 200);
	           f.setLayout(new BorderLayout());
	           
	           JPanel  panel = new JPanel(new GridLayout(1,2));
	           JRadioButton jrjx=new JRadioButton("精选免费IP");
	           JRadioButton jr89=new JRadioButton("89免费IP");
	           JRadioButton jrkdl=new JRadioButton("快代理免费IP");
	           
	           jrjx.setSelected(true);
	           
	           ButtonGroup  bg1=new ButtonGroup();
	           bg1.add(jrjx);
	           bg1.add(jr89);
	           bg1.add(jrkdl);
	           
	           panel.add(jrjx);
	           panel.add(jr89);
	           panel.add(jrkdl);
	             
	           
	           JPanel  panel2 = new JPanel(new GridLayout(1,4));
	           JButton  upBtn = new JButton("上一页");
	           JButton  downBtn = new JButton("下一页");
	           JTextField gofield = new JTextField();
	           gofield.setText(page+"");
	           JButton  jumpBtn = new JButton("跳转");
	           
	           panel2.add(upBtn);
	           panel2.add(downBtn);
	           panel2.add(gofield);
	           panel2.add(jumpBtn);
	           
	            columnNames = new String[] { "地址", "端口", "城市","验证时间"};
	            heros = new String[][] { { "116.114.19.204", "443", "内蒙古呼和浩特市"},
	                   { "123.207.68.166", "1080", "腾讯云广州数据中心 "}, { "116.114.19.211", "443", "内蒙古呼和浩特市" },
	                   { "117.191.11.113", "8080", "未知"},{ "121.40.90.189", "8001", "未知"},
	                   { "182.61.109.24", "8888", "未知"},{ "119.28.203.242", "8000", "深圳市腾讯IDC机房(BGP)"},
	                   { "120.79.187.118", "3128", "阿里云BGP数据中心"},{ "60.205.159.195", "3128", "阿里云BGP服务器"},
	                   { "106.14.82.38", "8080", "上海阿里云BGP数据中心"},{ "123.207.57.92", "1080", "腾讯云广州数据中心"},
	                   { "115.159.31.195", "8080", "深圳市腾讯上海BGP数据中心(BGP)"}};
	           JTable t = new JTable();
	    
	            t.setModel(new DefaultTableModel(heros,columnNames));
	           
	            t.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {
						if(e.getValueIsAdjusting() == true){
							int row = t.getSelectedRow();
							String host = String.valueOf(t.getValueAt(row, 0));
							int prot = Integer.valueOf(t.getValueAt(row, 1).toString());
							Main.addr = host;
							Main.prot = prot;
							Main.proxyfield.setText(host);
							Main.protfield.setText(String.valueOf(prot));
							Main.protfield.setEditable(false);
							Main.proxyfield.setEditable(false);
							Main.pxybtn.setEnabled(false);
							Main.bool = true;
						}else{
							 f.dispose();
						}	
					}
					
					
				});
	            
	            t.setEnabled(true);
	           
	           // 根据t创建 JScrollPane
	           JScrollPane sp = new JScrollPane(t);
	           
	    
	           //或则创建一个空的JScrollPane，再通过setViewportView把table放在JScrollPane中
	           // JScrollPane sp = new JScrollPane(t);
	           // sp.setViewportView(t);
	           
	           //radio选中事件
	           jr89.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Vector<Vector<Object>> datas = HtmlUtil.get89Ip(page);
						datas.remove(0);
						Vector<Object>  columnNames = new Vector<>();
						columnNames.add("地址");
						columnNames.add("端口");
						columnNames.add("城市及运营商");
						columnNames.add("验证时间");
						
						t.setModel(new DefaultTableModel(datas,columnNames));
					}
				});
	           //快ID选中
	           jrkdl.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Vector<Vector<Object>> datas = HtmlUtil.getKdaili(page);
						datas.remove(1);
						Vector<Object>  columnNames = datas.get(0);
						datas.remove(0);
						t.setModel(new DefaultTableModel(datas,columnNames));
					}	
			});
	           
	           jrjx.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					t.setModel(new DefaultTableModel(heros,columnNames));
				}
			});
	           
	           //按钮事件 下一页
	           downBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(jrjx.isSelected()){return;}
					page = page + 1;
					Vector<Vector<Object>> datas = null;
					if(jr89.isSelected()){
					    datas = HtmlUtil.get89Ip(page);
						datas.remove(0);
						Vector<Object>  columnNames = new Vector<>();
						columnNames.add("地址");
						columnNames.add("端口");
						columnNames.add("城市");
						columnNames.add("验证时间");
						t.setModel(new DefaultTableModel(datas,columnNames));
					}
					if(jrkdl.isSelected()){
					    datas = HtmlUtil.getKdaili(page);
						datas.remove(1);
						Vector<Object>  columnNames = datas.get(0);
						datas.remove(0);
						t.setModel(new DefaultTableModel(datas,columnNames));
					}
					gofield.setText(page+"");
				}
			});
	           //上一页
	           upBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(jrjx.isSelected()){return;}
						page = page-1<0?1:page-1;
						Vector<Vector<Object>> datas = null;
						if(jr89.isSelected()){
							datas = HtmlUtil.get89Ip(page);
							datas.remove(0);
							Vector<Object>  columnNames = new Vector<>();
							columnNames.add("地址");
							columnNames.add("端口");
							columnNames.add("城市");
							columnNames.add("验证时间");
							t.setModel(new DefaultTableModel(datas,columnNames));
						}
						if(jrkdl.isSelected()){
						    datas = HtmlUtil.getKdaili(page);
							datas.remove(1);
							Vector<Object>  columnNames = datas.get(0);
							datas.remove(0);
							t.setModel(new DefaultTableModel(datas,columnNames));
						}
						gofield.setText(page+"");
					}
			});
	            //跳转
	           jumpBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {	
					if(jrjx.isSelected()){return;}
					
					if(gofield.getText().equals("") || !Pattern.matches("^\\d+$", gofield.getText())){
						       return;
					}
					
					Vector<Vector<Object>> datas = null;
					if(jr89.isSelected()){
						datas = HtmlUtil.get89Ip(Integer.valueOf(gofield.getText()));
						datas.remove(0);
						Vector<Object>  columnNames = new Vector<>();
						columnNames.add("地址");
						columnNames.add("端口");
						columnNames.add("城市");
						columnNames.add("验证时间");
						t.setModel(new DefaultTableModel(datas,columnNames));
					}
					if(jrkdl.isSelected()){
					    datas = HtmlUtil.getKdaili(Integer.valueOf(gofield.getText()));
						datas.remove(1);
						Vector<Object>  columnNames = datas.get(0);
						datas.remove(0);
						t.setModel(new DefaultTableModel(datas,columnNames));
					}
					page = Integer.valueOf(gofield.getText());
				}
			});
	          
	               
	    
	           // 把sp而非JTable加入到JFrame上，
	           f.add(panel,"North");
	           f.add(sp,"Center");
	           f.add(panel2, "South");
	    
	           //只关闭子窗体
	           f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	           //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	           
	           f.setLocationRelativeTo(null);
	    
	           f.setVisible(true);
	           
	           f.setResizable(false);

	      } 
			
	    	   
		  public static void main(String[] args) {
			       new IPTables();
		}
}
