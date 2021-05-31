package me.didyoumuch.utils;

import org.lwjgl.opengl.GL11;

import me.didyoumuch.Core;
import me.didyoumuch.utils.reflection.ReflectionUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;

public class RenderUtils {
	private Minecraft mc = Minecraft.getInstance();
	public static void renderOutlinedRect(int x, int y, int x1, int y1, int color, int outlineColor) {
		Screen.fill(x, y, x1, y1, color);
		
		Screen.fill(x, y, x+1, y1, outlineColor);
		Screen.fill(x, y, x1, y-1, outlineColor);
		Screen.fill(x, y1-1, x1, y1, outlineColor);
		Screen.fill(x1, y, x1-1, y1, outlineColor);
		
		
	}
	
    public static void drawV(double x, double y, int x1, int y2, int color) {
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);

        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d((x+x1)/2, y2);
        
        GL11.glVertex2d((x+x1)/2, y2);
        GL11.glVertex2d(x1, y);
        
        GL11.glEnd();
    }
    
    public static void drawCircle(int x, int y, int radius, int color) {
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_POLYGON);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(x + Math.sin((double)i * 3.141526 / 180.0) * (double)radius, y + Math.cos((double)i * 3.141526 / 180.0) * (double)radius);
        }
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void drawCheckBox(int x, int y, boolean state, int lineColor, int circleColor) {
    	Screen.fill(x, y, x+10, y+1, lineColor);
    	int offset = 0;
//    	if(state) {
//    		drawCircle(x+10, y, 2, circleColor);
//    	}
//    	else {
//    		drawCircle(x, y, 2, circleColor);
//    	}
    	if(state) {
    		offset++;
    		if(offset >= 10) {
    			offset = 0;
    		}
    	}
    	
    	drawCircle(x+offset, y, 2, circleColor);
    	
    }
    
    public static float perSecondSpeed(float speed) {
        return (float)(ReflectionUtils.instance.getTimerTickLenght() / ReflectionUtils.instance.getMcDebugFPS() * speed);
     }

}
