package com.httpserver03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try (ServerSocket ss = new ServerSocket(80);) {
             while(true) {
            	 Socket s=ss.accept();
            	 System.out.println("连接成功");
            	 Thread t=new ServerThread(s);
            	 t.start();
             }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
