package me.didyoumuch.utils;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.Core;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.module.modules.AutoSprint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
	
	@SubscribeEvent
	public void playerTickEvent(PlayerTickEvent event) {
		if(Minecraft.getInstance().world == null || Minecraft.getInstance().player == null)return;
		Core.instance.getModuleManager().getModules().stream().forEach(module ->{
			if(module.isEnabled()) {
				AutoSprint autoSprint = (AutoSprint) Core.instance.getModuleManager().getModule(AutoSprint.class);
				autoSprint.getSettings().get(0).setCurrentValue(99);
				module.getSettings().forEach(s ->{
					Core.instance.getLogger().log(s.getName() + " | " + s.getCurrentValue());
				});
				module.onUpdate();
			}
		});
	}
	
	@SubscribeEvent
	public void clientTickEvent(ClientTickEvent event) {
		if(Minecraft.getInstance().world == null || Minecraft.getInstance().player == null)return;
		
		//TODO: some client tick code...
	}
	
	@SubscribeEvent
	public void keyInputEvent(KeyInputEvent event) {
		if(Minecraft.getInstance().world == null || Minecraft.getInstance().player == null || (Minecraft.getInstance().currentScreen instanceof ChatScreen))return;
		
        int keyCode = event.getKey();
        int action = event.getAction();
		
		if(keyCode >= 0) {
			Core.instance.getModuleManager().getModules().stream().forEach(module ->{
				if(module.getKey() == keyCode && action == GLFW.GLFW_PRESS) {
					module.setEnabled(!module.isEnabled());
				}
			});
		}
		
	}

}
