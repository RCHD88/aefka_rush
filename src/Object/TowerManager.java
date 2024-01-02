/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;
import MainPackage.Sound;
import Maps.MapsModel;
import PlayerBase.PlayerBase;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author richa
 */
public class TowerManager {
    protected HashMap<String, Tower> towers;
    protected GamePanel gamepanel;
    protected PlayerBase playerbase;
    protected int towerPrice;
    protected ArrayList<BufferedImage> images;
    Sound sound = new Sound();
    
    public TowerManager(GamePanel gamepanel, PlayerBase playerbase){
        towers = new HashMap<>();
        this.gamepanel = gamepanel;
        this.playerbase = playerbase;
        setupTowerImages();
        towerPrice = 150;
    }
    public void setupTowerImages(){
        images = new ArrayList<>();
        try{
            for(int x = 0; x < 11; x++){
                images.add(ImageIO.read(getClass().getResource("/assets/tower/tower" + String.valueOf(x+1) + ".png")));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void addNewTower(int playerX, int playerY){
        int x = playerX / gamepanel.tileSize;
        int y = playerY / gamepanel.tileSize;
        String newkey = String.valueOf(x) + "$" + String.valueOf(y);
        if(MapsModel.checkModel(x, y, "14") && !towers.containsKey(newkey)){
            if(playerbase.getCoin() >= towerPrice){
                x = x * gamepanel.tileSize;
                y = y * gamepanel.tileSize;
                towers.put(newkey, new Tower(x, y, gamepanel.tileSize + gamepanel.tileSize / 2, gamepanel.tileSize));
                playerbase.setCoin(playerbase.getCoin() - towerPrice);
                playSE(3);
            }else{
                System.out.println("not enough coin");
            }
        }else{
            System.out.println("position not valid");
        }
    }
    
    public void update(){
        if(towers.size() > 0){
            for(Map.Entry<String, Tower> entry : towers.entrySet()){
                Tower temp = entry.getValue();
                temp.update(gamepanel.getEnemyManager());
            }
        }
    }
    public void draw(Graphics2D g2){
        if(towers.size() > 0){
            for(Map.Entry<String, Tower> entry : towers.entrySet()){
                Tower temp = entry.getValue();
                temp.draw(g2,images.get(temp.getAnimationIndex()));
            }
        }
    }

    public HashMap<String, Tower> getTowers() {
        return towers;
    }

    public void setTowers(HashMap<String, Tower> towers) {
        this.towers = towers;
    }
    
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
    
}
