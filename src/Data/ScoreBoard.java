/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;
import MainPackage.GamePanel;
import java.util.PriorityQueue;
/**
 *
 * @author Asus
 */
public class ScoreBoard implements Comparable<ScoreBoard>{
    String Name;
    int score;
    static int Number=0;
    public ScoreBoard(int score) {
        this.score = score;
        Number++;
        this.Name="Player"+Number;
    }
    @Override
    public int compareTo(ScoreBoard o) {
        if(this.score>o.score){
            return -1;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return   Name + "-" + score + '}';
    }
    
}
