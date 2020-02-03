package com.olympos.tom.roles;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Lookout extends ARole{

	public Lookout(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player, RoleTime roleTime,
			TPlayer bodyguard, RoleQueue queue, TPlayer vote) {
		super(no, role, use, chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player, roleTime,
				bodyguard, queue, vote);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void go(TPlayer targetPlayer) {
		if (targetPlayer!=null) {
			if (!isDead()) {
				if (!isJailed()) {
					if (!isBlocked()) {
						String string = "";
						for (Player player : getPlayer().getActiveLobby().getPlayers().keySet()) {
							TPlayer tPlayer = getPlayer().getActiveLobby().getPlayers().get(player);
							if (!tPlayer.getRole().isDead() && tPlayer.getRole().getTargetPlayer()==targetPlayer &&targetPlayer.getRole().getRoleType()==RoleType.Visit
									&& !targetPlayer.getRole().isJailed() && !targetPlayer.getRole().isBlocked()) {
								string += ChatColor.GREEN+tPlayer.getRole().getPlayer().getPlayer().getName() +ChatColor.GRAY+",";
							}
						}
						if (string.length()!=0) {
							string += " visited your target last night!";
							getPlayer().getPlayer().sendMessage(string);
						}else getPlayer().getPlayer().sendMessage("Nobody visited your target last night!");
					}else getPlayer().getPlayer().sendMessage(ChatColor.BLUE+"Last night,you were blocked by someone.");
				}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"JAIL!");
			}else System.out.println("Error: Dead player is trying to doing something");
		}
	}

}
