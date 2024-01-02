/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Object;

import MainPackage.GamePanel;
import MainPackage.Sound;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author richa
 */
public class Tower extends Object implements Serializable{
    // HOT CONDITION & RANGE
    protected boolean shootable;
    protected int maxRange = 5 * 60;
    //COOLDOWN SHOOT
    protected int cooldownShoot = 60;
    protected int currCooldown = 1;
    // SHOT DIRECTION
    protected String shootDirection = "4";
    // MAGAZINE
    protected ArrayList<Projectile> magazine;
    // TOWER ANIMATION
    protected int animationCooldown = 10;
    protected int currAnimationCD;
    protected final int maxAnimationIndex = 11;
    protected int currAnimationIndex;
    
    
    public Tower(int x, int y, int height, int width){
        setupNewTower(x,y,height,width);
        magazine = new ArrayList<>();
        shootable = false;
        currAnimationCD = 1;
        currAnimationIndex = 0;
    }
    public void setupNewTower(int x, int y, int height, int width){
        posX = x;
        posY = y;
        this.height = height;
        this.width = width;
    }
    
    public void update(EnemyManager EManager) {
        // UPDATE ANIMATION 
        currAnimationCD++;
        if(currAnimationCD == animationCooldown){
            currAnimationIndex++;
            if(currAnimationIndex == maxAnimationIndex){
                currAnimationIndex = 0;
            }
            currAnimationCD = 1;
        }
        // ADD NEW MAGAZINE 
        checkShootableArea(EManager);
        if(shootable){
            currCooldown++;
            if(currCooldown == cooldownShoot){
                addMagazine(shootDirection);
                currCooldown = 1;
            }
        }
        // UPDATE MAGAZINE POSITION
        for(int x = 0; x < magazine.size(); x++){
            Projectile currMagazine = magazine.get(x);
            currMagazine.update();
            while(magazine.size() > 0 && (currMagazine.outOfRange(maxRange) || currMagazine.isHitEnemy(EManager))){
                magazine.remove(x);
            }
        }
    }
    public void checkShootableArea(EnemyManager EManager){
        if(EManager.checkAttackedArea(posX-maxRange, posY, maxRange, height, 0)){
            shootable = true;
            shootDirection = "3";
        }else if(EManager.checkAttackedArea(posX, posY-maxRange, width, maxRange, 0)){
            shootable = true;
            shootDirection = "1";
        }else if(EManager.checkAttackedArea(posX, posY, maxRange, height, 0)){
            shootable = true;
            shootDirection = "4";
        }else if(EManager.checkAttackedArea(posX, posY, width, maxRange, 0)){
            shootable = true;
            shootDirection = "2";
        }else{
            shootable = false;
        }
    }
    public void addMagazine(String code){
        int x,y,h,w;
        x = posX + width / 2;
        y = posY + height / 4;
        if(code.equals("1") || code.equals("2")){
            w = 40;
            h = 60;
        }else{
            w = 60;
            h = 40;
        }
        BufferedImage magazineImage = null;
        try {
            magazineImage = ImageIO.read(getClass().getResource("/assets/tower/magazine"+code+".png"));
        } catch (IOException ex) { ex.printStackTrace(); }
        Projectile newmagazine = new Projectile(x, y, w, h, magazineImage, Integer.parseInt(code));
        magazine.add(newmagazine);
    }
    
    public void draw(Graphics2D g2, BufferedImage image){
        g2.drawImage(image, posX, posY - height / 3 - 10, width, height, null);
        // draw magazine
        for(int x = 0; x < magazine.size(); x++){
            magazine.get(x).draw(g2);
        }
    }
    
    // GETTER SETTER

    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getAnimationIndex(){
        return currAnimationIndex;
    }
}
