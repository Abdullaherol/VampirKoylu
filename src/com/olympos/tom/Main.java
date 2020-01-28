package com.olympos.tom;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.olympos.tom.object.TPlayer;

public class Main extends JavaPlugin{
	
	private HashMap<Player, TPlayer> players;
	
	@Override
	public void onEnable() {
		players = new HashMap<Player, TPlayer>();
		// TODO Auto-generated method stub
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

}
