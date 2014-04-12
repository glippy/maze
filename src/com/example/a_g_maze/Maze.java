package com.example.a_g_maze;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;


public class Maze {


	private static final int W = 48; 		// ширина стенок
	private static final int C = 24; 		// для центра круга
	
	public float touchX = 0;
	public float touchY = 0;
	public MazeView maze;
    public final static int WIDTH = 12;
    public final static int HEIGHT = 12;
    private static char[][] arrMaze = new char[WIDTH][HEIGHT];
    private static int lvl;
    
	public Maze(int level, Context context) {
        

		
		System.out.println("creating");
		
		try {
			
			AssetManager assetManager = context.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open(level + ".lvl"));
            BufferedReader in = new BufferedReader(istream);
			
            String line;
            int col = 0, row = 0;
            while((line = in.readLine()) != null && row < HEIGHT) {
                for(col = 0; col < line.length() && col < WIDTH; col++) {
                    if ( line.charAt(col) != '2') { 
                    	arrMaze[row][col] = line.charAt(col);
                    } else {
                    	//прячем робота из массива, но передаем его координаты
                    	touchX = (row+1)*W+C;
                    	touchY = (col+1)*W+C;
                    }
                }
                row++;
                
            }
            in.close();
        } catch(IOException e) {
        	System.out.println("There is no file");
            e.printStackTrace();
        }

        lvl = level;
	}

    public static char checkWay(int x, int y) {
    	return arrMaze[y][x];
    }
	
	public static void setCell(int x, int y, char cell){
		arrMaze[y][x] = cell;
	}

    public static char[][] getMaze() {
    	return arrMaze;
    }


}

