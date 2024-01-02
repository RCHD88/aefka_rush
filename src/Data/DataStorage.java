/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import Object.Tower;
import java.io.Serializable;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 *
 * @author Asus
 */
public class DataStorage implements Serializable{
    //Dari PlayerBase
    int stage;
    int gold;
    int life;
    int[] item=new int[3];
    //Dari Player
    int[] build=new int[3];
    HashMap<String, Tower> Towers;
    PriorityQueue<ScoreBoard> scoreBoards=new PriorityQueue<>();

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int[] getItem() {
        return item;
    }

    public void setItem(int[] item) {
        this.item = item;
    }

    public int[] getBuild() {
        return build;
    }

    public void setBuild(int[] build) {
        this.build = build;
    }

    public PriorityQueue<ScoreBoard> getScoreBoards() {
        return scoreBoards;
    }

    public void setScoreBoards(PriorityQueue<ScoreBoard> scoreBoards) {
        this.scoreBoards = scoreBoards;
    }

    public HashMap<String, Tower> getTowers() {
        return Towers;
    }

    public void setTowers(HashMap<String, Tower> Towers) {
        this.Towers = Towers;
    }
    
}
