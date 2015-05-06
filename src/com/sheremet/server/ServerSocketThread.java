package com.sheremet.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.sheremet.utils.Command;
import com.sheremet.utils.Parser;

public class ServerSocketThread extends Thread{
	private Socket socket;
	private DBAPI api;
	private SecurityManager manager;
	public ServerSocketThread(Socket socket) {
		this.socket=socket;
	}
	@Override
	public void run() {
		try{
			Scanner scanner = new Scanner(socket.getInputStream());
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			while (scanner.hasNextLine()){
				String string = scanner.nextLine();
				Command command = new Command(Parser.parseXMLtoCommandHashMap(string));
				writer.println(Parser.unparseXMLfromResultObject(command.execute(manager)));
			}
			scanner.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
