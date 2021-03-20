package ui.compartments;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import model.*;
import persistence.*;
import ui.WeightPacerApp;

public class IntroSound {

    // TODO: Took inspiration from
    //  https://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
    //  as this is truly the best way to include my desired functionality of having a intro jingle/sound.
    public static void introSound() {
        String soundName = "./data/IntroSound.wav";
        try {
            AudioInputStream introSound = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(introSound);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
