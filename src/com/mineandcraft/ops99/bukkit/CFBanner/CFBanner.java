package com.mineandcraft.ops99.bukkit.CFBanner;
//Imports
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//End imports
/**
 * The CFBanner plugin. You can find more information <a href="http://dev.bukkit.org/server-mods/cfbanner">here</a>.
 * @author opspwns, CainFool
 */
//Start class
public class CFBanner extends JavaPlugin {
	//Start setting variables
	public static boolean enabled = false;
	Logger log = Logger.getLogger("Minecraft");
	//End setting variables
	//Start enable code
	public void onEnable(){
		if(this.getConfig().getBoolean("enabled") == true)
		{
		this.getConfig().options().copyDefaults(true);
		if(this.getConfig().getBoolean("autoUpdate") == true){
			Updater updater = new Updater(this, this.getName().toLowerCase(), this.getFile(), Updater.UpdateType.DEFAULT, true);
		}
		this.saveConfig();
		//Register the Listener
		getServer().getPluginManager().registerEvents(new CFBannerListener(this), this);
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    log.severe("CFBanner: Could not load Metrics!");
		}
		//Print stuff
		log.info("====================");
		log.info("  CFBanner Enabled  ");
		log.info("   Made by opspwns  ");
		log.info("   and CainFoool.   ");
		log.info("====================");
		}
		else
		{
		//Print stuff
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		pluginManager.disablePlugin(this);
		log.info("CFBanner: Plugin not enabled! Check config.yml to enable!");
		log.info("CFBanner: Disabled!");
		}
	}
	//End enable code
	
	//Start disable code
	public void onDisable(){
		//Print stuff
		log.info("===================");
		log.info(" CFBanner Disabled ");
		log.info("     Thanks for    ");
		log.info("   using CFBanner! ");
		log.info("====================");
	}
	//End disable code
}
//End class