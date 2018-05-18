package com.httpserver01;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		
		try(ServerSocket ss=new ServerSocket(80);){
			boolean b=true;
		while(b) {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			Socket s=ss.accept();
			Thread t=new ServerThread(s);
			t.start();
		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
