package com.olympos.tom.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;

public class CJailor implements CommandExecutor{

	private Main plugin;
	
	public CJailor(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player player = (Player)arg0;
			if (plugin.getPlayers().containsKey(player)) {
				TPlayer tPlayer = plugin.getPlayers().get(player);
				if (tPlayer.getActiveLobby()!=null) {
					if (arg3.length==0) {
						Inventory inventory = Bukkit.createInventory(null, 27,"Jailor");
						for (TPlayer tPlayer2 : tPlayer.getActiveLobby().getPlayers().values()) {
							
						}
					}else if (arg3.length==1) {
						String value = arg3[0];
					}
					
				}
			}
		}
		return false;
	}
	
	private ItemStack createItem(String name,int amount,Material material,boolean enc) {
		ItemStack itemStack = new ItemStack(material,amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		if(enc) {
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		}
		itemStack.setItemMeta(itemMeta);
		return itemStack;
		
	}
	
	

}
