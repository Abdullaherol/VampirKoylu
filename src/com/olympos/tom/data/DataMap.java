package com.olympos.tom.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.Door;

import com.olympos.tom.Main;
import com.olympos.tom.map.Map;

public class DataMap {
	private File file;
	private FileConfiguration fileConfiguration;
	private Main plugin;
	
	public DataMap(Main plugin) {
		this.plugin = plugin;
		file = new File(plugin.getDataFolder(),"Map.yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.fileConfiguration = YamlConfiguration.loadConfiguration(file);
		configTo();
	}
	
	@SuppressWarnings("unchecked")
	public void configTo() {
		
		for (String a : fileConfiguration.getConfigurationSection("").getKeys(false)) {
			ArrayList<Door> doors = new ArrayList<Door>();
			ArrayList<Location> doors2 = (ArrayList<Location>) fileConfiguration.getList(a+".Doors");
			for (Location location : doors2) {
				Door door = (Door) location.getBlock().getType().getNewData(location.getBlock().getData());
				doors.add(door);
			}
			
			ArrayList<Location> homesIn = (ArrayList<Location>) fileConfiguration.getList(a+".HomesIn");
			ArrayList<Location> homesOut = (ArrayList<Location>) fileConfiguration.getList(a+".HomesOut");
			ArrayList<Location> signs = (ArrayList<Location>) fileConfiguration.getList(a+".Signs");
			Location hangLocation = (Location) fileConfiguration.get(a+".HangLocation");
			Location jaiLocation = (Location) fileConfiguration.get(a+"JailLocation");
			Map map = new Map(a);
			map.setDoors(doors);
			map.setDoorLocations(doors2);
			map.setHangLocation(hangLocation);
			map.setHomesIn(homesIn);
			map.setHomesOut(homesOut);
			map.setSigns(signs);
			map.setJaiLocation(jaiLocation);
			plugin.getMaps().put(a, map);
		}
	}
	
	public void Save(Map map) {
		fileConfiguration.set(map.getName()+".Doors",map.getDoorLocations());
		fileConfiguration.set(map.getName()+".HomesIn",map.getHomesIn());
		fileConfiguration.set(map.getName()+".HomesOut",map.getHomesOut());
		fileConfiguration.set(map.getName()+".Signs",map.getSigns());
		fileConfiguration.set(map.getName()+".HangLocation",map.getHangLocation());
		fileConfiguration.set(map.getName()+".JailLocation",map.getJaiLocation());
		try {
			fileConfiguration.save(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
