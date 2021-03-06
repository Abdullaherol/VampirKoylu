package com.olympos.tom.roles;

import org.bukkit.GameMode;
import org.bukkit.block.Sign;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.RoleTime;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

import net.md_5.bungee.api.ChatColor;

public abstract class ARole {
	
	private int no;
	private Roles role;
	private int use;
	private Chat chat;
	private Dead deadType;
	private Side side;
	private boolean dead = false;
	private boolean blocked = false;
	private boolean jailed = false;
	private boolean healed = false;
	private TPlayer targetPlayer;
	private RoleType roleType;
	private TPlayer player;
	private RoleTime roleTime;
	private TPlayer bodyguard;
	private RoleQueue queue;
	private TPlayer vote;
	
	public ARole(int no, Roles role, int use ,Chat chat, Dead deadType, Side side, boolean dead, boolean blocked, boolean jailed,
			boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player,RoleTime roleTime,TPlayer bodyguard,RoleQueue queue,TPlayer vote) {
		super();
		this.no = no;
		this.role = role;
		this.use = use;
		this.chat = chat;
		this.deadType = deadType;
		this.side = side;
		this.dead = dead;
		this.blocked = blocked;
		this.jailed = jailed;
		this.healed = healed;
		this.targetPlayer = targetPlayer;
		this.roleType = roleType;
		this.player = player;
		this.roleTime = roleTime;
		this.bodyguard = bodyguard;
		this.queue = queue;
		this.vote = vote;
	}
	
	public void go(TPlayer tPlayer) {}
	public void go(TPlayer tPlayer,TPlayer tPlayer2) {}
	
	
	public TPlayer getVote() {
		return vote;
	}

	public void setVote(TPlayer vote) {
		this.vote = vote;
	}

	public RoleQueue getQueue() {
		return queue;
	}

	public void setQueue(RoleQueue queue) {
		this.queue = queue;
	}

	public TPlayer getBodyguard() {
		return bodyguard;
	}

	public void setBodyguard(TPlayer bodyguard) {
		this.bodyguard = bodyguard;
	}

	public RoleTime getRoleTime() {
		return roleTime;
	}

	public void setRoleTime(RoleTime roleTime) {
		this.roleTime = roleTime;
	}

	public boolean isHealed() {
		return healed;
	}

	public int getUse() {
		return use;
	}

	public void setUse(int use) {
		this.use = use;
	}

	public void setHealed(boolean healed) {
		this.healed = healed;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Chat getChat() {
		return chat;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public Dead getDeadType() {
		return deadType;
	}
	public void setDeadType(Dead deadType) {
		this.deadType = deadType;
	}
	public Side getSide() {
		return side;
	}
	public void setSide(Side side) {
		this.side = side;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
		if (dead) {
			chat = Chat.dead;
			player.getPlayer().setGameMode(GameMode.SPECTATOR);
			Sign sign = (Sign)getPlayer().getActiveLobby().getMap().getSigns().get(getPlayer().getRole().getNo()).getBlock().getState();
			sign.setLine(1, ChatColor.RED+"Dead");
			player.getPlayer().sendTitle(ChatColor.RED+"You are dead", ChatColor.GRAY+";(", 1, 20, 1);
		}
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public boolean isJailed() {
		return jailed;
	}
	public void setJailed(boolean jailed) {
		this.jailed = jailed;
	}
	public TPlayer getTargetPlayer() {
		return targetPlayer;
	}
	public void setTargetPlayer(TPlayer targetPlayer) {
		this.targetPlayer = targetPlayer;
	}
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	public TPlayer getPlayer() {
		return player;
	}
	public void setPlayer(TPlayer player) {
		this.player = player;
	}
	

	
}
