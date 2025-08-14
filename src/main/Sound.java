package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[100];

    public Sound(){

        soundURL[0] = getClass().getClassLoader().getResource("sound/overworld.wav");
        soundURL[1] = getClass().getClassLoader().getResource("sound/door_opening.wav");
        soundURL[2] = getClass().getClassLoader().getResource("sound/fling_head.wav");
        soundURL[3] = getClass().getClassLoader().getResource("sound/hurt/receive_damage.wav");
        soundURL[4] = getClass().getClassLoader().getResource("sound/hurt/fluffles_death.wav");
        soundURL[5] = getClass().getClassLoader().getResource("sound/hurt/fluffles_hurt.wav");
        soundURL[6] = getClass().getClassLoader().getResource("sound/level_up.wav");
        soundURL[7] = getClass().getClassLoader().getResource("sound/cursor.wav");
        soundURL[8] = getClass().getClassLoader().getResource("sound/pickup.wav");
        soundURL[9] = getClass().getClassLoader().getResource("sound/equip.wav");
        soundURL[10] = getClass().getClassLoader().getResource("sound/drink.wav");
        soundURL[11] = getClass().getClassLoader().getResource("sound/burning.wav");
    }

    public void setFile(int i){
        try{

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e){

        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
