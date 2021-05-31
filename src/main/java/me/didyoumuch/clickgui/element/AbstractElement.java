package me.didyoumuch.clickgui.element;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class AbstractElement {
	protected Minecraft mc = Minecraft.getInstance();
	private int x,y,width,height;
	private ArrayList<AbstractElement> sub = new ArrayList<AbstractElement>();
	private boolean open = false;
	
	public AbstractElement(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void onRender(int mouseX, int mouseY) {}
	public void mouseClicked(int mouseX, int mouseY, int button) {}
	public void mouseReleased(int mouseX, int mouseY, int button) {}
	public void keyPressed(int keyCode) {}
	public boolean isHovered(int mouseX, int mouseY) {
		if(mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight()) {
			return true;
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}	
	public ArrayList<AbstractElement> getSub() {
		return sub;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
}
