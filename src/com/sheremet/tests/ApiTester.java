package com.sheremet.tests;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import com.sheremet.client.BratchykViewPanel;
import com.sheremet.client.ClientConnection;
import com.sheremet.client.DBSecureAPI;
import com.sheremet.server.ServerStarter;
import com.sheremet.utils.Bratchyk;

public class ApiTester {
	public static void main(String[] args) {
		try {
//			ServerStarter.main(null);
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
				try{
					switch (key) {
					case 0:o=api.addBratchyk(Maneken.getBratchyk(0L));//ok
					break;
					case 1:o=api.addUser(Maneken.getUser());//ok
					break;
					case 2:o=api.deleteBratchyk(431532377515259L);//ok
					break;
					case 3:o=api.deleteBratchykHistory(431538022101809L, 1875325687L);//ok
					break;
					case 4:o=api.deleteUser(431541131888106L);//ok
					break;
					case 5:o=api.getBratchyk(431534160191623L);//ok
					break;
					case 6:o=api.getBratchykChildren(431534160191623L);//ok
					break;
					case 7:o=api.getBratchykHistory(431539669351030L);//ok
					break;
					case 8:o=api.getHeadBratchyks();//ok
					break;
					case 9:o=api.getUser(431532197364060L);//ok
					break;
					case 10:o=api.getUserList();//ok
					break;
					case 11:o=api.login("qw", "qw");//ok
					break;
					case 12:o=api.logOut();//ok
					break;
					case 13:o=api.setBratchyk(Maneken.getBratchyk(0L), 431539669351030L);//ok
					break;
					case 14:o=api.setUser(Maneken.getUser(), 431532197364060L);//ok
					break;
					case 15:o=api.setUserPermission(431541894317861L, 2);//ok
					break;

					default:
						break;
					}
				}catch(Exception exception){
					exception.printStackTrace();
				}
				//				JFrame frame = new JFrame();
				//				frame.setVisible(true);
				//				BratchykViewPanel panel = new BratchykViewPanel(true);
				//				panel.load((Bratchyk) o);
				//				frame.add(panel);
				System.err.println(o);
			}




		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
