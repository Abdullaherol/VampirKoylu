package com.olympos.tom.command;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Door;

import com.olympos.tom.Main;
import com.olympos.tom.map.Map;

public class CMap implements CommandExecutor {

	private Main plugin;
	
	public CMap(Main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if (arg0 instanceof Player) {
			Player player = (Player)arg0;
			if (player.isOp()) {
				//map new name
				//map house name
				//map sign name
				//map door name
				//map hang name
				//map list
				
				if (arg3.length==2) {
					String value1 = arg3[0];
					String value2 = arg3[1];
					
					switch (value1) {
					case "new":
						Map map = new Map(value2);
						plugin.getMaps().put(value2, map);
						player.sendMessage(ChatColor.GRAY+"map created");
						plugin.getDatamap().Save(map);
						break;
					case "houseIn":
						if (plugin.getMaps().containsKey(value2)) {
							Map map2 = plugin.getMaps().get(value2);
							map2.getHomesIn().add(player.getLocation());
							player.sendMessage(ChatColor.GRAY+"houseIn added");
							plugin.getDatamap().Save(map2);
						}
						break;
					case "houseOut":
						if (plugin.getMaps().containsKey(value2)) {
							Map map2 = plugin.getMaps().get(value2);
							map2.getHomesOut().add(player.getLocation());
							player.sendMessage(ChatColor.GRAY+"houseOut added");
							plugin.getDatamap().Save(map2);
						}
						break;
					case "sign":
						if (plugin.getMaps().containsKey(value2)) {
							Map map2 = plugin.getMaps().get(value2);
							map2.getSigns().add(player.getTargetBlock(null, 5).getLocation());
							player.sendMessage(ChatColor.GRAY+"sign added");
							plugin.getDatamap().Save(map2);
						}
						break;
					case "door":
						if (plugin.getMaps().containsKey(value2)) {
							Map map2 = plugin.getMaps().get(value2);
							@SuppressWarnings("deprecation")
							Door door = (Door) player.getTargetBlock(null, 5).getType().getNewData(player.getTargetBlock(null, 5).getData());
							map2.getDoors().add(door);
							map2.getDoorLocations().add(player.getTargetBlock(null, 5).getLocation());
							player.sendMessage(ChatColor.GRAY+"door added");
							plugin.getDatamap().Save(map2);
							

						}
						break;
					case "hang":
						if (plugin.getMaps().containsKey(value2)) {
							Map map2 = plugin.getMaps().get(value2);
							map2.setHangLocation(player.getLocation());
							player.sendMessage(ChatColor.GRAY+"hang location created");
							plugin.getDatamap().Save(map2);
						}
						break;
					default:
						break;
					}
				}else if (arg3.length==1) {
					String value1 = arg3[0];
					if (value1.equalsIgnoreCase("list")) {
						String maps = "";
						for (String string : plugin.getMaps().keySet()) {
							maps += string +",";
						}
						player.sendMessage(ChatColor.GRAY+"Maps: "+ChatColor.WHITE+maps);
					}
				}
			}
			
		}
		return false;
	}

}
