package com.olympos.tom.map;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.material.Door;

public class Map {
	
	private boolean busy = false;
	private String name;
	private ArrayList<Location> homesIn;
	private ArrayList<Location> homesOut;
	private ArrayList<Door> doors;
	private ArrayList<Block> signs;
	private Location hangLocation;
	private Location jaiLocation;
	
	public Map(String name) {
		this.name = name;
		homesIn = new ArrayList<Location>();
		homesOut = new ArrayList<Location>();
		doors = new ArrayList<Door>();
		signs = new ArrayList<Block>();
		
	}

	public Location getJaiLocation() {
		return jaiLocation;
	}

	public void setJaiLocation(Location jaiLocation) {
		this.jaiLocation = jaiLocation;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Location> getHomesIn() {
		return homesIn;
	}

	public void setHomesIn(ArrayList<Location> homesIn) {
		this.homesIn = homesIn;
	}

	public ArrayList<Location> getHomesOut() {
		return homesOut;
	}

	public void setHomesOut(ArrayList<Location> homesOut) {
		this.homesOut = homesOut;
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Door> doors) {
		this.doors = doors;
	}

	public ArrayList<Block> getSigns() {
		return signs;
	}

	public void setSigns(ArrayList<Block> signs) {
		this.signs = signs;
	}

	public Location getHangLocation() {
		return hangLocation;
	}

	public void setHangLocation(Location hangLocation) {
		this.hangLocation = hangLocation;
	}


	
	
	
}
