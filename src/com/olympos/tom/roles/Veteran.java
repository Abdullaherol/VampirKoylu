package com.olympos.tom.roles;

import org.bukkit.ChatColor;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Veteran extends ARole{

	public Veteran(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player, RoleTime roleTime,
			TPlayer bodyguard) {
		super(no, role, use, chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player, roleTime,
				bodyguard);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void go(TPlayer tPlayer) {
		if (getUse()>0) {
			setTargetPlayer(getTargetPlayer());
			setUse(getUse()-1);
		}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"You don't have enough rights to use your ability.");
	}
}
