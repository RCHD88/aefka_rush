/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import MainPackage.*;
import Object.Player;
import Object.PlayerBuild;
import PlayerBase.Item;
import PlayerBase.PlayerBase;
import java.io.FileOutputStream;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class SaveLoad implements Serializable{
    GamePanel gp;
    DataStorage data;
    public SaveLoad(GamePanel gp) {
            this.gp = gp;
            data= new DataStorage();
    }
    public void save() {
        PlayerBase temp=gp.getPlayerBase();
        data.gold=temp.getCoin();
        data.life=temp.getLife();
        data.stage=temp.getStageNumber();
        data.Towers=gp.getTowermanager().getTowers();
        System.out.println(data.Towers.size());
        for (int i = 0; i < temp.getAllitems().size(); i++) {
             data.build[i]=temp.getPlayer().getBuilds().get(i).getLevel();
        }
        for (int i = 0; i < temp.getAllitems().size(); i++) {
             data.item[i]=temp.getAllitems().get(i).getLevel();
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("SaveFile.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(data);
            out.close();
            fileOut.close();
            System.out.println("Saved");
        } catch (Exception e) {
            System.out.println("save failed");
            e.printStackTrace();
        }
    }
    public DataStorage load(){
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(new File("SaveFile.dat")));
             data=(DataStorage)in.readObject();
            System.out.println("Load Successful");
        }catch(Exception e){
            System.out.println("load failed");
        }
        return data;
    }
    public void addScore(int stage){
        data.getScoreBoards().add(new ScoreBoard(stage));
    }
    
}
