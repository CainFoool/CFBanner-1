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
public class CFBanner extends JavaPlugin implements Listener
{
	//Start setting variables
	Logger log = Logger.getLogger("Minecraft");
	//End setting variables
	//Start enable code
	public void onEnable()
	{
		if(getConfig().getBoolean("enabled") == true)
		{
			getConfig().options().copyDefaults(true);
			if(this.getConfig().getBoolean("autoUpdate") == true)
			{
				Updater updater = new Updater(this, getName().toLowerCase(), getFile(), Updater.UpdateType.DEFAULT, true);
			}
			saveConfig();
			//Register the Listener
			getServer().getPluginManager().registerEvents(this, this);
			try
			{
				Metrics metrics = new Metrics(this);
				metrics.start();
			}
			catch (IOException e)
			{
				log.severe("CFBanner: Could not load Metrics!");
			}
				//TODO: On enable
			}
			else
			{
				//PluginManager
				PluginManager pluginManager = Bukkit.getServer().getPluginManager();
				//Oh no!
				log.info("CFBanner: Plugin not enabled! Check config.yml to enable!");
				//Disable me
				pluginManager.disablePlugin(this);
			}
	}
	//End enable code
	
	//Start disable code
	public void onDisable()
	{
		//TODO: On disable
	}
	//End disable code
	//Listener code
	String ZOMBE_FLY_CODE = "¤f ¤f ¤1 ¤0 ¤2 ¤4 ¤3 ¤9 ¤2 ¤0 ¤0 ¤1";
	String ZOMBE_CHEAT_CODE = "¤f ¤f ¤2 ¤0 ¤4 ¤8 ¤3 ¤9 ¤2 ¤0 ¤0 ¤2";
	String CJB_CODE = "¤3 ¤9 ¤2 ¤0 ¤0 ¤0";
	String REI_CODE = "¤0¤0¤1¤2¤3¤4¤5¤6¤7¤e¤f";
	
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerJoin(final PlayerJoinEvent event)
	{
		Player thePlayer = event.getPlayer();
		if (!thePlayer.hasPermission("cfbanner.fly"))
		{
			thePlayer.sendMessage(this.ZOMBE_FLY_CODE);;
		} 
		if (!thePlayer.hasPermission("cfbanner.xray"))
		{
			thePlayer.sendMessage(this.ZOMBE_CHEAT_CODE);
		}
		if (!thePlayer.hasPermission("cfbanner.cjb"))
		{
			thePlayer.sendMessage(this.CJB_CODE);
		}
		if (thePlayer.hasPermission("cfbanner.minimap"))
		{
			thePlayer.sendMessage(this.REI_CODE);
		}
		if(getConfig().getBoolean("enabled") == true && getConfig().getBoolean("showRunningCFBanner") == true)
		{
			thePlayer.sendMessage(getConfig().getString("runningCFBannerMessage"));
		}
	}	
	//End listener code
}
//End class