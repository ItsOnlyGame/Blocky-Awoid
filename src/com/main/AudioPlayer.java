package com.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {

  public static Map<String, String> soundMap = new HashMap<String, String>();
  public static Map<String, String> musicMap = new HashMap<String, String>();

  private static Clip clip;

  public static void load() {
    musicMap.put("GameMusic", "/res/GameSound.wav");
    musicMap.put("GameMusic_E", "/res/GameSound_E.ogg");
    musicMap.put("GameMusic_H", "/res/GameSound_Hard.ogg");

    musicMap.put("MainMenu", "/res/MainMenu.wav");
  }

  public static void playMusic(final String id) {
    try {
      File musicFile = new File(System.getProperty("user.dir") + musicMap.get(id));

      AudioPlayer.stopMusic();
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicFile);
      clip = AudioSystem.getClip();
      clip.open(inputStream);
      clip.start();
    } catch (Exception e) {
      stopMusic();
      System.err.println(e.getMessage());
    }
  }

  public static void stopMusic() {
    if (clip != null) {
      clip.stop();
      clip.close();
      clip = null;
    }
  }

}
