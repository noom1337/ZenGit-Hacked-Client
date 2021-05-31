package me.didyoumuch.clickgui.element.elements;

import java.awt.Color;

import me.didyoumuch.clickgui.element.AbstractElement;
import me.didyoumuch.utils.RenderUtils;
import me.didyoumuch.utils.settings.BoolSetting;
import net.minecraft.client.gui.screen.Screen;

public class ElementBool extends AbstractElement {
	private BoolSetting setting;
	public ElementBool(int x, int y, int width, int height, BoolSetting setting) {
		super(x,y,width,height);
		this.setting = setting;
	}
	float offset = 0;
	@Override
	public void onRender(int mouseX, int mouseY) {
        Screen.fill(this.getX(), this.getY(), this.getX()+ this.getWidth(), this.getY() + this.getHeight(), -1);
        mc.fontRenderer.drawString(this.getSetting().getName(), this.getX() + this.getWidth()/2-mc.fontRenderer.getStringWidth(this.getSetting().getName())/2-15, this.getY()+mc.fontRenderer.FONT_HEIGHT/2, 0x000000);
		//RenderUtils.drawCheckBox(this.getX()+this.getWidth()-15, this.getY()+mc.fontRenderer.FONT_HEIGHT/2+3, this.getSetting().getCurrentValue(), new Color(0,0,0).getRGB(), new Color(0,0,0).getRGB());
        
    	Screen.fill(this.getX()+this.getWidth()-16, this.getY()+mc.fontRenderer.FONT_HEIGHT/2+3, this.getX()+this.getWidth()-5, this.getY()+mc.fontRenderer.FONT_HEIGHT/2+4, new Color(0,0,0).getRGB());
    	
//    	if(state) {
//    		drawCircle(x+10, y, 2, circleColor);
//    	}
//    	else {
//    		drawCircle(x, y, 2, circleColor);
//    	}
    	if(this.getSetting().getCurrentValue()) {
    		offset+=RenderUtils.perSecondSpeed(1);
    		if(offset >= 10) {
    			offset = 10;
    		}
    	}
    	else {
    		offset-=RenderUtils.perSecondSpeed(1);
    		if(offset <= 0) {
    			offset = 0;
    		}
    	}
    	RenderUtils.drawCircle((int) (this.getX()+this.getWidth()-15+offset), this.getY()+mc.fontRenderer.FONT_HEIGHT/2+3, 2, new Color(0,0,0).getRGB());
        super.onRender(mouseX, mouseY);
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int button) {
        if (this.isHovered(mouseX, mouseY) && button == 0)
        {
            this.getSetting().setCurrentValue(!this.setting.getCurrentValue());
        }
		super.mouseClicked(mouseX, mouseY, button);
	}
	
	public void setSetting(BoolSetting setting) {
		this.setting = setting;
	}
	public BoolSetting getSetting() {
		return setting;
	}

}
