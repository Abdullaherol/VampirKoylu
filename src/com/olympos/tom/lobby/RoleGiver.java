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
import com.olympos.tom.roles.Investigator;

public class RoleGiver extends BukkitRunnable{

	private Lobby lobby;
	private Main plugin;
	private boolean giver = true;
	
	public RoleGiver(com.olympos.tom.lobby.Lobby lobby, Main plugin) {
		this.lobby = lobby;
		this.plugin = plugin;
		this.runTaskTimer(this.plugin, 20, 20);
	}
	@Override
	public void run() {
		if (lobby.getPlayers().size()==lobby.getSelectedRoles().size()) {
			if (giver) {
				giver = false;
				int i = 0;
				for (Player eachPlayer : lobby.getPlayers().keySet()) {
					i++;
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
						break;
					case Escort:
						break;
					case Godfather:
						break;
					case Jailor:
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
				}
			}	
		}else {
			System.out.println("Lobby Failed");
			this.cancel();
		}
		
	}

}
