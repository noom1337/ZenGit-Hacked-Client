package me.didyoumuch.module.modules;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.Core;
import me.didyoumuch.clickgui.ClickGuiScreen;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;

public class ClickGui extends AbstractModule {
	public ClickGui() {
		super("ClickGui", Category.RENDER);
		this.setKey(GLFW.GLFW_KEY_RIGHT_SHIFT);
	}
	
	@Override
	public void onEnable() {
		mc.gameRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
		mc.displayGuiScreen(Core.instance.getClickGui());
//		mc.displayGuiScreen(new ClickGuiScreen());
		this.setEnabled(false);
		super.onEnable();
	}

}
