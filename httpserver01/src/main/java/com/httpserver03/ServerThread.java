package com.httpserver03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
	private Socket s;

	public ServerThread(Socket s) {
		this.s = s;
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String requestLine = br.readLine();
			System.out.println(requestLine);
			//String queryString=requestLine.split(" ")[1].indexOf("?")==-1?null:requestLine.split(" ")[1].split("\\?")[1];
			//String requestURI=requestLine.split(" ")[1].indexOf("?")==-1?requestLine.split(" ")[1]:requestLine.split(" ")[1].split("\\?")[0];
			
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
