package me.vik1395.BungeeAuthValidator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener
{
	public static String host, port, dbName, username, pass;
	Tables t;
	
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		host = getConfig().getString("Host");
		port = getConfig().getString("Port");
		username = getConfig().getString("Username");
		pass = getConfig().getString("Password");
		dbName = getConfig().getString("DBName");
		t = new Tables();
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent ple)
	{
		String name = ple.getPlayer().getName();
		
		if(t.checkPlayerEntry(name))
		{
			String status = t.getStatus(name);
			
			if(status.equalsIgnoreCase("online"))
			{
				return;
			}
			else
			{
				ple.disallow(Result.KICK_OTHER, "You have not authenticated with BungeeAuth!");
			}
		}
		else
		{
			ple.disallow(Result.KICK_OTHER, "You have not registered with BungeeAuth!");
		}
	}
}
