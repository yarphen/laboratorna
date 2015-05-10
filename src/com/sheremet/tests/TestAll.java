package com.sheremet.tests;

import com.sheremet.client.ClientStarter;
import com.sheremet.server.ServerStarter;

public class TestAll {
	private static void init() {
		ServerStarter.main(null);
		ClientStarter.main(null);
	}
	public static void main(String[] args) {
		init();
	}
}
