package com.olympos.tom.map;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class Map {
	
	private boolean busy = false;
	private String name;
	private ArrayList<Location> homes;
	private ArrayList<Block> doors;
	private ArrayList<Block> signs;
	private Location hangLocation;
	
	public Map(String name) {
		this.name = name;
		homes = new ArrayList<Location>();
		doors = new ArrayList<Block>();
		signs = new ArrayList<Block>();
		
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

	public ArrayList<Location> getHomes() {
		return homes;
	}

	public void setHomes(ArrayList<Location> homes) {
		this.homes = homes;
	}

	public ArrayList<Block> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Block> doors) {
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
