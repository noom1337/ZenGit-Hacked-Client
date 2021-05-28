package me.didyoumuch.clickgui.panel.element;

import java.util.ArrayList;

import me.didyoumuch.clickgui.panel.Panel;
import me.didyoumuch.module.AbstractModule;
import net.minecraft.client.Minecraft;

public class AbstractElement {
	private int x, y, width, height;
	private Panel panel;
	private boolean open;
	private ArrayList<AbstractElement> elements = new ArrayList<AbstractElement>();
	protected Minecraft mc = Minecraft.getInstance();

	public AbstractElement(int x, int y, int width, int height, Panel panel) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.panel = panel;
	}
	
	public ArrayList<AbstractElement> getElements() {
		return elements;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public Panel getPanel() {
		return panel;
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
	
	public void onRender() {}
	public void mouseClicked(int mouseX, int mouseY, int key) {}
	public void mouseReleased(int mouseX, int mouseY, int key) {}
	public void onKey(int keyCode) {}
	public boolean isHovered(int mouseX, int mouseY) {
		if(mouseX > this.x && mouseX < this.x + this.width && mouseY > this.y && mouseY < this.y + this.height)return true;
		return false;
	}
}
