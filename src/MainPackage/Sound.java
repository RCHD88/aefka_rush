package MainPackage;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author kelun
 */
public class Sound {
    Clip clip;
    URL soundURL []= new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/Sound/background_sound.wav");
        soundURL[1] = getClass().getResource("/Sound/hit_sound.wav");
        soundURL[2] = getClass().getResource("/Sound/pindah_sound.wav");
        soundURL[3] = getClass().getResource("/Sound/build_sound.wav");
        soundURL[4] = getClass().getResource("/Sound/buy_sound.wav");
        soundURL[5] = getClass().getResource("/Sound/atk_sound.wav");
        soundURL[6] = getClass().getResource("/Sound/tower_sound.wav");
        soundURL[7] = getClass().getResource("/Sound/game_sound.wav");
        soundURL[8] = getClass().getResource("/Sound/dead_sound.wav");
        soundURL[9] = getClass().getResource("/Sound/explosion_sound.wav");
        soundURL[10] = getClass().getResource("/Sound/mouse_sound.wav");
        soundURL[11] = getClass().getResource("/Sound/upgrade_sound.wav");
        
    }
   
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            
        }
    }
    
    public void play(){
        clip.start();
    }
    
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    
    public void stop(){
        clip.stop();
    }
}
