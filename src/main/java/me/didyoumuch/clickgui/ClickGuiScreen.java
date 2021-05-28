package me.didyoumuch.clickgui;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.Core;
import me.didyoumuch.clickgui.panel.Panel;
import me.didyoumuch.module.Category;
import me.didyoumuch.module.modules.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public class ClickGuiScreen extends Screen{
	private ArrayList<Panel> panels = new ArrayList<Panel>();

	public ClickGuiScreen() {
		super(new StringTextComponent("clickgui"));
		
		int xOffset = 10;
		int y = 40;
		int width = 80;
		int height = 15;
		for(Category c : Category.values()) {
			this.panels.add(new Panel(xOffset, y, width, height, c));
			xOffset += width + 20;
		}
	}
	
	public ArrayList<Panel> getPanels() {
		return panels;
	}
	
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.panels.stream().forEach(p ->{p.onRender(p_render_1_, p_render_2_);});
		super.render(p_render_1_, p_render_2_, p_render_3_);
	}
	
	@Override
	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
		if(p_keyPressed_1_ == Core.instance.getModuleManager().getModule(ClickGui.class).getKey() || p_keyPressed_1_ == GLFW.GLFW_KEY_ESCAPE) {
			this.onClose();
			Minecraft.getInstance().gameRenderer.getShaderGroup().close();
			return true;
		}
		this.panels.stream().forEach(p ->{p.onKey(p_keyPressed_1_);});

		return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		this.panels.stream().forEach(p ->{p.mouseClicked((int)p_mouseClicked_1_, (int)p_mouseClicked_3_, p_mouseClicked_5_);});
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}
	
	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
		this.panels.stream().forEach(p ->{p.mouseReleased((int)p_mouseReleased_1_, (int)p_mouseReleased_3_, p_mouseReleased_5_);});
		return super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}
	
	
	
	
}
