package com.sheremet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientConnection extends Thread{
	private Socket socket; 
	private PrintWriter writer;
	private LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
	private Scanner scanner;
	public class Node {
		public Node(String command, StringResultHandler handler) {
			super();
			this.command = command;
			this.handler = handler;
		}
		String command;
		StringResultHandler handler;
	}
	public ClientConnection() throws UnknownHostException, IOException {
		socket = new Socket(InetAddress.getLocalHost(), 9999);//for local server. change it for remote server
		writer = new PrintWriter(socket.getOutputStream());
		scanner = new Scanner (socket.getInputStream());
		start();
	}
	public void send(String command, StringResultHandler handler){

		command = to1line(command);
		queue.add(new Node(command, handler));
	}
	private static String to1line(String command) {
		command=command.replace("\n", " ");
		command=command.replace("\r", " ");
		return command;
	}
	@Override
	public void run() {
			while(true){
				try {
					Node node = queue.take();
					writer.println(node.command);
					writer.flush();
					node.handler.handle(scanner.nextLine());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

	}
}