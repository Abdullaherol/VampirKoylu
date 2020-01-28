package com.olympos.tom.lobby;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.roles.Roles;

public class Lobby {
	
	private boolean Started = false;
	private HashMap<Player, TPlayer> players;
	private int size;
	private Location lobbyLocation;
	private ArrayList<Roles> selectedRoles;
}
