package me.didyoumuch;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.clickgui.ClickGuiScreen;
import me.didyoumuch.hooks.ProfilerHook;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.module.ModuleManager;
import me.didyoumuch.utils.url.UrlUtils;
import me.didyoumuch.utils.url.CustomLogger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.Mod;

@Mod("zengit")
public class Core
{
	private String version = "1.0";
	private String clientName = "ZenGit";
	
	public static Core instance;
	private ModuleManager moduleManager;
	private CustomLogger logger;
	private ClickGuiScreen clickGui;
	private Timer timer;
	
    public Core() {
    	logger = new CustomLogger(getClientName());
    	if(UrlUtils.getVersion().contains(this.getVersion())) {
    		getLogger().log("Client inited!");
        	Core.instance = this;
        	this.moduleManager = new ModuleManager();
        	this.clickGui = new ClickGuiScreen();
        	try {
    			Field modifiersField = Field.class.getDeclaredField("modifiers");
    			modifiersField.setAccessible(true);
    			Field timerField = Minecraft.class.getDeclaredFields()[20];
    			timerField.setAccessible(true);
    			modifiersField.setInt(timerField, timerField.getModifiers() & ~Modifier.FINAL);
    			this.timer = (Timer) timerField.get(Minecraft.getInstance());
    			Field profilerField = Minecraft.class.getDeclaredFields()[66];
    			profilerField.setAccessible(true);
    			modifiersField.setInt(profilerField, profilerField.getModifiers() & ~Modifier.FINAL);
    			ProfilerHook profiler = new ProfilerHook(() -> {
    				return this.timer.elapsedTicks;
    			});
    			profilerField.set(Minecraft.getInstance(), profiler);
        	}catch (Exception e) {
        		getLogger().log("Cannot hook timer or profiler");
			}
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
    
    public ClickGuiScreen getClickGui() {
		return clickGui;
	}
    
    public void onKey() {
		if (Minecraft.getInstance().world == null || Minecraft.getInstance().player == null)
			return;
		for (AbstractModule m : getModuleManager().getModules()) {
			if (Minecraft.getInstance().currentScreen != null)
				return;
			if (m.getKey() > 0) {
				if (!InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), m.getKey()))
					m.setKeyFlag(false);

				if (InputMappings.isKeyDown(Minecraft.getInstance().mainWindow.getHandle(), m.getKey()) && !m.isKeyFlag()) {
					m.setEnabled(!m.isEnabled());;
					m.setKeyFlag(true);
				}
			}

		}
    }
    public void onRender2d() {
		if (Minecraft.getInstance().world == null || Minecraft.getInstance().player == null)
			return;
		
		for (AbstractModule m : getModuleManager().getModules()) {
			if (m.isEnabled())m.onRender2d();
				
		}
    }
    public void onUpdate() {
		if (Minecraft.getInstance().world == null || Minecraft.getInstance().player == null)
			return;
		for (AbstractModule m : getModuleManager().getModules()) {
			if (m.isEnabled())
				m.onUpdate();
		}
    }
    public void onRender3d() {
		if (Minecraft.getInstance().world == null || Minecraft.getInstance().player == null)
			return;
		for (AbstractModule m : getModuleManager().getModules()) {
			if (m.isEnabled())
				m.onRender3d();
		}
    }
    
	private static boolean[] keyStates = new boolean[512];

	public static boolean checkKey(int i) {
		if (Minecraft.getInstance().currentScreen != null) {
			return false;
		}

		if (GLFW.glfwGetKey(Minecraft.getInstance().mainWindow.getHandle(), i) == 1 != keyStates[i]) {
			return keyStates[i] = !keyStates[i];
		} else {
			return false;
		}
	}
}
