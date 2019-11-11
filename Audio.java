import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Handles playing the audio for the Game. Include play, pause, resume, and restart
 */
public class Audio {

    private Long currentFrame;
    private Clip clip;
    private String status;
    private static String filePath;

    /**
     * Constructs a Audio
     * @param filepath the filepath to the audio file
     */
    public Audio(String filepath) {
        this.filePath = filepath;

        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                System.out.println("Can't find file");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the audio file
     */
    public void play() {
        clip.start();
        status = "play";
    }

    /**
     * Pauses the audio file
     */
    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    /**
     * Stops playing the audio file
     */
    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
}