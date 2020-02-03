package com.olympos.tom.roles;

import org.bukkit.ChatColor;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class Godfather extends ARole{

	public Godfather(int no, Roles role, int use, Chat chat, Dead deadType, Side side, boolean dead, boolean blocked,
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
					if (!targetPlayer.getRole().isJailed()) {
						TPlayer mafia = null;
						for (TPlayer eachTPlayer : getPlayer().getActiveLobby().getPlayers().values()) {
							if (eachTPlayer.getRole().getRole()==Roles.Mafia) {
								mafia = eachTPlayer;
							}
						}
						if (mafia==null) {
							switch (targetPlayer.getRole().getRole()) {
							case Veteran:
								if (targetPlayer.getRole().getTargetPlayer()!=targetPlayer) {
									if (targetPlayer.getRole().getBodyguard()!=null) {
										targetPlayer.getRole().getBodyguard().getRole().setDead(true);
										setDead(true);
									}else {
										targetPlayer.getRole().setDead(true);
									}
								}else setDead(true);
								break;
							case Werewolf:
								if (getPlayer().getActiveLobby().isFullmoon()) {
									setDead(true);
								}else {
									if (targetPlayer.getRole().getBodyguard()!=null) {
										targetPlayer.getRole().getBodyguard().getRole().setDead(true);
										setDead(true);
									}else {
										targetPlayer.getRole().setDead(true);
									}
								}
								break;
							default:
								if (!targetPlayer.getRole().isHealed()) {
									if (targetPlayer.getRole().getBodyguard()==null) {
										targetPlayer.getRole().setDead(true);
									}else {
										if (!targetPlayer.getRole().getBodyguard().getRole().isDead()) {
											targetPlayer.getRole().getBodyguard().getRole().setDead(true);
											setDead(true);
										}
									}
								}
								break;
							}
						}else {
							mafia.getRole().setTargetPlayer(targetPlayer);
							mafia.getRole().go(mafia.getRole().getTargetPlayer());
						}
					}
				}else getPlayer().getPlayer().sendMessage(ChatColor.BLUE+"Last night,you were blocked by someone.");
			}else getPlayer().getPlayer().sendMessage(ChatColor.RED+"JAIL!");
		}else System.out.println("Error: Dead player is trying to doing something");
	}
	}
	
	@Override
	public void setDead(boolean dead) {
		if (dead) {
			for (TPlayer eachTPlayer : getPlayer().getActiveLobby().getPlayers().values()) {
				if (!eachTPlayer.getRole().isDead()) {
					if (eachTPlayer!=getPlayer()) {
						if (eachTPlayer.getRole().getSide()==Side.Mafia) {
							ARole aRole = new Godfather(eachTPlayer.getRole().getNo(), Roles.Godfather, -1, Chat.mafia, Dead.werewolf, Side.Mafia,
									false, false, false, false, null, RoleType.Visit, eachTPlayer.getRole().getPlayer(), RoleTime.Night, null,RoleQueue.a5,null);
							eachTPlayer.setRole(aRole);
						}
					}
				}
			}
		}
		super.setDead(dead);
	}

}
