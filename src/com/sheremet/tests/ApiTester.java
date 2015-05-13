package com.sheremet.tests;

import java.io.IOException;
import java.util.Scanner;

import com.sheremet.client.ClientConnection;
import com.sheremet.client.DBSecureAPI;
import com.sheremet.server.ServerStarter;

public class ApiTester {
	public static void main(String[] args) {
		try {
			ServerStarter.main(null);
			DBSecureAPI api = new DBSecureAPI(new ClientConnection());
			Scanner scanner = new Scanner(System.in);
			while(true){
				int key;
				while(true){
					try{
						key = scanner.nextInt();
						break;
					}catch(Exception e){continue;}
				}
				Object o=null;
				switch (key) {
				case 0:o=api.addBratchyk(Maneken.getBratchyk());
					break;
				case 1:o=api.addUser(Maneken.getUser());
					break;
				case 2:o=api.deleteBratchyk(Maneken.getLong());
					break;
				case 3:o=api.deleteBratchykHistory(Maneken.getLong(), Maneken.getInt());
					break;
				case 4:o=api.deleteUser(Maneken.getLong());
					break;
				case 5:o=api.getBratchyk(431370869118022L);//Maneken.getLong());
					break;
				case 6:o=api.getBratchykChildren(Maneken.getLong());
					break;
				case 7:o=api.getBratchykHistory(Maneken.getLong());
					break;
				case 8:o=api.getHeadBratchyks();
					break;
				case 9:o=api.getUser(Maneken.getLong());
					break;
				case 10:o=api.getUserList();
					break;
				case 11:o=api.login(Maneken.getString("login:"), "password");
					break;
				case 12:o=api.logOut();
					break;
				case 13:o=api.setBratchyk(Maneken.getBratchyk(), 431370869118022L);
					break;
				case 14:o=api.setUser(Maneken.getUser(), Maneken.getLong());
					break;
				case 15:o=api.setUserPermission(Maneken.getLong(), Maneken.getInt());
					break;

				default:
					break;
				}
				System.err.println(o);
			}




		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
