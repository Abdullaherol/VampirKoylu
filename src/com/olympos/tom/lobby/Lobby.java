package com.olympos.tom.lobby;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.olympos.tom.Main;
import com.olympos.tom.map.Map;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.roles.Roles;

import sun.net.www.content.text.plain;

public class Lobby {
	
	private Main plugin;
	private boolean started = false;
	private HashMap<Player, TPlayer> players;
	private int size = 0;
	private Map map;
	private ArrayList<Roles> selectedRoles;
	private GameManager gameManager;
	private LobbyManager lobbyManager;
	private boolean ready = false;
	
	
	public Lobby(Main plugin) {
		this.plugin = plugin;
		players =new HashMap<Player, TPlayer>();
		selectedRoles =  new ArrayList<Roles>();
		
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
		if (started) {
			gameManager = new GameManager(plugin,this);
		}
	}
	public HashMap<Player, TPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(HashMap<Player, TPlayer> players) {
		this.players = players;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public ArrayList<Roles> getSelectedRoles() {
		return selectedRoles;
	}
	public void setSelectedRoles(ArrayList<Roles> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}
	public GameManager getGameManager() {
		return gameManager;
	}
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
		if (ready) {
			lobbyManager = new LobbyManager(plugin, this);
		}
	}
	
	public void playerJoin(Player player) {
		players.put(player, null);
	}
	public void playerQuit(Player player) {
		players.remove(player);
	}
	
	
}
