package me.didyoumuch;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.didyoumuch.module.ModuleManager;
import me.didyoumuch.utils.EventHandler;

import java.util.stream.Collectors;

@Mod("zengit")
public class Core
{
	
	public static Core instance;
	private ModuleManager moduleManager;
	
    public Core() {
    	Core.instance = this;
    	this.moduleManager = new ModuleManager();
    	
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
    public ModuleManager getModuleManager() {
		return moduleManager;
	}
}
