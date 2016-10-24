package com.ai.astar.pacman.dfs;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
	private int val;
	private List<GraphNode> neighbour;
	private List<Integer> weightList;

	public List<GraphNode> getNeighbour() {
		return neighbour;
	}
	
	public GraphNode(int val) {
		super();
		this.val = val;
		this.neighbour = new ArrayList<GraphNode>();
		this.weightList = new ArrayList<Integer>();
	}

	public int getVal() {
		return val;
	}

	public boolean isAdjListcontainsNode(int val) {
		for (GraphNode neighbour1 : neighbour) {
			if (neighbour1.getVal() == val) {
				return true;
			}
		}
		return false;
	}

	public void addToAdjencyList(GraphNode node, int weight)
	{
		neighbour.add(node);
		weightList.add(weight);
	}
	
	public void printNode() {
		System.out.print("GraphNode[" + val + "]" + "neighbour =[");
		for (GraphNode neighbour : this.neighbour) {
			System.out.print(neighbour.getVal() + ",");
		}
		System.out.print("]  weightList=[");
		for (Integer wt : this.weightList) {
			System.out.print(wt + ",");
		}
		System.out.println("]");
	}
}
