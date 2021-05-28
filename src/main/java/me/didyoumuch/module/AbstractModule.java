package me.didyoumuch.module;

import java.util.ArrayList;

import me.didyoumuch.utils.settings.AbstractSetting;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class AbstractModule {
	protected Minecraft mc = Minecraft.getInstance();
	private String name;
	private int key;
	private boolean enabled;
	private ArrayList<AbstractSetting> settings = new ArrayList<>();
	private Category category;


	public AbstractModule(String name, Category category) {
		super();
		this.key = 0;
		this.enabled = false;
		this.name = name;
		this.category = category;
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
		MinecraftForge.EVENT_BUS.register(this);
	}

	public void onDisable(){
		MinecraftForge.EVENT_BUS.unregister(this);
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
	public void onPacket() {

	}




}
