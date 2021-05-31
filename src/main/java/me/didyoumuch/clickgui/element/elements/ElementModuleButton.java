package me.didyoumuch.clickgui.element.elements;

import java.awt.Color;

import org.lwjgl.glfw.GLFW;

import me.didyoumuch.Core;
import me.didyoumuch.clickgui.element.AbstractElement;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.utils.settings.AbstractSetting;
import me.didyoumuch.utils.settings.BoolSetting;
import me.didyoumuch.utils.settings.FloatSetting;
import net.minecraft.client.gui.screen.Screen;

public class ElementModuleButton extends AbstractElement{
	private AbstractModule module;
	private boolean binding = false;
	public ElementModuleButton(int x, int y, int width, int height, AbstractModule module) {
		super(x,y,width,height);
		this.module = module;
		
		for(AbstractSetting s : this.getModule().getSettings()) {
			if(s instanceof BoolSetting) {
				this.getSub().add(new ElementBool(x, y, width, height, (BoolSetting)s));
			}
			if(s instanceof FloatSetting) {
				this.getSub().add(new ElementFloat(x, y, width, height, (FloatSetting)s));
			}
		}
	}
	@Override
	public void onRender(int mouseX, int mouseY) {
		Screen.fill(this.getX(), this.getY(), this.getX() +  this.getWidth(), this.getY() + this.getHeight(), this.module.isEnabled() ? new Color(0,0,0).getRGB() : new Color(255,255,255).getRGB());
		
		if(this.isBinding()) {
			String bindingString = "Press a key...";
			mc.fontRenderer.drawString(bindingString, this.getX()+this.getWidth()/2-mc.fontRenderer.getStringWidth(bindingString)/2, this.getY()+mc.fontRenderer.FONT_HEIGHT/2, this.module.isEnabled() ? new Color(255,255,255).getRGB() : new Color(0,0,0).getRGB());

		}
		else {
			String moduleString;
			if(this.getModule().getKey() == 0 || GLFW.glfwGetKeyName(this.getModule().getKey(), 0) == null) {
				moduleString = this.getModule().getName();
			}
			else {
				moduleString = this.getModule().getName() + " | " + GLFW.glfwGetKeyName(this.getModule().getKey(), 0).toUpperCase();
			}
			mc.fontRenderer.drawString(moduleString, this.getX()+this.getWidth()/2-mc.fontRenderer.getStringWidth(moduleString)/2, this.getY()+mc.fontRenderer.FONT_HEIGHT/2, this.module.isEnabled() ? new Color(255,255,255).getRGB() : new Color(0,0,0).getRGB());
			
		}
		
		int hgh = 0;
        for (AbstractElement e : this.getSub())
        {
            e.setX(this.getX());
            e.setY(this.getY() + this.getHeight() + hgh);
            hgh += e.getHeight();
        }
		
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.onRender(mouseX, mouseY);});
		}
		
		super.onRender(mouseX, mouseY);
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(this.isHovered(mouseX, mouseY)) {
			if(button == 0) {
				this.getModule().setEnabled(!this.getModule().isEnabled());
			}
			if(button == 1) {
				this.setOpen(!this.isOpen());
			}
			if(button == 2) {
				for(ElementPanel panel : Core.instance.getClickGui().getPanels()) {
					for(AbstractElement btn : panel.getSub()) {
						((ElementModuleButton)btn).setBinding(false);
					}
				}
				this.setBinding(true);
			}
		}
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.mouseClicked(mouseX, mouseY, button);});
		}
		super.mouseClicked(mouseX, mouseY, button);
	}
	@Override
	public void keyPressed(int keyCode) {
		if(this.isBinding()) {
			if(keyCode == GLFW.GLFW_KEY_DELETE) {
				this.getModule().setKey(0);
			}
			else {
				this.getModule().setKey(keyCode);
			}
			this.setBinding(false);

		}
		super.keyPressed(keyCode);
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int button) {
		
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.mouseReleased(mouseX, mouseY, button);});
		}
		
		super.mouseReleased(mouseX, mouseY, button);
	}
	
	public boolean isBinding() {
		return binding;
	}
	public void setBinding(boolean binding) {
		this.binding = binding;
	}
	public AbstractModule getModule() {
		return module;
	}

}
