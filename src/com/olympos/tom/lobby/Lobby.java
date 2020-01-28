package com.olympos.tom.lobby;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.olympos.tom.map.Map;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.roles.Roles;

public class Lobby {
	
	private boolean started = false;
	private HashMap<Player, TPlayer> players;
	private int size = 0;
	private Map map;
	private ArrayList<Roles> selectedRoles;
	private GameManager gameManager;
	private boolean ready = false;
	
	
	public Lobby() {
		players =new HashMap<Player, TPlayer>();
		selectedRoles =  new ArrayList<Roles>();
		
	}
	public boolean isStarted() {
		return started;
	}
	public void setStarted(boolean started) {
		this.started = started;
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
	}
	
	
}
