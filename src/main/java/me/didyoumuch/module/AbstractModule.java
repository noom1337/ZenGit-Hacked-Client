package me.didyoumuch.module;

import net.minecraft.client.Minecraft;

public class AbstractModule {
	protected Minecraft mc = Minecraft.getInstance();
	private String name;
	private int key;
	private boolean enabled;
	
	
	public AbstractModule(String name) {
		this.name = name;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getName() {
		return name;
	}
	public int getKey() {
		return key;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setKey(int key) {
		this.key = key;
	}
	
	public void toggle() {
		this.enabled = !this.enabled;
	}
	
	public void onUpdate() {
		
	}
	public void onPacket() {
		
	}
	

}
