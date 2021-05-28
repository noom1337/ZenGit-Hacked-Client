package me.didyoumuch.module;

import java.util.ArrayList;

import me.didyoumuch.Core;
import me.didyoumuch.module.modules.AutoSprint;
import me.didyoumuch.module.modules.ClickGui;
import me.didyoumuch.module.modules.TestModule1;

public class ModuleManager {
	private ArrayList<AbstractModule> modules;
	public ModuleManager() {
		modules = new ArrayList<>();
		
		modules.add(new AutoSprint());
		modules.add(new ClickGui());
		modules.add(new TestModule1());
		
		Core.instance.getLogger().log("Loaded " + modules.size() + " modules");
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
