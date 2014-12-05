/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author aberg2
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
      Level level1 = new Level(1);
      level1.printData();
       
      Level level0 = new Level(0);
      level0.printData();
      
            
      JFrame frame = new JFrame("");
      Pane myPane=new Pane();
      myPane.setCurrentLevel(level1);
      
      frame.add(myPane);
      
      frame.setSize(myPane.getPaneSizeX()*myPane.getTileSizeX(), 
                    myPane.getPaneSizeY()*myPane.getTileSizeY()+24);
      frame.setVisible(true);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       
    }
    
}
