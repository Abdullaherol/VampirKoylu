package com.olympos.tom.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

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
						if (!tPlayer.getActiveLobby().getGameManager().isNight()) {
							Inventory inventory = Bukkit.createInventory(null, 18,"Jailor");
							for (TPlayer tPlayer2 : tPlayer.getActiveLobby().getPlayers().values()) {
								if (!tPlayer2.getRole().isDead()) {
									if (tPlayer.getRole().getTargetPlayer()==tPlayer2) {
										ItemStack itemStack = createItem(tPlayer2.getPlayer().getName(), 1, Material.SKULL_ITEM, true, tPlayer2.getPlayer().getName());
										inventory.addItem(itemStack);
									}else {
										ItemStack itemStack = createItem(tPlayer2.getPlayer().getName(), 1, Material.SKULL_ITEM, false, tPlayer2.getPlayer().getName());
										inventory.addItem(itemStack);
									}
								}
							}
						}
					}else if (arg3.length==1) {
						if (tPlayer.getActiveLobby().getGameManager().isNight()) {
							if (tPlayer.getRole().getTargetPlayer()!=null) {
								String value = arg3[0];
								if (value.equalsIgnoreCase("kill")) {
									if (tPlayer.getRole().getUse()>0) {
										tPlayer.getRole().getTargetPlayer().getRole().setDead(true);
									}else tPlayer.getPlayer().sendMessage(ChatColor.RED+"You don't have enough rights to use your ability.");
								}
							}
						}
					}
					
				}
			}
		}
		return false;
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
