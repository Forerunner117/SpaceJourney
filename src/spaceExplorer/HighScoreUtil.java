package spaceExplorer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Path;



/**
 * @author austin
 *
 */
public class HighScoreUtil {
    /**
     * Method for writing new scores to a local CSV file.
     */
    public static void addScore(String level, String player, String time){
        String csvFile = "resources/HighScores.csv";
        
        String newScore = level + "," + player + "," + time;
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(csvFile, true)))) {
            out.println(newScore);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        
    }
    
    /**
     * Method for reading high scores from a local CSV file.
     */
    public static String[] readScores(){
        String csvFile = "resources/HighScores.csv";
        String line = "";
        String csvSplitBy = ",";
        String[] bestScores = new String[4];
        int bestTime1 = 1000000000;
        int bestTime2 = 1000000000;
        
        try(BufferedReader br = new BufferedReader(new FileReader(new File(csvFile).getAbsolutePath()))){         
            while ((line = br.readLine()) != null) {      
                    String[] score = line.split(csvSplitBy);
                    
                    int level = Integer.parseInt(score[0]);                                   
                    String player = score[1];
                    int time = Integer.parseInt(score[2]);
                    
                    System.out.println("Score [Player= " + player 
                             + " , Time=" + time + "]");
                    
                    if(level == 1 && time < bestTime1){
                        bestTime1 = time;
                        bestScores[0] = player;
                        bestScores[1] = Integer.toString(time/1000);
                    }
                    else if(level == 2 && time < bestTime2){
                        bestTime2 = time;
                        bestScores[2] = player;
                        bestScores[3] = Integer.toString(time/1000);
                    }

            }

        } catch (IOException e) {
                e.printStackTrace();
        }
        
        return bestScores;
    }//END readScores()
    
}
