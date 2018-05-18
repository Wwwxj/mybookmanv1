 package com.httpserver02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread02 extends Thread{
	private Socket s;
public ServerThread02(Socket s) {
		this.s=s;
	}
@Override
public void run() {
	try {
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		//请求行(第一行)
		
		String requestLine=br.readLine();
		System.out.println(requestLine);
		//请求行第一部分(method)
		String method=requestLine.split(" ")[0];
		//请求行第二部分(requestURI)
		String requestURI=requestLine.split(" ")[1];
		//请求头(readLine()读)
		String str=null;
		while((str=br.readLine())!=null&&!str.equals("")) {
			System.out.println(str);
		}
		//空白行
		System.out.println();
		//如果是POST有请求体(用char数组读)
		if(method.equals("POST")) {
			char[] ch=new char[1024];
			int a=-1;
			a=br.read(ch);
			//while((a=br.read(ch))!=-1) {
				String str1=new String(ch, 0, a);
				//有可能没有回车，所以不能println
				System.out.print(str1);
			//}
		}
		
		System.out.println("----------------");
		//作出响应
		PrintWriter pw=new PrintWriter(s.getOutputStream());
		
		if(requestURI.endsWith(".html")) {
			//响应行->类型/版本   200是连接上   OK是状态处于OK 
			pw.println("HTTP/1.1 200 OK");
			//响应头(用print)
			pw.println("Content-Type:text/html;charset=utf-8");
			//空白行(用于区分响应头和响应体)
			pw.println();
			//响应体
			BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream("WebContent/"+requestURI)));
			//体有可能不换行(用char读)
			char[] cs=new char[1024];
			int b=-1;
			while((b=br2.read(cs))!=-1) {
				pw.write(cs,0,b);
			}
			pw.flush();
			br2.close();
		}else {
			//响应行->类型/版本   200是连接上   OK是状态处于OK 
			pw.println("HTTP/1.1 404 Not Found");
			//响应头(用print)
			pw.println("Content-Type:text/html;charset=utf-8");
			//空白行(用于区分响应头和响应体)
			pw.println();
			//响应体
			BufferedReader br2=new BufferedReader(new InputStreamReader(new FileInputStream("WebContent/notFound.html")));
			char[] cs=new char[1024];
			int b=-1;
			while((b=br2.read(cs))!=-1) {
				pw.write(cs,0,b);
			}
			pw.flush();
			br2.close();
		}
	} catch (IOException e) {
		
		e.printStackTrace();
	}finally {
		try {
			s.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
}
}
