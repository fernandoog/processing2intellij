package processing.sketchs;

import processing.core.PApplet;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Main Application Class
 */
public class RecordSound extends PApplet {

    public static final String FULL_SCREEN = "--present";
    public static final String WINDOW_COLOR = "--window-color=#666666";
    public static final String STOP_COLOR = "--stop-color=#cccccc";
    public static final String CLASS_NAME = "processing.Sketch";

    float angle;
    float jitter;

    AudioFileFormat.Type aFF_T = AudioFileFormat.Type.WAVE;
    AudioFormat aF = new AudioFormat(8000.0F, 16, 1, true, false);
    TargetDataLine tD;
    File f = new File("Grabacion.wav");

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{FULL_SCREEN, WINDOW_COLOR, STOP_COLOR, CLASS_NAME};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

    public void setup() {

        noStroke();
        fill(255);
        rectMode(CENTER);
    }

    public void draw() {
        try {
            DataLine.Info dLI = new DataLine.Info(TargetDataLine.class, aF);
            tD = (TargetDataLine) AudioSystem.getLine(dLI);
            new CapThread().start();
            System.out.println("Grabando durante 10s...");
            Thread.sleep(10000);
            tD.close();
        } catch (Exception e) {
        }
    }

    public void settings() {
        size(640, 360);
    }

    class CapThread extends Thread {
        public void run() {
            try {
                tD.open(aF);
                tD.start();
                AudioSystem.write(new AudioInputStream(tD), aFF_T, f);
            } catch (Exception e) {
            }
        }
    }

}
