package com.sheremet.tests;

import java.util.regex.Pattern;

public class SimpleTester {
	public static void main(String[] args) {
		String s = "act=\"login\"\\login=\"qwerty\"\\password=\"empty\"";
		String [] array = s.split(Pattern.quote("\\"));
		String temp = array[0];
		String[] arr2 = temp.split("=", 2);
		System.out.println(arr2[0]);
		System.out.println(arr2[1]);
		System.err.println();
	}
}
