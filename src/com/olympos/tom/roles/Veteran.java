package com.olympos.tom.roles;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Veteran extends ARole{

	public Veteran(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player) {
		super(no, role, 3, chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player);
	}

	@Override
	public void go(TPlayer tPlayer) {
		setTargetPlayer(getTargetPlayer());
	}
}
