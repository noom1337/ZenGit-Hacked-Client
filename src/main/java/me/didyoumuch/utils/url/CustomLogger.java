package me.didyoumuch.utils.url;

import me.didyoumuch.Core;

public class CustomLogger {
	private String name;
	
	public CustomLogger(String name) {
		this.name = name;
	}
	
	public void log(String message) {
		System.out.println("["+this.getName()+"] " + message);
	}
	
	public String getName() {
		return name;
	}

}
