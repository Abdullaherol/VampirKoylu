package com.olympos.tom;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olympos.tom.command.CPlay;
import com.olympos.tom.gui.LobbyGui;
import com.olympos.tom.handler.MainHandler;
import com.olympos.tom.command.CMap;
import com.olympos.tom.lobby.Lobby;
import com.olympos.tom.map.Map;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;
import com.olympos.tom.roles.ARole;
import com.olympos.tom.roles.Investigator;

import jdk.nashorn.internal.ir.Block;

public class Main extends JavaPlugin{
	
	private HashMap<Player, TPlayer> players;
	private HashMap<String, Map> maps;  
	private HashMap<Player, ArrayList<Lobby>> lobbies;
	private ArrayList<Lobby> readyLobbies;
	private LobbyGui lobbyGui;
	
	@Override
	public void onEnable() {
		players = new HashMap<Player, TPlayer>();
		maps = new HashMap<String, Map>();
		lobbies = new HashMap<Player, ArrayList<Lobby>>();
		lobbyGui = new LobbyGui(this);
		readyLobbies = new ArrayList<Lobby>();
		
		Bukkit.getServer().getPluginCommand("map").setExecutor(new CMap(this));
		Bukkit.getServer().getPluginCommand("play").setExecutor(new CPlay(this));
		Bukkit.getServer().getPluginManager().registerEvents(new MainHandler(this),this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

	public HashMap<Player, TPlayer> getPlayers() {
		return players;
	}

	public HashMap<String, Map> getMaps() {
		return maps;
	}

	public void setMaps(HashMap<String, Map> maps) {
		this.maps = maps;
	}

	public void setPlayers(HashMap<Player, TPlayer> players) {
		this.players = players;
	}

	public HashMap<Player, ArrayList<Lobby>> getLobbies() {
		return lobbies;
	}

	public void setLobbies(HashMap<Player, ArrayList<Lobby>> lobbies) {
		this.lobbies = lobbies;
	}

	public LobbyGui getLobbyGui() {
		return lobbyGui;
	}

	public void setLobbyGui(LobbyGui lobbyGui) {
		this.lobbyGui = lobbyGui;
	}

	public ArrayList<Lobby> getReadyLobbies() {
		return readyLobbies;
	}

	public void setReadyLobbies(ArrayList<Lobby> readyLobbies) {
		this.readyLobbies = readyLobbies;
	}

	
}
