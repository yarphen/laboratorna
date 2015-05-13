package com.sheremet.client;

public class ServerException extends Exception{
	private final String message;
	public ServerException(String send) {
		super();
		message=send;
	}
	@Override
	public String getMessage() {
		return "ServerException";
	}
	@Override
	public String getLocalizedMessage() {
		return message;
	}
}
