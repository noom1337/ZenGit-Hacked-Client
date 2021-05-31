package me.didyoumuch.hooks;


import java.util.function.IntSupplier;

import me.didyoumuch.Core;
import net.minecraft.profiler.DebugProfiler;
import net.minecraft.profiler.Profiler;

public class ProfilerHook extends DebugProfiler{
	public ProfilerHook(IntSupplier currentTicks) {
		super(currentTicks);
	}
	
	@Override
	public void startSection(String name) {
		if(name.contains("tick")) {
			Core.instance.onUpdate();
			Core.instance.onKey();
		}
		if(name.contains("bossHealth")) {
			Core.instance.onRender2d();
		}

		super.startSection(name);

		
	}
	
	@Override
	public void endStartSection(String name) {
		if(name.contains("hand")) {
			Core.instance.onRender3d();
		}
		super.endStartSection(name);
	}

}
