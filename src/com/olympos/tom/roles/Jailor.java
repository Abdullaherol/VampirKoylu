package com.olympos.tom.roles;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Jailor extends ARole{

	public Jailor(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player,
			RoleTime roleTime) {
		super(no, role, use, chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player, roleTime);
	}
	
	@Override
	public void go(TPlayer tPlayer) {
		if (!isDead()) {
			tPlayer.getRole().setJailed(true);
			//if jailor want to kill, he can use /jailor kill
		}else System.out.println("Error: Dead player is trying to doing something");
	}

}
