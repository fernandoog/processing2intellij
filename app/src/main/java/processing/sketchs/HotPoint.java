package processing.sketchs;

import processing.core.PVector;
import java.awt.*;

import static jdk.nashorn.internal.objects.NativeMath.random;
import static processing.core.PApplet.map;

import  processing.core.PGraphics;

public class HotPoint{
    PVector center;
    Color fillColor;
    Color strokeColor;
    PGraphics pGraphics;
    int size;
    int pointsIncluded;
    int maxPoints;
    boolean wasJustHit;
    int threshold;

    HotPoint(float centerX, float centerY, float centerZ, int boxSize){

        center = new PVector (centerX, centerY, centerZ);
        size = boxSize;
        pointsIncluded = 0;
        maxPoints = 1000;
        threshold = 50;
        fillColor = strokeColor = new Color((int) random(255), (int) random(255), (int) random(255));

    }

    void setThreshold(int newThreshold){
        threshold = newThreshold;
    }

    void setMaxPoints(int newMaxPoints){
        maxPoints = newMaxPoints;

    }

    void setColor(float red, float green, float blue){
        fillColor = strokeColor = new Color(red, green, blue);
    }

    public boolean check(PVector point){
        boolean result = false;

        if(point.x > center.x - size/2 && point.x < center.x + size/2){
            if(point.y > center.y - size/2 && point.y < center.y + size/2){
                if(point.z > center.z - size/2 && point.z < center.z + size/2){
                    result = true;
                    pointsIncluded++;
                }
            }
        }
        return result;
    }

    public void draw(){
        pGraphics.pushMatrix();
        pGraphics.translate(center.x, center.y, center.z);

        pGraphics.fill(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 255*percentIncluded());
        pGraphics.stroke(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue(), 255);
        pGraphics.box(size);
        pGraphics.popMatrix();
    }

    float percentIncluded(){
        return map(pointsIncluded, 0, maxPoints, 0, 1);
    }

    boolean currentlyHit(){
        return (pointsIncluded > threshold);
    }

    public boolean isHit(){
        return currentlyHit() && !wasJustHit;
    }

    public void clear(){
        wasJustHit = currentlyHit();
        pointsIncluded = 0;

    }
}