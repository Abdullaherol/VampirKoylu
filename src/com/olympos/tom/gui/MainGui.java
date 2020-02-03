package com.olympos.tom.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;

import net.md_5.bungee.api.ChatColor;

public class MainGui {
	
	private Main plugin;
	
	public MainGui(Main plugin) {
		this.plugin = plugin;
	}

	public void vote(TPlayer tPlayer) {
		Inventory inventory = Bukkit.createInventory(null, 27,"Vote");
		for (Player player: tPlayer.getActiveLobby().getPlayerlist()) {
			if (tPlayer.getRole().getVote()==null) {
				ItemStack itemStack = createItem(player.getName(), 1, Material.SKULL_ITEM, false, player.getName());
				inventory.addItem(itemStack);
			}else {
				ItemStack itemStack = createItem(player.getName(), 1, Material.SKULL_ITEM, true, player.getName());
				inventory.addItem(itemStack);
			}
			tPlayer.getPlayer().openInventory(inventory);
		}
	}
	
	@SuppressWarnings("deprecation")
	private ItemStack createItem(String name,int amount,Material material,boolean enc,String owner) {
		ItemStack itemStack = new ItemStack(material,amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		SkullMeta meta = (SkullMeta)itemMeta;
		meta.setDisplayName(name);
		meta.setOwner(owner);
		if(enc) {
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		}
		itemStack.setItemMeta(meta);
		return itemStack;
		
	}
}
