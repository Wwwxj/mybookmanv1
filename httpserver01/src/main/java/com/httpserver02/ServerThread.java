package com.httpserver02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket s;

	public ServerThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			// 接收
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			// 请求行
			String requestLine = br.readLine();
			System.out.println(requestLine);
			// Method
			
			String method = requestLine.split(" ")[0];
			// request-URI
			String requestURI = requestLine.split(" ")[1];
			// 请求头
			String string1 = null;
			while ((string1 = br.readLine()) != null && !string1.equals("")) {
				System.out.println(string1);
			}
			// 空白行
			System.out.println();
			// 请求体
			if(method.equals("POST")) {
				char[] ch = new char[1024];
				int a = -1;
				// 不能用while循环了
				a = br.read(ch);
				// while((a=br.read(ch))!=-1) {
				String string2 = new String(ch, 0, a);
				System.out.print(string2);
			}
			
			// }
			System.out.println("-----------------------");
			// 响应
			PrintWriter pw = new PrintWriter(s.getOutputStream());
			if (requestURI.endsWith(".html")) {
				// 响应行
				pw.println("HTTP/1.1 200 OK");
				// 响应头
				pw.println("Centent-Type:text/html;charset=utf-8");
				//空白行
				pw.println();
				// 响应体
				/*
				 * 我要根据我在html文件里读到的东西来进行响应 所以先要读
				 */
				BufferedReader br2 = new BufferedReader(
						new InputStreamReader(new FileInputStream("WebContent/" + requestURI)));
				char[] ch2 = new char[1024];
				int b = -1;
				while ((b=br2.read(ch2)) != -1) {
					pw.write(ch2, 0, b);
				}
				// 刷新
				pw.flush();
				br2.close();
			} else {
				// 响应行
				pw.println("HTTP/1.1 404 Not Found");
				// 响应头
				pw.println("Centent-Type:text/html;charset=utf-8");
				//空白行
				pw.println();
				// 响应体
				/*
				 * 我要根据我在html文件里读到的东西来进行响应 所以先要读
				 */
				String string4="notFound.html";
				BufferedReader br2 = new BufferedReader(
						new InputStreamReader(new FileInputStream("WebContent/"+string4)));
				char[] ch2 = new char[1024];
				int b = -1;
				while ((b=br2.read(ch2)) != -1) {
					pw.write(ch2, 0, b);
				}
				// 刷新
				pw.flush();
				br2.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
	}
}
