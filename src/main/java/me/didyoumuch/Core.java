package me.didyoumuch;

import me.didyoumuch.module.ModuleManager;
import me.didyoumuch.utils.EventHandler;
import me.didyoumuch.utils.url.UrlUtils;
import me.didyoumuch.utils.url.CustomLogger;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("zengit")
public class Core
{
	private String version = "1.0";
	private String clientName = "ZenGit";
	
	public static Core instance;
	private ModuleManager moduleManager;
	private CustomLogger logger;
	
    public Core() {
    	logger = new CustomLogger(getClientName());
    	if(UrlUtils.getVersion().contains(this.getVersion())) {
    		getLogger().log("Client inited!");
        	Core.instance = this;
        	this.moduleManager = new ModuleManager();
        	
            MinecraftForge.EVENT_BUS.register(new EventHandler());
    	}
    	else {
    		getLogger().log("Client is outdated! Download new version!");
    	}
    }
    
    public ModuleManager getModuleManager() {
		return moduleManager;
	}
    
    public CustomLogger getLogger() {
		return logger;
	}
    
    public String getClientName() {
		return clientName;
	}
    public String getVersion() {
		return version;
	}
}
