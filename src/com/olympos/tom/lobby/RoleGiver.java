package com.olympos.tom.lobby;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;

public class RoleGiver extends BukkitRunnable{

	private Lobby lobby;
	private Main plugin;
	
	public RoleGiver(com.olympos.tom.lobby.Lobby lobby, Main plugin) {
		this.lobby = lobby;
		this.plugin = plugin;
		this.runTaskTimer(plugin, 20, 20);
	}
	@Override
	public void run() {
		if (lobby.getPlayers().size()==lobby.getSelectedRoles().size()) {
			for (Player eachPlayer : lobby.getPlayers().keySet()) {
				TPlayer tPlayer = lobby.getPlayers().get(eachPlayer);
				int random = (int)(Math.random()*lobby.getSelectedRoles().size());
				
			}	
		}else {
			System.out.println("Lobby Failed");
			this.cancel();
		}
		
	}

}
