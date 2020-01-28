package com.olympos.tom.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.olympos.tom.Main;
import com.olympos.tom.lobby.Lobby;
import com.olympos.tom.map.Map;
import com.olympos.tom.roles.Roles;

public class LobbyGui {
	
	private Main plugin;
	
	public LobbyGui(Main plugin) {
		super();
		this.plugin = plugin;
	}

	public void openMainLobby(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27,"Town of Minecraft");
		ItemStack yourLobbies = createItem("Your Lobbies", 1, Material.COMPASS, false);
		ItemStack joinLobby = createItem("Join Lobby", 1, Material.NETHER_STAR, false);
		ItemStack profile = createItem("Profile", 1, Material.SKULL_ITEM, false,(byte)3);
		inventory.setItem(11,yourLobbies);
		inventory.setItem(13,joinLobby);
		inventory.setItem(15,profile);
		player.openInventory(inventory);
	}
	
	public void openYourLobbies(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 27,"Your Lobbies");
		for (int i = 0; i < plugin.getLobbies().get(player).size();i++) {
			ItemStack itemStack = createItem("", 1, Material.STAINED_CLAY, false);
			inventory.addItem(itemStack);
		}
		player.openInventory(inventory);
	}
	
	public void openCreateLobby(Player player) {
		Lobby lobby = new Lobby();
		ArrayList<Lobby> lobbies = new ArrayList<Lobby>();
		lobbies.add(lobby);
		plugin.getLobbies().put(player, lobbies);
		Inventory inventory = Bukkit.createInventory(null, 54,"Create Lobby "+lobbies.indexOf(lobby));
		int a=1 ,i = 0;
		for (Roles role : Roles.values()) {
			ItemStack itemStack;
			if (lobby.getSelectedRoles().contains(role)) {
				itemStack = createItem(role.toString(), 1, Material.GOLD_RECORD, true);
			}else itemStack = createItem(role.toString(), 1, Material.GOLD_RECORD, false);
			if (a!=0 && a%3==0) {
				inventory.setItem(i, itemStack);
				i+=7;
			}else {
				inventory.setItem(i, itemStack);
				i++;
			}
			a++;
			
		}
		
		for (int j = 3; j < 49;) {
			ItemStack itemStack = createItem("", 1, Material.STAINED_GLASS_PANE, false,(byte)15);
			
			inventory.setItem(j, itemStack);
			j +=9;
		}
		for (int j = 5; j < 51;) {
			ItemStack itemStack = createItem("", 1, Material.STAINED_GLASS_PANE, false,(byte)15);
			
			inventory.setItem(j, itemStack);
			j +=9;
		}
		for (int j = 8; j < 54;) {
			if (j==26 || j==35) {
				ItemStack itemStack = createItem("Ready", 1, Material.STAINED_GLASS_PANE, false,(byte)5);
				inventory.setItem(j, itemStack);
			}else {
				ItemStack itemStack = createItem("", 1, Material.STAINED_GLASS_PANE, false,(byte)15);
				inventory.setItem(j, itemStack);
			}
			j +=9;
			
		}
		int l=4;
		for (String map : plugin.getMaps().keySet()) {
			ItemStack itemStack;
			if (lobby.getMap()!=null) {
				if (lobby.getMap().getName().equals(map)) {
					itemStack = createItem(map, 1, Material.EMPTY_MAP, true);
					inventory.setItem(l, itemStack);
				}else {
					itemStack = createItem(map, 1, Material.EMPTY_MAP, false);
					inventory.setItem(l, itemStack);
				}
			}else {
				itemStack = createItem(map, 1, Material.EMPTY_MAP, false);
				inventory.setItem(l, itemStack);
			}
			
			l+=9;
		}
		int s = 8;
		for (int j = 6; j < 53;) {
			if (s<=16) {
				ItemStack itemStack;
				if (lobby.getSize()==s) {
					itemStack = createItem(Integer.toString(s), 1, Material.TOTEM, true);
					inventory.setItem(j, itemStack);
				}else {
					itemStack = createItem(Integer.toString(s), 1, Material.TOTEM, false);
					inventory.setItem(j, itemStack);
				}
				s++;
				if (j!=51) {
					j+=9;
				}else j=7;
			}else j=99;
		}
		
		player.openInventory(inventory);
	}
	
	public void openCreateLobby(Player player,Lobby lobby) {
		Inventory inventory = Bukkit.createInventory(null, 54,"Create Lobby "+plugin.getLobbies().get(player).indexOf(lobby));
		int a=1 ,i = 0;
		for (Roles role : Roles.values()) {
			ItemStack itemStack;
			if (lobby.getSelectedRoles().contains(role)) {
				itemStack = createItem(role.toString(), 1, Material.GOLD_RECORD, true);
			}else itemStack = createItem(role.toString(), 1, Material.GOLD_RECORD, false);
			if (a!=0 && a%3==0) {
				inventory.setItem(i, itemStack);
				i+=7;
			}else {
				inventory.setItem(i, itemStack);
				i++;
			}
			a++;
			
		}
		
		for (int j = 3; j < 49;) {
			ItemStack itemStack = createItem("", 1, Material.STAINED_GLASS_PANE, false,(byte)15);
			
			inventory.setItem(j, itemStack);
			j +=9;
		}
		for (int j = 5; j < 51;) {
			ItemStack itemStack = createItem("", 1, Material.STAINED_GLASS_PANE, false,(byte)15);
			
			inventory.setItem(j, itemStack);
			j +=9;
		}
		for (int j = 8; j < 54;) {
			if (j==26 || j==35) {
				ItemStack itemStack = createItem("Ready", 1, Material.STAINED_GLASS_PANE, false,(byte)5);
				inventory.setItem(j, itemStack);
			}else {
				ItemStack itemStack = createItem("", 1, Material.STAINED_GLASS_PANE, false,(byte)15);
				inventory.setItem(j, itemStack);
			}
			j +=9;
			
		}
		int l=4;
		for (String map : plugin.getMaps().keySet()) {
			ItemStack itemStack;
			if (lobby.getMap()!=null) {
				if (lobby.getMap().getName().equals(map)) {
					itemStack = createItem(map, 1, Material.EMPTY_MAP, true);
					inventory.setItem(l, itemStack);
				}else {
					itemStack = createItem(map, 1, Material.EMPTY_MAP, false);
					inventory.setItem(l, itemStack);
				}
			}else {
				itemStack = createItem(map, 1, Material.EMPTY_MAP, false);
				inventory.setItem(l, itemStack);
			}
			
			l+=9;
		}
		int s = 8;
		for (int j = 6; j < 53;) {
			if (s<=16) {
				ItemStack itemStack;
				if (lobby.getSize()==s) {
					itemStack = createItem(Integer.toString(s), 1, Material.TOTEM, true);
					inventory.setItem(j, itemStack);
				}else {
					itemStack = createItem(Integer.toString(s), 1, Material.TOTEM, false);
					inventory.setItem(j, itemStack);
				}
				s++;
				if (j!=51) {
					j+=9;
				}else j=7;
			}else j=99;
		}
		
		player.openInventory(inventory);
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
	private ItemStack createItem(String name,int amount,Material material,boolean enc,byte type) {
		ItemStack itemStack = new ItemStack(material,amount,type);
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
