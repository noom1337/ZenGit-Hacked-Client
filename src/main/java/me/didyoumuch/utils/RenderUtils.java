package me.didyoumuch.utils;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.screen.Screen;

public class RenderUtils {
	public static void renderOutlinedRect(int x, int y, int x1, int y1, int color, int outlineColor) {
		Screen.fill(x, y, x1, y1, color);
		
		Screen.fill(x, y, x+1, y1, outlineColor);
		Screen.fill(x, y, x1, y-1, outlineColor);
		Screen.fill(x, y1-1, x1, y1, outlineColor);
		Screen.fill(x1, y, x1-1, y1, outlineColor);
		
		
	}

}
