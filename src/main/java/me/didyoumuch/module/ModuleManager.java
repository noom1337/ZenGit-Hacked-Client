package me.didyoumuch.module;

import java.util.ArrayList;

import me.didyoumuch.module.modules.AutoSprint;

public class ModuleManager {
	private ArrayList<AbstractModule> modules;
	public ModuleManager() {
		modules = new ArrayList<>();
		
		modules.add(new AutoSprint());
		
	}
	
	public ArrayList<AbstractModule> getModules(){
		return this.modules;
	}
	
	public AbstractModule getModule(Class cls) {
		for(AbstractModule module : this.getModules()) {
			if(module.getClass() == cls) {
				return module;
			}
		}
		return null;
	}

}
