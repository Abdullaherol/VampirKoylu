package com.olympos.tom.lobby;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

public class GameManager extends BukkitRunnable{

	private Main plugin;
	private Lobby lobby;
	private int day;
	private int time = 0; 
	private boolean night = false;
	private boolean vote = true; //at first day true
	private boolean dead = true; //at first day true
	private boolean discussion = false;
	private HashMap<Roles, Player> players;
	//30sn day,30sn night
	
	public GameManager(Main plugin,Lobby lobby) {
		this.lobby = lobby;
		this.plugin = plugin;
		this.runTaskTimer(plugin, 60, 20);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println(time);
		if (lobby.isStarted()) {
			if (time==0) {
				if (dead) {
					if (discussion) {
						if (vote) {
							if (night) {
								//at finish night
								night=false;
								for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
									eachTPlayer.getPlayer().setPlayerTime(18000, true);
									if (!eachTPlayer.getRole().isDead()) {
										for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
											if (!otherTPlayer.getRole().isDead()) {
												eachTPlayer.getPlayer().showPlayer(otherTPlayer.getPlayer());
												
											}
										}
									}
								}
								
								for (int i = 1; i < RoleQueue.values().length; i++) {
									for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
										if (!eachTPlayer.getRole().isDead()) {
											if (eachTPlayer.getRole().getQueue()==RoleQueue.values()[i]) {
												eachTPlayer.getRole().go(eachTPlayer.getRole().getTargetPlayer());
											}
										}
									}
								}
							}else {
								
								for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
									eachTPlayer.getPlayer().setPlayerTime(6000, true);
									if (!eachTPlayer.getRole().isDead()) {
										if (eachTPlayer.getRole().getSide()==Side.Town) {
											if (eachTPlayer.getRole().getRole()==Roles.Jailor) {
												if (eachTPlayer.getRole().getTargetPlayer()!=null) {
													eachTPlayer.getPlayer().teleport(lobby.getMap().getJaiLocation());
													Player jailed = eachTPlayer.getRole().getTargetPlayer().getPlayer();
													eachTPlayer.getPlayer().showPlayer(jailed);
													jailed.showPlayer(eachTPlayer.getPlayer());
												}else {
													for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
														if (!otherTPlayer.getRole().isDead()) {
															if (otherTPlayer!=eachTPlayer) {
																eachTPlayer.getPlayer().hidePlayer(otherTPlayer.getPlayer());
															}
														}
													}
												}
											}else {
												for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
													if (!otherTPlayer.getRole().isDead() && !otherTPlayer.getRole().isJailed()) {
														if (otherTPlayer!=eachTPlayer) {
															eachTPlayer.getPlayer().hidePlayer(otherTPlayer.getPlayer());
														}
													}
												}
											}
										}else if (eachTPlayer.getRole().getSide()==Side.Neutral) {
											if (eachTPlayer.getRole().getRole()==Roles.Vampire) {
												for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
													if (!otherTPlayer.getRole().isDead()) {
														if (otherTPlayer!=eachTPlayer) {
															if (otherTPlayer.getRole().getRole()==Roles.Vampire) {
																
															}else {
																eachTPlayer.getPlayer().hidePlayer(otherTPlayer.getPlayer());
															}
														}
													}
												}
											}else {
												for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
													if (!otherTPlayer.getRole().isDead()) {
														if (otherTPlayer!=eachTPlayer) {
															eachTPlayer.getPlayer().hidePlayer(otherTPlayer.getPlayer());
														}
													}
												}
											}
										}
									}
								}
								
								night=true;
								vote=false;
								dead=false;
								discussion=false;
								time =30;
							}
						}else {
							time=10;
							vote=true;
						}
					}else {
						time=30;
						discussion=true;
					}
				}else {
					//end of night //dead process
					time=10;
					dead=true;
					//dead manager
				}
			}else {
				time--;	
			}
		}
	
	
	}
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isNight() {
		return night;
	}
	public void setNight(boolean night) {
		this.night = night;
	}
	public boolean isVote() {
		return vote;
	}
	public void setVote(boolean vote) {
		this.vote = vote;
	}
	public boolean isDead() {
		return dead;
	}
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	public boolean isDiscussion() {
		return discussion;
	}
	public void setDiscussion(boolean discussion) {
		this.discussion = discussion;
	}
	public HashMap<Roles, Player> getPlayers() {
		return players;
	}
	public void setPlayers(HashMap<Roles, Player> players) {
		this.players = players;
	}

}
