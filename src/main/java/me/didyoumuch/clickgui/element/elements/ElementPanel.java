package me.didyoumuch.clickgui.element.elements;

import java.awt.Color;
import java.util.ArrayList;

import me.didyoumuch.Core;
import me.didyoumuch.clickgui.element.AbstractElement;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.module.Category;
import me.didyoumuch.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;

public class ElementPanel extends AbstractElement{
	private Category category;
	private boolean drag;
	private int dragX, dragY;
	public ElementPanel(int x, int y, int width, int height, Category category) {
		super(x,y,width, height);
		this.category = category;
		this.drag = false;
		int yOff = this.getHeight();
		for(AbstractModule module : Core.instance.getModuleManager().getModules()) {
			if(module.getCategory() == category) {
				int elementWidth = this.getWidth();
				int elementHeight = this.getHeight();
				this.getSub().add(new ElementModuleButton(this.getX(), this.getY()+yOff, elementWidth, elementHeight, module));
				yOff += elementHeight;
			}
		}
	}
	@Override
	public void onRender(int mouseX, int mouseY) {
		if(this.isDrag()) {
			this.setX(mouseX + this.dragX);
			this.setY(mouseY + this.dragY);
		}
		Screen.fill(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), new Color(255,255,255,100).getRGB());
		mc.fontRenderer.drawString(this.getCategory().name(), this.getX() + this.getWidth()/2 - mc.fontRenderer.getStringWidth(this.getCategory().name()), this.getY() + mc.fontRenderer.FONT_HEIGHT/2, new Color(0,0,0).getRGB());
		
		
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.onRender(mouseX, mouseY);});
		}
		
		int hgh = 0;
		
        for (AbstractElement e : this.getSub())
        {
            e.setX(this.getX());
            e.setY(this.getY() + this.getHeight() + hgh);
            hgh += e.isOpen() ? e.getSub().size() * 15 + e.getHeight(): e.getHeight();
        }
		
		super.onRender(mouseX, mouseY);
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
		if(this.isHovered(mouseX, mouseY)) {
			if(button == 0) {
				this.setDrag(true);
                this.dragX = this.getX() - mouseX;
                this.dragY = this.getY() - mouseY;
			}
			if(button == 1) {
				this.setOpen(!this.isOpen());
			}
		}
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.mouseClicked(mouseX, mouseY, button);});
		}
		
		super.mouseClicked(mouseX, mouseY, button);
	}
	@Override
	public void mouseReleased(int mouseX, int mouseY, int button) {
		if(button == 0) {
			this.setDrag(false);
		}
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.mouseReleased(mouseX, mouseY, button);});
		}
		
		super.mouseReleased(mouseX, mouseY, button);
	}
	@Override
	public void keyPressed(int keyCode) {
		if(this.isOpen()) {
			this.getSub().stream().forEach(sub ->{sub.keyPressed(keyCode);});
		}
		super.keyPressed(keyCode);
	}
	public Category getCategory() {
		return category;
	}
	public boolean isDrag() {
		return drag;
	}
	public void setDrag(boolean drag) {
		this.drag = drag;
	}
	
}
