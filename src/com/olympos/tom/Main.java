package com.olympos.tom;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olympos.tom.command.CPlay;
import com.olympos.tom.command.CVote;
import com.olympos.tom.data.DataMap;
import com.olympos.tom.gui.LobbyGui;
import com.olympos.tom.gui.MainGui;
import com.olympos.tom.handler.MainHandler;
import com.olympos.tom.command.CJailor;
import com.olympos.tom.command.CMap;
import com.olympos.tom.lobby.Lobby;
import com.olympos.tom.map.Map;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleQueue;
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
	private DataMap datamap;
	private MainGui mainGui;
	
	@Override
	public void onEnable() {
		if (!getDataFolder().mkdirs()) {
			getDataFolder().exists();
		}
		maps = new HashMap<String, Map>();
		datamap = new DataMap(this);
		players = new HashMap<Player, TPlayer>();
		lobbies = new HashMap<Player, ArrayList<Lobby>>();
		readyLobbies = new ArrayList<Lobby>();
		lobbyGui = new LobbyGui(this);
		mainGui = new MainGui(this);
		
		Bukkit.getServer().getPluginCommand("map").setExecutor(new CMap(this));
		Bukkit.getServer().getPluginCommand("play").setExecutor(new CPlay(this));
		Bukkit.getServer().getPluginCommand("jailor").setExecutor(new CJailor(this));
		Bukkit.getServer().getPluginCommand("vote").setExecutor(new CVote(this));
		Bukkit.getServer().getPluginManager().registerEvents(new MainHandler(this),this);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		if (maps.size()!=0) {
			for (Map map : maps.values()) {
				datamap.Save(map);
			}
		}
		super.onDisable();
	}

	public MainGui getMainGui() {
		return mainGui;
	}

	public void setMainGui(MainGui mainGui) {
		this.mainGui = mainGui;
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

	public DataMap getDatamap() {
		return datamap;
	}

	public void setDatamap(DataMap datamap) {
		this.datamap = datamap;
	}

	
}
