package com.olympos.tom.lobby;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
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
import com.olympos.tom.roles.Medium;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;

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
								Side.Town, false, false, false, false, null, RoleType.InHome, tPlayer, RoleTime.Night, null, RoleQueue.a4,null);
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
								Side.Town, false, false, false, false, null, RoleType.Visit, tPlayer, RoleTime.Night, null, RoleQueue.a3,null);
						lobby.getSelectedRoles().remove(Roles.Doctor);
						tPlayer.setRole(doctor);
						break;
					case Escort:
						break;
					case Godfather:
						ARole godfather = new Godfather(i, Roles.Godfather, -1, Chat.mafia, Dead.werewolf,
								Side.Mafia, false, false, false, false, null, RoleType.Visit, tPlayer, RoleTime.Night, null, RoleQueue.a5,null);
						lobby.getSelectedRoles().remove(Roles.Godfather);
						tPlayer.setRole(godfather);
						break;
					case Jailor:
						ARole jailor = new Jailor(i, Roles.Jailor, -1, Chat.town, Dead.normal,
								Side.Town, false, false, false, false, null, RoleType.Visit, tPlayer, RoleTime.Night, null, RoleQueue.a1,null);
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
						ARole medium = new Medium(i, Roles.Medium, -1, Chat.medium, Dead.normal, Side.Town, false, false, false, false, null,
								RoleType.InHome, tPlayer, RoleTime.Night, null, RoleQueue.a0, null);
						lobby.getSelectedRoles().remove(Roles.Medium);
						tPlayer.setRole(medium);
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
							eachPlayer.sendTitle(ChatColor.GREEN+tPlayer.getRole().getRole().toString(), ChatColor.GREEN+"Town", 1, 20, 1);
							break;
						case Mafia:
							eachPlayer.sendTitle(ChatColor.RED+tPlayer.getRole().getRole().toString(), ChatColor.RED+"Mafia", 1, 20, 1);
							break;
						case Neutral:
							eachPlayer.sendTitle(ChatColor.GRAY+tPlayer.getRole().getRole().toString(), ChatColor.GRAY+"Neutral", 1, 20, 1);
							break;
						default:
							break;
						}
						System.out.println(tPlayer.getRole().getNo());
						System.out.println(lobby.getMap().getHomesOut().size());
						eachPlayer.teleport(lobby.getMap().getHomesOut().get(tPlayer.getRole().getNo()));
					}
					
					for (int j = 0; j < lobby.getMap().getSigns().size(); j++) {
						Sign sign = (Sign) lobby.getMap().getSigns().get(j).getBlock().getState();
						for (int k = 0; k < 4; k++) {
							sign.setLine(k, "");
						}
						sign.update();
					}
					for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
						eachTPlayer.getPlayer().setWalkSpeed(0.5f);
						Sign sign = (Sign) lobby.getMap().getSigns().get(eachTPlayer.getRole().getNo()).getBlock().getState();
						sign.setLine(0, ChatColor.GRAY+"["+ChatColor.WHITE+eachTPlayer.getPlayer().getName()+ChatColor.GRAY+"]");
						sign.setLine(1, ChatColor.GREEN+"Alive");
						sign.update();
					}
					lobby.setStarted(true);
					this.cancel();
				}else {
					for (Player eachPlayer : lobby.getPlayers().keySet()) {
						eachPlayer.sendTitle(ChatColor.GREEN+String.valueOf(time), "Town of Minecraft", 1, 20, 1);
					}
					time--;
				}
			}
		
		
	}

}
