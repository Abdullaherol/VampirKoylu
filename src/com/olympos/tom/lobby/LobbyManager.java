package com.olympos.tom.lobby;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import com.olympos.tom.Main;

public class LobbyManager extends BukkitRunnable{

	private Lobby lobby;
	private Main plugin;
	private int time = 10;
	private int lastTime = 5;
	private ScoreboardManager scoreboardManager;
	private Scoreboard scoreboard;
	public LobbyManager(Main plugin,Lobby lobby) {
		this.plugin = plugin;
		this.lobby = lobby;
		scoreboardManager = Bukkit.getScoreboardManager();
		this.runTaskTimer(plugin, 20, 20);
	}
	@Override
	public void run() {
		if (lobby.getPlayers().size()!=lobby.getSize()) {
			
			if (time==0) {
				CancelLobby(lobby);
			}
			String[] titles = {"Players:   ","Time:      "};
			int[] scores = {lobby.getPlayers().size(),time};
			scoreboard = scoreboardGenerator("Town of Minecraft",titles, scores);
			for (Player player: lobby.getPlayers().keySet()) {
				player.setScoreboard(scoreboard);
			}
			time--;
		}else {
			if (lastTime==0) {
				lobby.setStarted(true);
				this.cancel();
			}
			lastTime--;
		}
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
	
	public void CancelLobby(Lobby lobby) {
		plugin.getReadyLobbies().remove(lobby);
		lobby.setReady(false);
		for (Player player : lobby.getPlayers().keySet()) {
			player.setScoreboard(null);
		}
		this.cancel();
	}

}
