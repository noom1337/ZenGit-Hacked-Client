package me.didyoumuch.clickgui.panel;

import java.awt.Color;
import java.util.ArrayList;

import me.didyoumuch.Core;
import me.didyoumuch.clickgui.panel.element.AbstractElement;
import me.didyoumuch.clickgui.panel.element.elements.ElementModuleButton;
import me.didyoumuch.module.AbstractModule;
import me.didyoumuch.module.Category;
import me.didyoumuch.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;

public class Panel {
	private int x, y, width, height, dragX, dragY;
	private ArrayList<AbstractElement> elements = new ArrayList<AbstractElement>();
	private Category category;
	private boolean open, drag;
	protected Minecraft mc = Minecraft.getInstance();

	public Panel(int x, int y, int width, int height, Category category) {
		this.x = x;
		this.y = y;
		this.category = category;
		this.width = width;
		this.height = height;

		int yOffset = this.height;
		for(AbstractModule module : Core.instance.getModuleManager().getModules()) {
			if(module.getCategory() == this.category) {
				int elementWidth = this.width;
				int elementHeight = this.height;
				
				ElementModuleButton button = new ElementModuleButton(this.x, this.y+yOffset, elementWidth, elementHeight, this, module);
				elements.add(button);
				yOffset+= elementHeight;
			}
		}
	}
	
	public int getDragX() {
		return dragX;
	}
	public int getDragY() {
		return dragY;
	}
	
	public boolean isDrag() {
		return drag;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public ArrayList<AbstractElement> getElements() {
		return elements;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void onRender(int x, int y) {
		if(isDrag()) {
			this.x = x + dragX;
			this.y = y + dragY;
		}
		
        int maxX = (int) (mc.mainWindow.getScaledWidth() - this.getWidth());
        int maxY = (int) (mc.mainWindow.getScaledHeight() - this.getHeight());
        if(this.x >= maxX) {
            this.x = maxX;
        }
        if(this.x <= 0) {
            this.x = 0;
        }
        if(this.y <= 0) {
            this.y = 0;
        }
        if(this.y >= maxY) {
            this.y = maxY;
        }
        RenderUtils.renderOutlinedRect(this.x, this.y, this.x + this.width, this.y + this.height, this.isOpen() ? new Color(255,255,255).getRGB() : new Color(0,0,0).getRGB(), this.isOpen() ? new Color(0,0,0).getRGB() : new Color(255,255,255).getRGB());
		mc.fontRenderer.drawString(this.category.name(), this.x+this.width/2-mc.fontRenderer.getStringWidth(this.category.name())/2, this.y+mc.fontRenderer.FONT_HEIGHT/2, this.isOpen() ? new Color(0,0,0).getRGB() : new Color(255,255,255).getRGB());
		if(this.isOpen()) {
			this.elements.stream().forEach(e ->{
				e.onRender();

			});
		}
		int height1 = 0;
		for(AbstractElement e : this.elements) {
			e.setX(e.getPanel().getX());
			e.setY(e.getPanel().getY() + e.getPanel().getHeight() + height1);
			height1 += e.isOpen() ? e.getElements().size() * 10 + e.getHeight() : e.getHeight();
		}
		

	}
	public void mouseClicked(int mouseX, int mouseY, int key) {
		if(this.isHovered(mouseX, mouseY)) {
			if(key == 1) {
				this.open = !this.open;
			}
			if(key == 0) {
				this.drag = true;
				this.dragX = this.x - mouseX;
				this.dragY = this.y - mouseY;
			}
		}

		if(this.isOpen()) {
			this.elements.stream().forEach(e ->{e.mouseClicked(mouseX, mouseY, key);});
		}
	}
	public void mouseReleased(int mouseX, int mouseY, int key) {
		this.drag = false;
		if(this.isOpen()) {
			this.elements.stream().forEach(e ->{e.mouseReleased(mouseX, mouseY, key);});
		}
		
	}
	public boolean isHovered(int mouseX, int mouseY) {
		if(mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height)return true;
		return false;
	}
	public void onKey(int keyCode) {
		if(this.isOpen()) {
			this.elements.stream().forEach(e ->{e.onKey(keyCode);});
		}
	}
}
