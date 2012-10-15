package com.mineandcraft.ops99.bukkit.CFBanner;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CFBannerListener implements Listener {
	CFBanner thePlugin;
	public CFBannerListener(CFBanner plInstance){
		thePlugin = plInstance;
	}
	String ZOMBE_FLY_CODE = "¤f ¤f ¤1 ¤0 ¤2 ¤4 ¤3 ¤9 ¤2 ¤0 ¤0 ¤1";
	String ZOMBE_CHEAT_CODE = "¤f ¤f ¤2 ¤0 ¤4 ¤8 ¤3 ¤9 ¤2 ¤0 ¤0 ¤2";
	String CJB_CODE = "¤3 ¤9 ¤2 ¤0 ¤0 ¤0";
	String REI_CODE = "¤0¤0¤1¤2¤3¤4¤5¤6¤7¤e¤f";
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(final PlayerJoinEvent event) {
		Player thePlayer = event.getPlayer();
		if(thePlugin.getConfig().getBoolean("enabled") == true && thePlugin.getConfig().getBoolean("showRunningCFBanner") == true){
			String message = thePlugin.getConfig().getString("runningCFBannerMessage");
			thePlayer.sendMessage(message);
		}
		String message = "";
		
		if (!thePlayer.hasPermission("cfbanner.fly")) {
			message = message + this.ZOMBE_FLY_CODE;
		} 
		if (!thePlayer.hasPermission("cfbanner.xray")) {
			message = message + this.ZOMBE_CHEAT_CODE;
		}
		if (!thePlayer.hasPermission("cfbanner.cjb")) {
			message = message + this.CJB_CODE;
		}
		if (thePlayer.hasPermission("cfbanner.minimap")) {
			message = message + this.REI_CODE;
		}
		thePlayer.sendMessage(message);
	}	
}