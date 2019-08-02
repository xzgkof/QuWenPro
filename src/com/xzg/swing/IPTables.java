package com.xzg.swing;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class IPTables{
	
	      public IPTables(){
	    	  JFrame f = new JFrame("代理IP列表");
	           f.setSize(500, 400);
	           //f.setLocation(200, 200);
	           f.setLayout(new BorderLayout());
	           
	           JLabel  lb = new JLabel();
	           lb.setText("<html><body>"
	           		+ "<p align=\"center\">"
	           		+ "&nbsp&nbsp&nbsp免费代理IP列表&nbsp&nbsp&nbsp"
	           		+"更多免费IP请转到 http://www.89ip.cn/index_5.html"
	           		+ "</p>"  
	           		+ "</body></html>");
	            
	           
	           String[] columnNames = new String[] { "host", "prot", "remark"};
	           String[][] heros = new String[][] { { "116.114.19.204", "443", "内蒙古呼和浩特市" },
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
	           
	          
	               
	    
	           // 把sp而非JTable加入到JFrame上，
	           f.add(lb,"North");
	           f.add(sp,"Center");
	    
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
