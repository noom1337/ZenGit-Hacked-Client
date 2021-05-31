package me.didyoumuch.utils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import me.didyoumuch.Core;
import me.didyoumuch.hooks.ProfilerHook;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

public class ReflectionUtils {
	public static ReflectionUtils instance = new ReflectionUtils();

	private Timer timer;
	public double getTimerTickLenght() {
		try {
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			Field tickLenghtField = this.getTimer().getClass().getDeclaredFields()[4];
			tickLenghtField.setAccessible(true);
			modifiersField.setInt(tickLenghtField, tickLenghtField.getModifiers() & ~Modifier.FINAL);
			return (double)tickLenghtField.getDouble(this.getTimer());
		}catch (Exception e) {
			Core.instance.getLogger().log("Cannot get timer tick lenght");
			
		}
		return 0;
	}

	public int getMcDebugFPS() {
		try {
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			Field fps = Minecraft.class.getDeclaredField("debugFPS");
			fps.setAccessible(true);
			return fps.getInt(Minecraft.getInstance());
		}catch (Exception e) {
			Core.instance.getLogger().log("Cannot get mc debug fps");
		}
		return 0;
	}
	
	public void hookTimer() {
		try{
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			Field profilerField = Minecraft.class.getDeclaredFields()[66];
			profilerField.setAccessible(true);
			modifiersField.setInt(profilerField, profilerField.getModifiers() & ~Modifier.FINAL);
			ProfilerHook profiler = new ProfilerHook(() -> {
				return this.timer.elapsedTicks;
			});
			profilerField.set(Minecraft.getInstance(), profiler);
		}catch (Exception e) {
			Core.instance.getLogger().log("Cannot hook timer");
		}
		
	}
	public void hookProfiler() {
		try {
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			Field timerField = Minecraft.class.getDeclaredFields()[20];
			timerField.setAccessible(true);
			modifiersField.setInt(timerField, timerField.getModifiers() & ~Modifier.FINAL);
			this.timer = (Timer) timerField.get(Minecraft.getInstance());
		}catch (Exception e) {
			Core.instance.getLogger().log("Cannot hook profiler");
		}
	}
	
	public Timer getTimer() {
		return timer;
	}
}
