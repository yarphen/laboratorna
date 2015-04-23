package com.sheremet.tests;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketThread extends Thread{
	private Socket socket;
	public SocketThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		try{
			Scanner scanner = new Scanner(socket.getInputStream());
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			String temp;
			do{
				temp = scanner.nextLine();
				System.out.println(temp);
			}while(!temp.isEmpty());
			printWriter.println("HTTP/1.0 200 OK");
			printWriter.println("Content-Type: text/plain");
			printWriter.println("Cache-Control: no-store");
			printWriter.println("");
			printWriter.println("Hello world!");
			printWriter.println("");
			printWriter.flush();
			socket.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
