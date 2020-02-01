package com.olympos.tom.lobby;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.olympos.tom.Main;
import com.olympos.tom.object.TPlayer;
import com.olympos.tom.properties.RoleQueue;
import com.olympos.tom.properties.RoleType;
import com.olympos.tom.properties.Roles;
import com.olympos.tom.properties.Side;

import net.md_5.bungee.api.ChatColor;

public class GameManager extends BukkitRunnable{

	private Main plugin;
	private Lobby lobby;
	private int day = 0;
	private int time = 30; 
	private boolean night = false;
	private boolean vote = true; //at first day true
	private boolean dead = true; //at first day true
	private boolean discussion = true;
	private boolean voteDead = true;
	private HashMap<Roles, Player> players;
	private Scoreboard scoreboard;
	private ScoreboardManager scoreboardManager;
	private HashMap<Player, BossBar> bossbars;
	//30sn day,30sn night
	
	public GameManager(Main plugin,Lobby lobby) {
		this.lobby = lobby;
		this.plugin = plugin;
		bossbars = new HashMap<Player, BossBar>();
		scoreboardManager = Bukkit.getScoreboardManager();
		this.runTaskTimer(plugin, 60, 20);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if (lobby.isStarted()) {
			
			if (time<=0) {
				if (dead) {
					if (discussion) {
						if (vote) {
							if (voteDead) {
								if (night) {
									//at finish night
									
									day++;
									for (int i = 1; i < RoleQueue.values().length; i++) {
										for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
											if (!eachTPlayer.getRole().isDead()) {
												if (eachTPlayer.getRole().getQueue()==RoleQueue.values()[i]) {
													eachTPlayer.getRole().go(eachTPlayer.getRole().getTargetPlayer());
												}
											}
										}
									}
									
									for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
										eachTPlayer.getPlayer().setPlayerTime(6000, false);
										if (!eachTPlayer.getRole().isDead()) {
											eachTPlayer.getRole().setBlocked(false);
											eachTPlayer.getRole().setHealed(false);
											eachTPlayer.getRole().setBodyguard(null);
											eachTPlayer.getRole().setJailed(false);
											eachTPlayer.getRole().setTargetPlayer(null);
											eachTPlayer.getPlayer().teleport(lobby.getMap().getHomesOut().get(eachTPlayer.getRole().getNo()));
											for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
												if (!otherTPlayer.getRole().isDead()) {
													eachTPlayer.getPlayer().showPlayer(otherTPlayer.getPlayer());
												}
											}
										}else {
											for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
													eachTPlayer.getPlayer().showPlayer(otherTPlayer.getPlayer());
											}
										}
									}
									night=false;
									vote=false;
									dead=false;
									discussion=false;
								}else {
									
									for (TPlayer eachTPlayer : lobby.getPlayers().values()) {
										eachTPlayer.getPlayer().setPlayerTime(18000, false);
										if (eachTPlayer.getRole().getRoleType()==RoleType.Visit) {
											eachTPlayer.getPlayer().teleport(lobby.getMap().getHomesOut().get(eachTPlayer.getRole().getNo()));
										}else eachTPlayer.getPlayer().teleport(lobby.getMap().getHomesIn().get(eachTPlayer.getRole().getNo()));
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
																System.out.println("Town Hide");
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
											}else {
												for (TPlayer otherTPlayer : lobby.getPlayers().values()) {
													if (!otherTPlayer.getRole().isDead()) {
														if (otherTPlayer!=eachTPlayer) {
															if (otherTPlayer.getRole().getSide()!=Side.Mafia) {
																System.out.println("Mafia Hide");
																eachTPlayer.getPlayer().hidePlayer(otherTPlayer.getPlayer());
															}
														}
													}
												}
											}
										}
									}
									
									night=true;
									
									time =30;
								}
							}else {
								voteDead=true;
								time=5;
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
			
			for (Player player: lobby.getPlayers().keySet()) {
				TPlayer tPlayer = lobby.getPlayers().get(player);
				String[] titles = {"Players: "+lobby.getPlayers().size(),"Time: "+time,"Day: "+day,"Night: "+night,"Discussion: "+discussion,"Dead: "+tPlayer.getRole().isDead()};
				int[] scores = {6,5,4,3,2,1};
				scoreboard = scoreboardGenerator("Town of Minecraft",titles, scores);
				
				
				player.setScoreboard(scoreboard);
				
				if (!tPlayer.getRole().isDead()) {
					if (bossbars.containsKey(player)) {
						BossBar bossBar = bossbars.get(player);
					}else {
						
						switch (tPlayer.getRole().getSide()) {
						case Town:
							BossBar bossBar = Bukkit.createBossBar(ChatColor.GREEN+tPlayer.getRole().getRole().toString(), BarColor.WHITE, BarStyle.SOLID, BarFlag.DARKEN_SKY);
							bossBar.addPlayer(player);
							bossbars.put(player, bossBar);
							break;
						case Mafia:
							BossBar bossBar2 = Bukkit.createBossBar(ChatColor.RED+tPlayer.getRole().getRole().toString(), BarColor.WHITE, BarStyle.SOLID, BarFlag.DARKEN_SKY);
							bossBar2.addPlayer(player);
							bossbars.put(player, bossBar2);
							break;
						case Neutral:
							BossBar bossBar3 = Bukkit.createBossBar(ChatColor.DARK_GRAY+tPlayer.getRole().getRole().toString(), BarColor.WHITE, BarStyle.SOLID, BarFlag.DARKEN_SKY);
							bossBar3.addPlayer(player);
							bossbars.put(player, bossBar3);
							break;
						}
					}
				}
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
	public Scoreboard scoreboardGenerator(String title,String[] scoreTitles,int[] scores) {
		Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
		
		Objective objective = scoreboard.registerNewObjective("Lobby", "");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(title);
		
		for (int i = 0; i < scoreTitles.length; i++) {
			Score score = objective.getScore(scoreTitles[i]);
			score.setScore(scores[i]);
		}
		
		return scoreboard;
	}
}
