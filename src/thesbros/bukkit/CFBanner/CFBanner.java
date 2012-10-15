package thesbros.bukkit.CFBanner;
//Imports
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
//End imports
/**
 * The CFBanner plugin. You can find more information <a href="http://dev.bukkit.org/server-mods/cfbanner">here</a>.
 * @author opspwns, CainFool
 */
//Start class
public class CFBanner extends JavaPlugin implements Listener {
	//Start setting variables
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
		getServer().getPluginManager().registerEvents(this, this);
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
	//Listener code
	String ZOMBE_FLY_CODE = "¤f ¤f ¤1 ¤0 ¤2 ¤4 ¤3 ¤9 ¤2 ¤0 ¤0 ¤1";
	String ZOMBE_CHEAT_CODE = "¤f ¤f ¤2 ¤0 ¤4 ¤8 ¤3 ¤9 ¤2 ¤0 ¤0 ¤2";
	String CJB_CODE = "¤3 ¤9 ¤2 ¤0 ¤0 ¤0";
	String REI_CODE = "¤0¤0¤1¤2¤3¤4¤5¤6¤7¤e¤f";
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(final PlayerJoinEvent event) {
		Player thePlayer = event.getPlayer();
		if(getConfig().getBoolean("enabled") == true && getConfig().getBoolean("showRunningCFBanner") == true){
			String message = getConfig().getString("runningCFBannerMessage");
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
	//End listener code
}
//End class