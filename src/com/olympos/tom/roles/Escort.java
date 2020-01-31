package com.olympos.tom.roles;

import org.bukkit.ChatColor;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Escort extends ARole{

	public Escort(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player,
			RoleTime roleTime) {
		super(no, role, use, chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player, roleTime);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void go(TPlayer targetPlayer) {
		if (!isDead()) {
			if (!isJailed()) {
				if (!isBlocked()) {
					if (!targetPlayer.getRole().isJailed()) {
						switch (targetPlayer.getRole().getRole()) {
						case Veteran:
							if (targetPlayer.getRole().getTargetPlayer()==targetPlayer) setDead(true);
							break;
						case Werewolf:
							if (getPlayer().getActiveLobby().isFullmoon()) {
								setDead(true);
							}else targetPlayer.getRole().setBlocked(true);
							break;
						default:
							targetPlayer.getRole().setBlocked(true);
							break;
						}
					}
				}else getPlayer().getPlayer().sendMessage(ChatColor.BLUE+"Last night,you were blocked by someone.");
			}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"JAIL!");
		}else System.out.println("Error: Dead player is trying to doing something");
	}

}
