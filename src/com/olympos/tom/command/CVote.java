package com.olympos.tom.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;

public class CVote implements CommandExecutor{
	
	private Main plugin;
	
	public CVote(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player player = (Player)arg0;
			if (plugin.getPlayers().containsKey(player)) {
				TPlayer tPlayer = plugin.getPlayers().get(player);
				if (tPlayer.getActiveLobby()!=null) {
					if (arg3.length==0) {
						if (tPlayer.getActiveLobby().getGameManager().isDiscussion()) {
							plugin.getMainGui().vote(tPlayer);
						}
					}
				}
			}
		}
		return false;
	}

}
