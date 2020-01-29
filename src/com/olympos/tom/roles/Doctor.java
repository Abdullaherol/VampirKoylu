package com.olympos.tom.roles;

import org.bukkit.ChatColor;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Doctor extends ARole{
	
	

	public Doctor(int no, Roles role, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player) {
		super(no, role, 1,chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player);
	}

	@Override
	public void go(TPlayer targetPlayer) {
		if (!isDead()) {
			if (!isJailed()) {
				if (!isBlocked()) {
					if (!targetPlayer.getRole().isJailed()) {
						switch (targetPlayer.getRole().getRole()) {
						case Veteran:
							if (targetPlayer.getRole().getTargetPlayer()!=targetPlayer) {
								targetPlayer.getRole().setHealed(true);
							}else setDead(true);
							break;
						case Werewolf:
							if (getPlayer().getActiveLobby().isFullmoon()) {
								setDead(true);
							}else targetPlayer.getRole().setHealed(true);
							break;
						default:
							if (getPlayer()==getTargetPlayer().getPlayer()) {
								if (getUse()>0) {
									targetPlayer.getRole().setHealed(true);
									setUse(getUse()-1);
								}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"You can't use your ability on yourself!");
							}
							break;
						}
					}
				}else getPlayer().getPlayer().sendMessage(ChatColor.BLUE+"Last night,you were blocked by someone.");
			}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"JAIL!");
		}else System.out.println("Error: Dead player is trying to doing something");
		
	}
}


	