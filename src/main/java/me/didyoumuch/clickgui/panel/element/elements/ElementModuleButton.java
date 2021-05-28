package me.didyoumuch.clickgui.panel.element.elements;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.didyoumuch.clickgui.panel.Panel;
import me.didyoumuch.clickgui.panel.element.AbstractElement;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.utils.RenderUtils;
import net.minecraft.client.gui.screen.Screen;

public class ElementModuleButton extends AbstractElement{
	private AbstractModule module;
	private ElementModuleButton moduleButton;
	public ElementModuleButton(int x, int y, int width, int height, Panel panel, AbstractModule module) {
		super(x, y, width, height, panel);
		this.moduleButton = this;
		this.module = module;
	}
	
	public AbstractModule getModule() {
		return module;
	}
	
	public ElementModuleButton getModuleButton() {
		return moduleButton;
	}
	@Override
	public void onRender() {
		Screen.fill(this.getX(), this.getY(), this.getX() +  this.getWidth(), this.getY() + this.getHeight(), this.module.isEnabled() ? new Color(0,0,0).getRGB() : new Color(255,255,255).getRGB());
		mc.fontRenderer.drawString(this.module.getName(), this.getX()+this.getWidth()/2-mc.fontRenderer.getStringWidth(this.module.getName())/2, this.getY()+mc.fontRenderer.FONT_HEIGHT/2, this.module.isEnabled() ? new Color(255,255,255).getRGB() : new Color(0,0,0).getRGB());
		super.onRender();
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int key) {
		if(this.isHovered(mouseX, mouseY)) {
			if(key == 0) {
				this.module.setEnabled(!this.module.isEnabled());
			}
			
		}
		super.mouseClicked(mouseX, mouseY, key);
	}

}
