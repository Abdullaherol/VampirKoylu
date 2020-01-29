package com.olympos.tom.roles;

import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.Chat;
import com.olympos.tom.properties.Dead;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

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
	
	public ARole(int no, Roles role, int use ,Chat chat, Dead deadType, Side side, boolean dead, boolean blocked, boolean jailed,
			boolean healed, TPlayer targetPlayer, RoleType roleType, TPlayer player) {
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
	}
	
	public void go(TPlayer tPlayer) {}
	public void go(TPlayer tPlayer,TPlayer tPlayer2) {}
	
	
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
