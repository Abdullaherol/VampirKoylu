package com.olympos.tom.handler;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.olympos.tom.Main;
import com.olympos.tom.lobby.Lobby;
import com.olympos.tom.map.Map;
import com.olympos.tom.roles.Roles;

public class MainHandler implements Listener{
	private Main plugin;
	
	public MainHandler(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent event) {
		
		if(event.getClickedInventory()==null) return;
		if(event.getCurrentItem()==null) return;
		Player player = (Player)event.getWhoClicked();
		
		event.setCancelled(true);
		switch (event.getClickedInventory().getTitle()) {
		case "Town of Minecraft":
			if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Your Lobbies")) {
				if (plugin.getLobbies().get(player).size()!=0) {
					plugin.getLobbyGui().openYourLobbies(player);
				}else plugin.getLobbyGui().openCreateLobby(player);
			}else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Join Lobby")) {
				plugin.getLobbyGui().openJoinLobbies(player);
			}else {
				
			}
			break;
		case "Your Lobbies":
			Lobby lobbies = plugin.getLobbies().get(player).get(event.getSlot());
			plugin.getLobbyGui().openCreateLobby(player, lobbies);
			break;
		case "Profile":
			
			break;
		case "Join Lobby":
			if (event.getClick()==ClickType.LEFT) {
				//join this lobby
				Lobby lobby = plugin.getReadyLobbies().get(event.getSlot());
				lobby.playerJoin(player);
			}else if (event.getClick()==ClickType.RIGHT) {
				//open players in the lobby
			
			}
			break;
		default:
			if (event.getClickedInventory().getTitle().contains("Create Lobby")) {
				int index = Integer.valueOf(event.getClickedInventory().getTitle().split("Create Lobby ")[1]);
				Lobby lobby = plugin.getLobbies().get(player).get(index);
				if (event.getCurrentItem().getType()==Material.GOLD_RECORD) {
					Roles role = Roles.valueOf(event.getCurrentItem().getItemMeta().getDisplayName());
					if (lobby.getSelectedRoles().contains(role)) {
						lobby.getSelectedRoles().remove(role);
					}else lobby.getSelectedRoles().add(role);
					plugin.getLobbyGui().openCreateLobby(player, lobby);
				}else if (event.getCurrentItem().getType()==Material.TOTEM) {
					lobby.setSize(Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName()));
					plugin.getLobbyGui().openCreateLobby(player, lobby);
				}else if (event.getCurrentItem().getType()==Material.STAINED_GLASS_PANE) {
					if (event.getCurrentItem().getItemMeta().getDisplayName()!=null) {
						if (lobby.getSelectedRoles().size()!=lobby.getSize()) {
							player.sendMessage(ChatColor.RED+"Selected roles must have the same Lobby size!");
						}else if (lobby.getMap()==null) {
							player.sendMessage(ChatColor.RED+"You have to choose a map!");
						}else {
							ArrayList<Roles> mustRoles = new ArrayList<Roles>();
							mustRoles.add(Roles.Investigator);
							mustRoles.add(Roles.Doctor);
							mustRoles.add(Roles.Jailor);
							mustRoles.add(Roles.Medium);
							mustRoles.add(Roles.Escort);
							mustRoles.add(Roles.Godfather);
							mustRoles.add(Roles.Mafia);
							mustRoles.add(Roles.Jester);
							if (lobby.getSelectedRoles().containsAll(mustRoles)) {
								lobby.getPlayers().put(player, null);
								plugin.getReadyLobbies().add(lobby);
								lobby.setReady(true);
								player.closeInventory();
							}else player.sendMessage(ChatColor.RED+"You have to select these roles: \n"
													+ChatColor.GREEN+ "Investigator,Doctor,Jailor,Medium,Escort,"
													+ChatColor.RED+"Godfather,Mafia,"+ChatColor.GRAY+"Jester.");
							
						}
					}
				}else if (event.getCurrentItem().getType()==Material.EMPTY_MAP) {
					Map map = plugin.getMaps().get(event.getCurrentItem().getItemMeta().getDisplayName());
					lobby.setMap(map);
					plugin.getLobbyGui().openCreateLobby(player, lobby);
				}
			}
			break;
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!plugin.getLobbies().containsKey(event.getPlayer())) {
			plugin.getLobbies().put(event.getPlayer(), new ArrayList<Lobby>());
		}
	}

}
