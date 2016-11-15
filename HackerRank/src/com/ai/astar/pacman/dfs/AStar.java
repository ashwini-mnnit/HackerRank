package com.ai.astar.pacman.dfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStar {
	private Position pacmanPos;
	private Position foodPos;
	private int arenaRows;
	private int arenaCols;

	private Position[][] arena;

	public void RunAStar() {
		ReadData();
		try {
			AStarImplimentation();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void AStarImplimentation() throws Exception {
		Comparator<Position> comparator = new PositionFScoreComparator();
		PriorityQueue<Position> openListPosition = new PriorityQueue<Position>(comparator);
		ArrayList<Position> closedList = new ArrayList<Position>();

		Position start = this.pacmanPos;
		Position end = this.foodPos;

		start.setScore(ScoreType.G_SCORE, 0);
		start.setScore(ScoreType.F_SCORE, start.getScore(ScoreType.G_SCORE) + Heuristics(start, end));
		openListPosition.add(start);

		while (!openListPosition.isEmpty()) {
			Position currentPos = openListPosition.poll();
			// if reached
			if (currentPos.equals(end)) {
				CalculatePath(currentPos);
			}

			closedList.add(currentPos);
			ArrayList<Position> neighbors = GetNeighbors(currentPos, arena);

			for (Position neighbor : neighbors) {
				if (!closedList.contains(neighbor)) {
					// tentative GScore
					double gScoreCur = neighbor.getScore(ScoreType.G_SCORE) + Heuristics(neighbor, currentPos);
					if (!openListPosition.contains(neighbor))
						openListPosition.add(neighbor);

					else if (gScoreCur >= neighbor.getScore(ScoreType.G_SCORE))
						continue;

					// This path is the best until now. Record it!
					neighbor.setScore(ScoreType.G_SCORE, gScoreCur);
					neighbor.setScore(ScoreType.F_SCORE, gScoreCur + Heuristics(neighbor, end));
					neighbor.setParent(currentPos);
				}
			}
		}
		System.out.println("NO path Exist !!!");;
	}

	private ArrayList<Position> GetNeighbors(Position currentPos, Position[][] arena) {
		ArrayList<Position> rv = new ArrayList<Position>();
		int xLimit = this.arenaRows - 1;
		int yLimit = this.arenaCols - 1;

		// left
		int xLeft = currentPos.getX();
		int yLeft = currentPos.getY() - 1;
		if (Utils.PositionInRange(0, xLimit, xLeft) && Utils.PositionInRange(0, yLimit, yLeft)
				&& arena[xLeft][yLeft].isFree())
			rv.add(arena[xLeft][yLeft]);

		// Right
		int xRight = currentPos.getX();
		int yRight = currentPos.getY() + 1;
		if (Utils.PositionInRange(0, xLimit, xRight) && Utils.PositionInRange(0, yLimit, yRight)
				&& arena[xRight][yRight].isFree())
			rv.add(arena[xRight][yRight]);

		// Up
		int xUp = currentPos.getX() - 1;
		int yUp = currentPos.getY();
		if (Utils.PositionInRange(0, xLimit, xUp) && Utils.PositionInRange(0, yLimit, yUp) && arena[xUp][yUp].isFree())
			rv.add(arena[xUp][yUp]);

		// Down
		int xDown = currentPos.getX() + 1;
		int yDown = currentPos.getY();
		if (Utils.PositionInRange(0, xLimit, xDown) && Utils.PositionInRange(0, yLimit, yDown)
				&& arena[xDown][yDown].isFree())
			rv.add(arena[xDown][yDown]);

		return rv;
	}

	private void CalculatePath(Position currentPos) {
		Stack<Position> stack = new Stack<Position>();
		while (currentPos.getParent() != null) {
			stack.add(currentPos);
			currentPos = currentPos.getParent();
		}
		System.out.println("----------------------------------------");
		while (!stack.isEmpty()) {
			System.err.println(stack.pop().toString());
		}
		System.out.println("----------------------------------------");

	}

	private double Heuristics(Position start, Position end) {
		// Calculate Heuristics.
		return Utils.getEuclideanDistance(start.getX(), start.getY(), end.getX(), end.getY());
	}
	
	private void ReadData() {
		BufferedReader bufferReader;
		try {
			bufferReader = new BufferedReader(new FileReader("InputPacmanAstar.txt"));
			readPacmanPosition(bufferReader);
			readFoodPosition(bufferReader);
			readArenaSize(bufferReader);
			this.arena = new Position[arenaRows][arenaCols];
			readArena(bufferReader);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void readArena(BufferedReader bufferReader) throws IOException {
		String sCurrentLine;
		int row = 0;
		while ((sCurrentLine = bufferReader.readLine()) != null) {
			for (int icol = 0; icol < sCurrentLine.length(); icol++) {
				this.arena[row][icol] = new Position(row, icol, sCurrentLine.charAt(icol));
			}
			row++;
		}
	}

	private void readArenaSize(BufferedReader bufferReader) throws IOException {
		String pacMonPosLine = bufferReader.readLine();
		this.arenaRows = Integer.parseInt(pacMonPosLine.split(" ")[0]);
		this.arenaCols = Integer.parseInt(pacMonPosLine.split(" ")[1]);
	}

	private void readFoodPosition(BufferedReader bufferReader) throws IOException {
		String pacMonPosLine = bufferReader.readLine();
		this.foodPos = new Position(Integer.parseInt(pacMonPosLine.split(" ")[0]),
				Integer.parseInt(pacMonPosLine.split(" ")[1]), '.');
	}

	private void readPacmanPosition(BufferedReader bufferReader) throws IOException {
		String pacMonPosLine = bufferReader.readLine();
		this.pacmanPos = new Position(Integer.parseInt(pacMonPosLine.split(" ")[0]),
				Integer.parseInt(pacMonPosLine.split(" ")[1]), 'P');
	}
}
