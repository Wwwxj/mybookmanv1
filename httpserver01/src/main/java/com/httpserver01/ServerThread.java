package com.httpserver01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread extends Thread{
	private Socket s;
public ServerThread(Socket s) {
		this.s=s;
	}
@Override
public void run() {
	try {
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		String requestLine=br.readLine();
		//要第一个
		String method=requestLine.split(" ")[0];
		String str=null;
		while((str=br.readLine())!=null&&!str.equals("")) {
			System.out.println(str);
		}
		//空白行
		System.out.println();
		//如果是POST有体
		if(method.equals("POST")) {
			char[] ch=new char[1024];
			int a=-1;
			while((a=br.read(ch))!=-1) {
				String str1=new String(ch, 0, a);
				//有可能没有回车，所以不能println
				System.out.print(str1);
			}
		}
		
		System.out.println("----------------");
		//作出响应
		PrintWriter pw=new PrintWriter(s.getOutputStream());
		//响应行->类型/版本   200是连接上   OK是状态处于OK 
		pw.println("HTTP/1.1 200 OK");
		//响应头
		pw.println("Content-Type:text/html;charset=utf-8");
		//空白行(用于区分响应头和响应体)
		pw.println();
		//响应体
		pw.print("<html>"+'\n'
	            +"<head><tilte>我的java项目</tilte></head>"+'\n'
			    +"<body>"+'\n'
	            +"<b>为您报时：");
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	pw.println(sdf.format(new Date()));
	pw.println("</b>"+'\n'
	            +"</body>"+'\n'
			     +"</html>");
	    pw.flush();//强行刷新
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
}
