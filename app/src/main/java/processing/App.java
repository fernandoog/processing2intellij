package processing;

import processing.core.*;


public class App extends PApplet {


    float angle;
    float jitter;

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
        translate(width/2, height/2);
        rotate(c);
        rect(0, 0, 180, 180);
    }
    public void settings() {  size(640, 360); }
    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "processing.App" };
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
