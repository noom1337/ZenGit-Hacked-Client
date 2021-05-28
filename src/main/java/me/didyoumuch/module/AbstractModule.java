package me.didyoumuch.module;

import java.util.ArrayList;

import me.didyoumuch.utils.settings.AbstractSetting;
import net.minecraft.client.Minecraft;

public class AbstractModule {
	protected Minecraft mc = Minecraft.getInstance();
	private String name;
	private int key;
	private boolean enabled;
	private ArrayList<AbstractSetting> settings = new ArrayList<>();
	private Category category;
	private boolean keyFlag;


	public AbstractModule(String name, Category category) {
		super();
		this.key = 0;
		this.enabled = false;
		this.name = name;
		this.category = category;
	}
	
	public boolean isKeyFlag() {
		return keyFlag;
	}
	public void setKeyFlag(boolean keyFlag) {
		this.keyFlag = keyFlag;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		if (this.isEnabled()) {
			this.onEnable();
		} else {
			this.onDisable();
		}
	}

	public void onEnable(){
	}

	public void onDisable(){
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

	public ArrayList<AbstractSetting> getSettings() {
		return settings;
	}

	public void addSetting(AbstractSetting... settings) {
		for(AbstractSetting s : settings) {

			getSettings().add(s);
		}
	}

	public void onUpdate() {

	}
	public void onRender2d() {
		
	}
	public void onRender3d() {
		
	}
	public void onPacket() {

	}




}
