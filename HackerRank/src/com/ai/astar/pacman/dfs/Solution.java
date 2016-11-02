package com.ai.astar.pacman.dfs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		String sCurrentLine; 
		//URL url = Test.class.getClassLoader().getResource("/Files/input.txt");
		//System.out.println(url.getPath());
		BufferedReader bufferReader = new BufferedReader(new FileReader("src/com/ai/astar/pacman/dfs/input.txt"));

		try {
			while ((sCurrentLine = bufferReader.readLine()) != null) {
			  System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
