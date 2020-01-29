package com.olympos.tom.object;

import org.bukkit.entity.Player;

import com.olympos.tom.lobby.Lobby;
import com.olympos.tom.roles.ARole;

public class TPlayer {
	
	private Lobby activeLobby;
	private Player player;
	private ARole role;
	
	public TPlayer(Lobby activeLobby, Player player, ARole role) {
		this.activeLobby = activeLobby;
		this.player = player;
		this.role = role;
	}
	public Lobby getActiveLobby() {
		return activeLobby;
	}
	public void setActiveLobby(Lobby activeLobby) {
		this.activeLobby = activeLobby;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ARole getRole() {
		return role;
	}
	public void setRole(ARole role) {
		this.role = role;
	}
	
	
	
}
