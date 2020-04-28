package com.company;

import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {
    private Clip clip;
    public Sound(String path){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
            AudioFormat baseFormat =ais.getFormat();
            AudioFormat decodFormat =new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,baseFormat.getSampleRate(),
                    16,baseFormat.getChannels(),baseFormat.getChannels()*2,baseFormat.getSampleRate(),false);
            AudioInputStream dais =AudioSystem.getAudioInputStream(decodFormat,ais);
            clip=AudioSystem.getClip();
            clip.open(dais);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public  void  play(){
        if(clip==null)return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    public void close(){
        stop();
        clip.close();
    }
    public  void stop(){
        if (clip.isRunning()) clip.stop();
    }
}
