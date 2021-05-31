package me.didyoumuch.clickgui;

import java.util.ArrayList;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.Core;
import me.didyoumuch.clickgui.element.elements.ElementPanel;
import me.didyoumuch.module.Category;
import me.didyoumuch.module.modules.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.StringTextComponent;

public class ClickGuiScreen extends Screen{
	private Minecraft mc = Minecraft.getInstance();
	private ArrayList<ElementPanel> panels = new ArrayList<ElementPanel>();
	
	public ClickGuiScreen() {
		super(new StringTextComponent("clickgui"));
		int xOff = 20;
		for(Category category : Category.values()) {
			int width = 100;
			int height = 15;
			panels.add(new ElementPanel(xOff, 40, width, height, category));
			xOff += width +5;
		}
	}
	
	public ArrayList<ElementPanel> getPanels() {
		return panels;
	}
	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		this.getPanels().stream().forEach(panel ->{panel.onRender(p_render_1_, p_render_2_);});
		super.render(p_render_1_, p_render_2_, p_render_3_);
	}
	
	@Override
	public boolean mouseClicked(double p_mouseClicked_1_, double p_mouseClicked_3_, int p_mouseClicked_5_) {
		this.getPanels().stream().forEach(panel ->{panel.mouseClicked((int)p_mouseClicked_1_, (int)p_mouseClicked_3_, p_mouseClicked_5_);});
		return super.mouseClicked(p_mouseClicked_1_, p_mouseClicked_3_, p_mouseClicked_5_);
	}
	@Override
	public boolean mouseReleased(double p_mouseReleased_1_, double p_mouseReleased_3_, int p_mouseReleased_5_) {
		this.getPanels().stream().forEach(panel ->{panel.mouseReleased((int)p_mouseReleased_1_, (int)p_mouseReleased_3_, p_mouseReleased_5_);});
		return super.mouseReleased(p_mouseReleased_1_, p_mouseReleased_3_, p_mouseReleased_5_);
	}
	@Override
	public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
		if(p_keyPressed_1_ == GLFW.GLFW_KEY_ESCAPE || p_keyPressed_1_ == Core.instance.getModuleManager().getModule(ClickGui.class).getKey()) {
			this.onClose();
			mc.gameRenderer.getShaderGroup().close();
			return true;
		}
		this.getPanels().stream().forEach(panel ->{panel.keyPressed(p_keyPressed_1_);});
		return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
	}
	
	@Override
	public boolean isPauseScreen() {
		return false;
	}

}
