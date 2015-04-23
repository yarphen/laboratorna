package com.sheremet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientConnection extends Thread{
	private Socket socket; 
	private PrintWriter writer;
	private LinkedBlockingQueue<Node> queue;
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
		socket = new Socket(InetAddress.getLocalHost(), 80);//for local server. change it for remote server
		writer = new PrintWriter(socket.getOutputStream());
		scanner = new Scanner (socket.getInputStream());
	}
	public void send(String command, StringResultHandler handler){
		//push command to queue
		//notify this thread if it is sleeping 
		queue.add(new Node(command, handler));
		synchronized (this) {
			notify();
		}
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
						// TODO Auto-generated catch block
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
		//		send all commands in infinite cicle to server and invoke StringResultHandler's method handle
	}
}
abstract class StringResultHandler{
	abstract void handle(String s) throws Exception;
}
