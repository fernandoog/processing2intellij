package processing.sketchs;

import processing.core.PApplet;

/**
 * Main Application Class
 */
public class DrawSquare extends PApplet {

    public static final String FULL_SCREEN = "--present";
    public static final String WINDOW_COLOR = "--window-color=#666666";
    public static final String STOP_COLOR = "--stop-color=#cccccc";
    public static final String CLASS_NAME = "processing.Sketch";

    float angle;
    float jitter;

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
        background(51);

        // during even-numbered seconds (0, 2, 4, 6...)
        if (second() % 2 == 0) {
            jitter = random(-0.1f, 0.1f);
        }
        angle = angle + jitter;
        float c = cos(angle);
        translate(width / 2, height / 2);
        rotate(c);
        rect(0, 0, 180, 180);
    }

    public void settings() {
        size(640, 360);
    }
}
