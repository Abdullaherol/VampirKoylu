package com.olympos.tom.lobby;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;
import com.olympos.tom.roles.ARole;
import com.olympos.tom.roles.Doctor;
import com.olympos.tom.roles.Godfather;
import com.olympos.tom.roles.Investigator;
import com.olympos.tom.roles.Jailor;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class RoleGiver extends BukkitRunnable{

	private Lobby lobby;
	private Main plugin;
	private boolean giver = true;
	private boolean last5 = true;
	private int time = 5;
	
	public RoleGiver(com.olympos.tom.lobby.Lobby lobby, Main plugin) {
		this.lobby = lobby;
		this.plugin = plugin;
		this.runTaskTimer(this.plugin, 20, 20);
	}
	@Override
	public void run() {
		System.out.println("time: "+time+" Players: "+lobby.getPlayers().keySet()+" SelectedRoles: "+lobby.getSelectedRoles());

			if (giver) {
				giver = false;
				int i = 0;
				for (Player eachPlayer : lobby.getPlayers().keySet()) {
					
					TPlayer tPlayer = lobby.getPlayers().get(eachPlayer);
					int random = (int)(Math.random()*lobby.getSelectedRoles().size());
					switch (lobby.getSelectedRoles().get(random)) {
					case Investigator:
						ARole aRole = new Investigator(i, Roles.Investigator, -1, Chat.town, Dead.normal,
								Side.Town, false, false, false, false, null, RoleType.InHome, tPlayer, RoleTime.Night, null, RoleQueue.a4);
						lobby.getSelectedRoles().remove(Roles.Investigator);
						tPlayer.setRole(aRole);
						break;
					case Blackmailer:
						break;
					case Bodyguard:
						break;
					case Consort:
						break;
					case Doctor:
						ARole doctor = new Doctor(i, Roles.Doctor, 1, Chat.town, Dead.normal,
								Side.Town, false, false, false, false, null, RoleType.Visit, tPlayer, RoleTime.Night, null, RoleQueue.a3);
						lobby.getSelectedRoles().remove(Roles.Doctor);
						tPlayer.setRole(doctor);
						break;
					case Escort:
						break;
					case Godfather:
						ARole godfather = new Godfather(i, Roles.Godfather, -1, Chat.mafia, Dead.werewolf,
								Side.Mafia, false, false, false, false, null, RoleType.Visit, tPlayer, RoleTime.Night, null, RoleQueue.a5);
						lobby.getSelectedRoles().remove(Roles.Godfather);
						tPlayer.setRole(godfather);
						break;
					case Jailor:
						ARole jailor = new Jailor(i, Roles.Jailor, -1, Chat.town, Dead.normal,
								Side.Town, false, false, false, false, null, RoleType.Visit, tPlayer, RoleTime.Night, null, RoleQueue.a1);
						lobby.getSelectedRoles().remove(Roles.Jailor);
						tPlayer.setRole(jailor);
						break;
					case Jester:
						break;
					case Lookout:
						break;
					case Mafia:
						break;
					case Medium:
						break;
					case Sheriff:
						break;
					case Spy:
						break;
					case Vampire:
						break;
					case Veteran:
						break;
					case Werewolf:
						break;

					}
					i++;
				}
			}else {
				if (time==0) {
					for (Player eachPlayer : lobby.getPlayers().keySet()) {
						TPlayer tPlayer = lobby.getPlayers().get(eachPlayer);
						switch (tPlayer.getRole().getSide()) {
						case Town:
							eachPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(""+tPlayer.getRole().getRole().toString()).color(ChatColor.GREEN).create());
							break;
						case Mafia:
							eachPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(""+tPlayer.getRole().getRole().toString()).color(ChatColor.RED).create());
							break;
						case Neutral:
							eachPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(""+tPlayer.getRole().getRole().toString()).color(ChatColor.DARK_GRAY).create());
							break;
						default:
							break;
						}
						System.out.println(tPlayer.getRole().getNo());
						System.out.println(lobby.getMap().getHomesOut().size());
						eachPlayer.teleport(lobby.getMap().getHomesOut().get(tPlayer.getRole().getNo()));
					}
					lobby.setStarted(true);
					this.cancel();
				}else {
					for (Player eachPlayer : lobby.getPlayers().keySet()) {
						eachPlayer.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(""+time).color(ChatColor.AQUA).create());
					}
					time--;
				}
			}
		
		
	}

}
