package com.sheremet.client;

import java.util.concurrent.LinkedBlockingQueue;

public class ClientConnection extends Thread{
	public class Node {
		String command;
		StringResultHandler handler;
	}
	LinkedBlockingQueue<Node> queue;
	public ClientConnection() {
		// open connection
	}
	public void send(String command, StringResultHandler handler){
		String result = null;
		//push command to queue
		//notify this thread if it is sleeping 
	}
	@Override
	public void run() {
		//		send all commands in infinite cicle to server and invoke StringResultHandler's method handle
	}
}
abstract class StringResultHandler{
	abstract void handle(String s) throws Exception;
}
