package me.didyoumuch.module.modules;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.module.Category;
import me.didyoumuch.utils.settings.BoolSetting;
import me.didyoumuch.utils.settings.FloatSetting;

public class AutoSprint extends AbstractModule {
	public AutoSprint() {
		super("AutoSprint", Category.PLAYER);
		this.addSetting(new FloatSetting("TestFloatSetting", 10, 1, 100, false));
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(mc.gameSettings.keyBindForward.isKeyDown()) {
			mc.player.setSprinting(true);
		}
	}

}
