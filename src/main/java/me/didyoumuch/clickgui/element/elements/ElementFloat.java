package me.didyoumuch.clickgui.element.elements;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import me.didyoumuch.clickgui.element.AbstractElement;
import me.didyoumuch.utils.settings.AbstractSetting;
import me.didyoumuch.utils.settings.FloatSetting;
import net.minecraft.client.gui.screen.Screen;

public class ElementFloat extends AbstractElement {
	private FloatSetting setting;
	private boolean drag;
	public ElementFloat(int x, int y, int width, int height, FloatSetting setting) {
		super(x,y,width,height);
		this.setting = setting;
	}

	
	@Override
	public void onRender(int mouseX, int mouseY) {
		double diff = Math.min(this.getWidth(), Math.max(0, mouseX - this.getX()));
		float renderWidth = (this.getWidth()) * (this.getSetting().getCurrentValue() - this.getSetting().getMin()) / (this.getSetting().getMax() - this.getSetting().getMin());
        if (this.isDrag())
        {
            if (diff == 0)
            {
            	this.getSetting().setCurrentValue(this.getSetting().getMin());
            }
            else
            {
                float newValue = this.round(((diff / this.getWidth()) * (this.getSetting().getMax() - this.getSetting().getMin()) + this.getSetting().getMin()), 2);
                this.getSetting().setCurrentValue(newValue);
            }
        }
        Screen.fill(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), -1);
        Screen.fill(this.getX(), this.getY(), (int)(this.getX() + renderWidth), this.getY() + this.getHeight(), new Color(0, 0, 0).getRGB());
        mc.fontRenderer.drawString(this.getSetting().getName() + " | " + this.getSetting().getCurrentValue(), this.getX()+5, this.getY()+mc.fontRenderer.FONT_HEIGHT/2, new Color(255,255,255).getRGB());
		
		super.onRender(mouseX, mouseY);
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
        if (this.isHovered(mouseX, mouseY))
        {
            this.setDrag(true);
        }
		super.mouseClicked(mouseX, mouseY, button);
	}
	@Override
	public void mouseReleased(int mouseX, int mouseY, int button) {
		this.setDrag(false);
		super.mouseReleased(mouseX, mouseY, button);
	}
	
	public FloatSetting getSetting() {
		return setting;
	}
	public void setSetting(FloatSetting setting) {
		this.setting = setting;
	}

	public boolean isDrag() {
		return drag;
	}
	public void setDrag(boolean drag) {
		this.drag = drag;
	}
    float round(double value, int places)
    {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
