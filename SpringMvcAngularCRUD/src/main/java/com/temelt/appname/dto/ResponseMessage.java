package com.temelt.appname.dto;

/**
 * 
 * @author taner.temel
 *
 */
public class ResponseMessage {
	public enum Type {
		success, warn, error, info;
	}

	private final Type type;
	private final String text;

	public ResponseMessage(Type type, String text) {
		this.type = type;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public Type getType() {
		return type;
	}
}
