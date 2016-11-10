package core;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Menu implements Listener {
	
	
	
	// Main menu
	private Inventory kits;
	//private Inventory perks;
	private ItemStack k1, k2, k3, k4, k5;

	public Menu(Plugin pl) {

		String menuTitle = Main.getInstance().getConfig().getString("menuTitle");
		menuTitle = menuTitle.replaceAll("&", "§");
		
		int rows = Main.getInstance().getConfig().getInt("rows");
		
		kits = Bukkit.getServer().createInventory(null, rows, menuTitle);
		
		k1 = createItem(Material.DIAMOND_SWORD, ChatColor.YELLOW + "Figther" + ChatColor.GRAY);
		k2 = createItem(Material.BOW, ChatColor.YELLOW + "Archer" + ChatColor.GRAY);
		k3 = createItem(Material.DIAMOND_AXE, ChatColor.YELLOW + "Viking" + ChatColor.GRAY);
		k4 = createItem(Material.ENDER_PEARL, ChatColor.YELLOW + "Enderman" + ChatColor.GRAY);
		k5 = createItem(Material.BARRIER, ChatColor.RED + "NONE" + ChatColor.GRAY);
		
		kits.setItem(0, k1);
		kits.setItem(2, k2);
		kits.setItem(4, k3);
		kits.setItem(6, k4);
		kits.setItem(8, k5);
		
		Bukkit.getServer().getPluginManager().registerEvents(this, pl);
	}
	
	

	private ItemStack createItem(Material mat, String name) {
		ItemStack i = new ItemStack(mat);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		//im.setLore(Arrays.asList(name.toLowerCase() + " kit will be given to you!"));
		i.setItemMeta(im);
		return i;
	}

	public void show(Player p) {
		p.openInventory(kits);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		PlayerInventory pi = p.getInventory();
		
		String invFull = Main.getInstance().getConfig().getString("invFull");
		invFull = invFull.replaceAll("&", "§");
		String kitFailed = Main.getInstance().getConfig().getString("kitFailed");
		kitFailed = kitFailed.replaceAll("&", "§");
		String kitReceived = Main.getInstance().getConfig().getString("kitReceived");
		kitReceived = kitReceived.replaceAll("&", "§");
		
		if (e.getInventory().getName().equalsIgnoreCase(kits.getName())) {
			e.setCancelled(true);
		}
		
		
		
		if (!(e.getInventory().getName().equalsIgnoreCase(kits.getName()))) return;
		
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Figther")) {
			e.setCancelled(true);
			pi.addItem(new ItemStack(Material.IRON_BOOTS));
			pi.addItem(new ItemStack(Material.IRON_LEGGINGS));
			pi.addItem(new ItemStack(Material.DIAMOND_CHESTPLATE));
			pi.addItem(new ItemStack(Material.IRON_HELMET));
			pi.addItem(new ItemStack(Material.DIAMOND_SWORD));
			p.sendMessage(kitReceived);
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Archer")) {
			e.setCancelled(true);
			pi.addItem(new ItemStack(Material.DIAMOND_BOOTS));
			pi.addItem(new ItemStack(Material.IRON_LEGGINGS));
			pi.addItem(new ItemStack(Material.IRON_CHESTPLATE));
			pi.addItem(new ItemStack(Material.DIAMOND_HELMET));
			pi.addItem(new ItemStack(Material.IRON_SWORD));
			pi.addItem(new ItemStack(Material.BOW));
			pi.addItem(new ItemStack(Material.ARROW, 64));
			p.sendMessage(kitReceived);
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Viking")) {
			e.setCancelled(true);
			pi.addItem(new ItemStack(Material.DIAMOND_BOOTS));
			pi.addItem(new ItemStack(Material.IRON_LEGGINGS));
			pi.addItem(new ItemStack(Material.IRON_CHESTPLATE));
			pi.addItem(new ItemStack(Material.IRON_HELMET));
			pi.addItem(new ItemStack(Material.DIAMOND_AXE));
			p.sendMessage(kitReceived);
			e.getWhoClicked().closeInventory();
			
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Enderman")) {
			e.setCancelled(true);
			pi.addItem(new ItemStack(Material.DIAMOND_BOOTS));
			pi.addItem(new ItemStack(Material.DIAMOND_LEGGINGS));
			pi.addItem(new ItemStack(Material.IRON_CHESTPLATE));
			pi.addItem(new ItemStack(Material.DIAMOND_HELMET));
			pi.addItem(new ItemStack(Material.IRON_SWORD));
			pi.addItem(new ItemStack(Material.ENDER_PEARL, 4));
			p.sendMessage(kitReceived);
			e.getWhoClicked().closeInventory();
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("NONE")) {
			e.setCancelled(true);
			p.sendMessage(kitFailed);
			e.getWhoClicked().closeInventory();
		}
		
	}

}

