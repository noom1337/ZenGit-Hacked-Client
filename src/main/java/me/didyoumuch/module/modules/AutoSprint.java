package me.didyoumuch.module.modules;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.module.AbstractModule;

public class AutoSprint extends AbstractModule {
	public AutoSprint() {
		super("AutoSprint");
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(mc.gameSettings.keyBindForward.isKeyDown()) {
			mc.player.setSprinting(true);
		}
	}

}
