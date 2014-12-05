/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aberg2
 */
public class Level {
    final private int maxX=1000;
    final private int maxY=400;
    private int levelNumber=0;
    private int levelLength=0;
    private int levelHeight=0;
    final private int [][] map; //  = new int[maxX][maxY];
    
    public int getMap(int ix,int jx) {
        return map[ix][jx];
    }
    
    public Level(int level){
     map = new int[maxX][maxY];
     levelNumber=level;
     try{
        readLevel();
       }
       catch(IOException e) {
            e.printStackTrace();
       }
    }
   
    public void setLevelNumber(int num){
        levelNumber=num;
    }
    
    public int getLevelNumber(){
        return levelNumber;
    }
    
    public int getLevelHeight(){
        return levelHeight;
    }
    
    public int getLevelLength(){
        return levelLength;
    }
    
    void printData(){
        System.out.println("Level Number:" + levelNumber);
        System.out.println("Level Length:" + levelLength);
        System.out.println("Level Height:" + levelHeight);
    }
    
    public void readLevel() throws IOException {
    String fileName = "src/game2/Level"+levelNumber+".lvl";
            
    ArrayList lines = new ArrayList();   
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    
    while (true) {
         String line = reader.readLine();
         if (line == null) {
             reader.close();
             break;
         }
         if (!line.startsWith("#")) {
             lines.add(line);
             levelLength = Math.max(levelLength,line.length());
         }
     }
     // parse height
     levelHeight = lines.size();
     
     
     for (int jx=0;jx<levelHeight;jx++) {
            String line = (String)lines.get(jx);
            for (int ix=0;ix<Math.min(levelLength,line.length());ix++) {
                char ch = line.charAt(ix);
                if (ch == ' ') {
                   map[ix][jx]=0;
                }
                if (ch == 'A') {
                      map[ix][jx]=1;
                }
                if (ch == 'B') {
                     map[ix][jx]=2;
                }
                if (ch == 'C') {
                     map[ix][jx]=3;
                }
                if (ch == 'D') {
                     map[ix][jx]=4;
                }
                if (ch == 'E') {
                     map[ix][jx]=5;
                }
                if (ch == 'F') {
                     map[ix][jx]=6;
                }
                if (ch == 'G') {
                     map[ix][jx]=7;
                }
                if (ch == 'H') {
                     map[ix][jx]=8;
                }
                if (ch == 'I') {
                     map[ix][jx]=9;
                }
                if (ch == 'J') {
                     map[ix][jx]=10;
                }
                if (ch == 'K') {
                     map[ix][jx]=11;
                }
           }      
     }
     
     
    }
}
