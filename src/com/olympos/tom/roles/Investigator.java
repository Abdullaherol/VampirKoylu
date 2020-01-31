package com.olympos.tom.roles;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Investigator extends ARole{


	public Investigator(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
			boolean jailed, boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player, RoleTime roleTime,
			TPlayer bodyguard, RoleQueue queue) {
		super(no, role, use, chat, deadType, side, dead, blocked, jailed, healed, targetPlayer, roleType, player, roleTime,
				bodyguard, queue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void go(TPlayer targetPlayer) {
		if (targetPlayer!=null) {
			if (!isDead()) {
				if (!isJailed()) {
					if (!isBlocked()) {
						if (!targetPlayer.getRole().isJailed()) {
							switch (targetPlayer.getRole().getRole()) {
							case Veteran:
								if (targetPlayer.getRole().getTargetPlayer()==targetPlayer) {
									setDead(true);
								}else getPlayer().getPlayer().sendMessage(randomRoles(targetPlayer.getRole().getRole()));
								break;
							case Werewolf:
								if (getPlayer().getActiveLobby().isFullmoon()) {
									setDead(true);
								}else getPlayer().getPlayer().sendMessage(randomRoles(targetPlayer.getRole().getRole()));
								break;
							default:
								getPlayer().getPlayer().sendMessage(randomRoles(targetPlayer.getRole().getRole()));
								break;
							}
						}
					}else getPlayer().getPlayer().sendMessage(ChatColor.BLUE+"Last night,you were blocked by someone.");
				}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"JAIL!");
			}else System.out.println("Error: Dead player is trying to doing something");
		}
	}
	
	public String randomRoles(Roles targetRole) {
		String string = ChatColor.GRAY+"Your target could be an ";
		ArrayList<Roles> list = new ArrayList<Roles>();
		list.add(targetRole);
		int random;
		int max = 2;
			for (int i = 0; i < max; i++) {
				random = (int)(Math.random()*Roles.values().length);
				if (!list.contains(Roles.values()[random])) {
					list.add(Roles.values()[random]);
				}else {
					max++;
				}
			}
			
			random = (int)(Math.random()*2);
			System.out.println(list);
			string += list.get(random) +",";
			list.remove(random);
			System.out.println(list);
			random = (int)(Math.random()*2);
			string += list.get(random) +",";
			list.remove(random);
			System.out.println(list);
			string += list.get(0);
		return string;
	}
}
