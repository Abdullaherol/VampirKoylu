package com.olympos.tom.handler;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.material.Door;

import com.olympos.tom.Main;
import com.olympos.tom.lobby.GameManager;
import com.olympos.tom.lobby.Lobby;
import com.olympos.tom.map.Map;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.roles.ARole;

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
		TPlayer tPlayer = plugin.getPlayers().get(player);
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
				lobby.playerJoin(tPlayer);
			}else if (event.getClick()==ClickType.RIGHT) {
				//open players in the lobby
			
			}
			break;
		case "Jailor":
			Player targetPlayer = Bukkit.getPlayer(event.getCurrentItem().getItemMeta().getDisplayName());
			TPlayer target = tPlayer.getActiveLobby().getPlayers().get(targetPlayer);
			if (tPlayer.getRole().getTargetPlayer()!=null && tPlayer.getRole().getTargetPlayer()!=target) {
				tPlayer.getRole().getTargetPlayer().getRole().setJailed(false);
				tPlayer.getRole().setTargetPlayer(target);
				target.getRole().setJailed(true);
			}else {
				tPlayer.getRole().setTargetPlayer(target);
				target.getRole().setJailed(true);
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
	
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		TPlayer tPlayer = plugin.getPlayers().get(player);
		if (tPlayer.getActiveLobby()!=null) {
			Lobby lobby = tPlayer.getActiveLobby();
			event.setCancelled(true);
			GameManager gameManager = lobby.getGameManager();
			ARole role = tPlayer.getRole();
			
			if (!tPlayer.getRole().isDead()) {
				if (gameManager.isNight()) {
					//jailor
					//medium
					//mafia
					//vampir
					if (!role.isJailed()) {
						if (role.getChat()==Chat.medium) {
							for (Player eachPlayer : lobby.getPlayers().keySet()) {
								TPlayer Tplayer = lobby.getPlayers().get(eachPlayer);
								if (Tplayer.getRole().getChat()==Chat.dead) {
									eachPlayer.sendMessage("Medium: "+event.getMessage());
								}
							}
						}else if (role.getChat()==Chat.mafia) {
							for (Player eachPlayer : lobby.getPlayers().keySet()) {
								TPlayer Tplayer = lobby.getPlayers().get(eachPlayer);
								if (Tplayer.getRole().getChat()==Chat.mafia) {
									eachPlayer.sendMessage(player.getName()+"("+tPlayer.getRole().getRole().toString()+"): "+event.getMessage());
								}else if (tPlayer.getRole().getChat()==Chat.spy) {
									eachPlayer.sendMessage(tPlayer.getRole().getRole().toString()+": "+event.getMessage());
								}
							}
						}else if (role.getChat()==Chat.vampir) {
							for (Player eachPlayer : lobby.getPlayers().keySet()) {
								TPlayer Tplayer = lobby.getPlayers().get(eachPlayer);
								if (Tplayer.getRole().getChat()==Chat.vampir) {
									eachPlayer.sendMessage(player.getName()+"("+tPlayer.getRole().getRole().toString()+"): "+event.getMessage());
								}
							}
						}else if (role.getChat()==Chat.jailor) {
							for (Player eachPlayer : lobby.getPlayers().keySet()) {
								TPlayer Tplayer = lobby.getPlayers().get(eachPlayer);
								if (Tplayer.getRole().isJailed()) {
									eachPlayer.sendMessage(eachPlayer.getName()+"("+tPlayer.getRole().getRole().toString()+"): "+event.getMessage());
								}
							}
						}
					}else {
						for (Player eachPlayer : lobby.getPlayers().keySet()) {
							TPlayer Tplayer = lobby.getPlayers().get(eachPlayer);
							if (Tplayer.getRole().getRole()==Roles.Jailor) {
								eachPlayer.sendMessage(eachPlayer.getName()+": "+event.getMessage());
							}
						}
					}
				}else {
					for (Player eachPlayer : lobby.getPlayers().keySet()) {
						eachPlayer.sendMessage(player.getName()+": "+event.getMessage());
					}
				}
			}else {
				for (Player  eachPlayer: lobby.getPlayers().keySet()) {
					TPlayer Tplayer = lobby.getPlayers().get(eachPlayer);
					if (gameManager.isNight()) {
						if (Tplayer.getRole().getChat()==Chat.dead || Tplayer.getRole().getChat()==Chat.medium) {
							eachPlayer.sendMessage("Dead Player: "+event.getMessage());
						}
					}else {
						
					}
				}
			}
		}
	}
	
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (!player.isOp()) {
			event.setCancelled(true);
		}
	}
	public void onPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (!player.isOp()) {
			event.setCancelled(true);
		}
	}
	//on click to door
	public void onClickBlock(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getClickedBlock().getType().toString().contains("door")) {
			TPlayer tPlayer = plugin.getPlayers().get(player);
			if (tPlayer.getActiveLobby()!=null) {
				if (!tPlayer.getRole().isDead()) {
					if (tPlayer.getActiveLobby().getGameManager().isNight()) {
						Door door = (Door) event.getClickedBlock().getState().getData();
						for (Door eachDoor : tPlayer.getActiveLobby().getMap().getDoors()) {
							if (door.equals(eachDoor)) {
								int index = tPlayer.getActiveLobby().getMap().getDoors().indexOf(door);
								for (TPlayer eachTPlayer : tPlayer.getActiveLobby().getPlayers().values()) {
									if (eachTPlayer.getRole().getNo()==index) {
										if (!eachTPlayer.getRole().isDead()) {
											if (eachTPlayer.getRole().getRole()==Roles.Doctor) {
												tPlayer.getRole().setTargetPlayer(eachTPlayer);
											}else if (eachTPlayer.getRole().getRole()==Roles.Veteran) {
												if (eachTPlayer==tPlayer) {
													tPlayer.getRole().setTargetPlayer(eachTPlayer);
												}
											}else {
												if (tPlayer!=eachTPlayer) {
													tPlayer.getRole().setTargetPlayer(eachTPlayer);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
}

