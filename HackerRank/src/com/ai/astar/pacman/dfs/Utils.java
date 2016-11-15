package com.ai.astar.pacman.dfs;

public class Utils {

	// Inclusive
	public static Boolean PositionInRange(int min, int max, int val) {
		if (val >= min && val <= max)
			return true;
		return false;
	}
	
	//Euclidean Distance
	public static double getEuclideanDistance(int xStart, int yStart, int xEnd, int yEnd)
	{
		return Math.pow(Math.abs(xStart-xEnd), 2)+ Math.pow(Math.abs(yStart-yEnd), 2);
	}
}
