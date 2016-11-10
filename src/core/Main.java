package core;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private Menu kits;
	
	
	
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		kits = new Menu(this);
		Bukkit.getServer().getLogger().info("EzKitz >> Enabled with success!");
	}
	
	private static Main instance;
	
	public static Main getInstance() {
	return instance;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			//sender.sendMessage(ChatColor.DARK_RED + ">> Oh my god... you're not a player D:");
			String consoleExecute = getConfig().getString("consoleExecute");
			consoleExecute = consoleExecute.replaceAll("&", "§");
			sender.sendMessage(consoleExecute);
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("kits")) {
			kits.show(p.getPlayer());
		}
		
		return true;
	}
	
}
