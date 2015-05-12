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
		synchronized (this) {
			notify();
		}
	}
	private static String to1line(String command) {
		command=command.replace("\n", " ");
		command=command.replace("\r", " ");
		return command;
	}
	@Override
	public void run() {
		synchronized (this) {
			while(true){

				while (!queue.isEmpty()){
					Node node = null;
					try {
						node = queue.take();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					writer.println(node.command);
					writer.flush();
					try {
						node.handler.handle(scanner.nextLine());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try{
					wait();
				}catch(InterruptedException e1){

				}
			}
		}
		
	}
	public static PreparedStatement prepeareStatement(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}