package com.ai.astar.pacman.dfs;

import java.util.Comparator;

enum ScoreType {
	H_SCORE, F_SCORE, G_SCORE
}

public class Position {

	private int x;
	private int y;
	private char value;
	
	private Position parent; 

	public Position getParent() {
		return parent;
	}

	public void setParent(Position parent) {
		this.parent = parent;
	}

	// this is not the main data about the Position, actually transient info.
	// So, local class
	class Scores {
		double gScore;
		double fScore;
		double hScore;
	}

	public Scores scores;

	public void setScore(ScoreType type, double val) throws Exception {
		switch (type) {
		case H_SCORE:
			scores.hScore = val;
			break;
		case F_SCORE:
			scores.fScore = val;
			break;
		case G_SCORE:
			scores.gScore = val;
			break;
		default:
			throw new Exception("Invalid Score Type");
		}
	}

	public double getScore(ScoreType type) throws Exception {
		switch (type) {
		case H_SCORE:
			return scores.hScore;
		case F_SCORE:
			return scores.fScore;
		case G_SCORE:
			return scores.gScore;
		default:
			throw new Exception("Invalid Score Type");
		}
	}

	public Position(int x, int y, char value) {
		super();
		this.x = x;
		this.y = y;
		this.value = value;
		this.parent = null;
		this.scores = new Scores();
	}

	public Boolean hasFood() {
		return value == '.' ? true : false;
	}

	public Boolean hasPacman() {
		return value == 'P' ? true : false;
	}

	public Boolean isFree() {
		return value == '-' ? true : false;
	}

	public Boolean isWall() {
		return value == '%' ? true : false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Position other = (Position) obj;
		if (value != other.value)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

class PositionFScoreComparator implements Comparator<Position> {
	@Override
	public int compare(Position x, Position y) {
		double xHScore = 0.0;
		double yHScore = 0.0;
		try {
			xHScore = x.getScore(ScoreType.H_SCORE);
			yHScore = y.getScore(ScoreType.H_SCORE);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (xHScore < yHScore) {
			return -1;
		}
		if (xHScore > yHScore) {
			return 1;
		}
		return 0;
	}
}
