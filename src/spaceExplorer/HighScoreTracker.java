package spaceExplorer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



/**
 * @author austin
 *
 */
public class HighScoreTracker {
    public static String csvFile = "/home/austin/Dropbox/eclipse/spaceExplorer/src/spaceExplorer/HighScores.csv";
    public static BufferedReader br = null;
    public static String line = "";
    public static String csvSplitBy = ",";
    
    /**
     * Method for writing new scores to a local CSV file.
     */
    public void addScore(){
        
    }
    
    /**
     * Method for reading high scores from a local CSV file.
     */
    public static String[] readScores(){
        String[] bestScores = new String[4];
        
        try {
            
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {      
                    int bestTime1 = 1000000000;
                    int bestTime2 = 1000000000;                    
                
                    String[] score = line.split(csvSplitBy);
                    
                    int level = Integer.parseInt(score[0]);                                   
                    String player = score[1];
                    int time = Integer.parseInt(score[2]);
                    
                    System.out.println("Score [Player= " + player 
                             + " , Time=" + time + "]");
                    
                    if(level == 1 && time < bestTime1){
                        bestTime1 = time;
                        bestScores[0] = player;
                        bestScores[1] = Integer.toString(time);
                    }
                    else if(level == 2 && time < bestTime2){
                        bestTime2 = time;
                        bestScores[2] = player;
                        bestScores[3] = Integer.toString(time);
                    }

            }

        } catch (FileNotFoundException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                        br.close();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
        }  
        
        return bestScores;
    }//END readScores()
    
}
