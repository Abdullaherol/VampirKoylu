package com.olympos.tom.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.olympos.tom.Main;
import com.olympos.tom.lobby.Lobby;

public class CPlay implements CommandExecutor{

	private Main plugin;
	
	public CPlay(Main plugin) {
		super();
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player player = (Player)arg0;
			if (!plugin.getLobbies().containsKey(player)) {
				plugin.getLobbies().put(player, new ArrayList<Lobby>());
			}
			plugin.getLobbyGui().openMainLobby(player);
		}
		return false;
	}
	
	
	

}
