/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HighscoreData;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author richardwei
 */
public class HighscoreBanner {
    protected int height,width,x,y;
    protected int leaderboardButtonX, leaderboardButtonY, leaderboardButtonSize;
    protected int exitButtonX, exitButtonY, exitButtonSize;
    protected BufferedImage buttonImage;
    protected BufferedImage exitImage;
    protected Font font;
    protected final int maxSize = 5;
    protected PriorityQueue<ScoreData> highscoreData;
    
    public HighscoreBanner(){
        highscoreData = new PriorityQueue<>();
        setupDefault();
    }
    public void setupDefault(){
        try {
            buttonImage = ImageIO.read(getClass().getResource("/assets/banner/leaderboard.png"));
            exitImage = ImageIO.read(getClass().getResource("/assets/banner/back.png"));
            
            InputStream inputstream = getClass().getResourceAsStream("/assets/font/LuckiestGuy-Regular.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, inputstream);
            
        } catch (IOException ex) {
            Logger.getLogger(HighscoreBanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FontFormatException ex) {
            Logger.getLogger(HighscoreBanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addNewData(int value){
        ScoreData newdata = new ScoreData(value);
        if(highscoreData.size() >= maxSize){
            int lastScore = highscoreData.peek().getScore();
            if(value > lastScore){
                highscoreData.poll();
                highscoreData.offer(newdata);
            }
        }else{
            highscoreData.offer(newdata);
        }
    }
    public ArrayList<ScoreData> getData(){
        PriorityQueue<ScoreData> helper = new PriorityQueue<>();
        ArrayList<ScoreData> datas = new ArrayList<>();
        while(!highscoreData.isEmpty()){
            ScoreData temp = highscoreData.poll();
            datas.add(temp);
            helper.add(temp);
        }
        highscoreData = helper;
        return datas;
    }
    public void setLeaderboardButton(int x, int y, int size){
        leaderboardButtonX = x;
        leaderboardButtonY = y;
        leaderboardButtonSize = size;
    }
    public void setBackButton(int x, int y, int size){
        exitButtonX = x;
        exitButtonY = y;
        exitButtonSize = size;
    }
    public void drawLeaderboardButton(Graphics2D g2){
        g2.drawImage(buttonImage, leaderboardButtonX, leaderboardButtonY, leaderboardButtonSize, leaderboardButtonSize, null);
    }
    public void drawExitButton(Graphics2D g2){
        g2.drawImage(exitImage, exitButtonX, exitButtonY, exitButtonSize, exitButtonSize, null);
    }
    public void drawScoreBoard(Graphics2D g2){
        int startPosX = exitButtonX + exitButtonSize + 80;
        int startPosY = exitButtonY;
        int marginBottonTitle = 16;
        int marginText = 10;
        String text = "Leaderboard";
        
        g2.setColor(new Color(255,255,204));
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 64F));
        startPosY += g2.getFontMetrics().getAscent();
        
        g2.drawString(text, startPosX, startPosY);
        startPosY += g2.getFontMetrics().getHeight() + marginBottonTitle;
        startPosX += 40;
        
        ArrayList<ScoreData> datas = getData();
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 36F));
        if(datas.size() == 1){
            for(int x = datas.size()-1; x >= 0; x--){
                text = String.valueOf(datas.size() - x) + ". " + datas.get(x).toString();
                g2.drawString(text, startPosX, startPosY);
                startPosY += g2.getFontMetrics().getHeight() + marginText;
            }
        }else{
            text = "! no data available !";
            g2.drawString(text, startPosX, startPosY);
        }
    }
    
    public boolean isButtonPressed(int x, int y){
        if(leaderboardButtonX <= x && x <= leaderboardButtonX + leaderboardButtonSize){
            if(leaderboardButtonY <= y && y <= leaderboardButtonY + leaderboardButtonSize){
                return true;
            }
        }
        return false;
    }
    public boolean isExitButtonPressed(int x, int y){
        if(exitButtonX <= x && x <= exitButtonX + exitButtonSize){
            if(exitButtonY <= y && y <= exitButtonY + exitButtonSize){
                return true;
            }
        }
        return false;
    }
}
