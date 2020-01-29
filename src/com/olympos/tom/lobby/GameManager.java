package com.olympos.tom.lobby;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.olympos.tom.Main;

public class GameManager extends BukkitRunnable{

	private Main plugin;
	private Lobby lobby;
	private int day;
	private int time = 45; //first 15 second give roles,30 second discussion
	private boolean night = false;
	private boolean vote = true; //at first day true
	private boolean dead = true; //at first day true
	private boolean discussion = false;
	//30sn day,30sn night
	
	public GameManager(Main plugin,Lobby lobby) {
		this.lobby = lobby;
		this.plugin = plugin;
		this.runTaskTimer(plugin, 60, 20);
	}
	@Override
	public void run() {
		System.out.println(time);
		if (lobby.isStarted()) {
			if (time==0) {
				if (dead) {
					if (discussion) {
						if (vote) {
							if (night) {
								
							}else {
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

}
