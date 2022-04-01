package processing;

import SimpleOpenNI.SimpleOpenNI;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.sketchs.HotPoint;

import java.awt.*;

/**
 * Main Application Class
 */
public class App extends PApplet {

    public static final String FULL_SCREEN = "--present";
    public static final String WINDOW_COLOR = "--window-color=#666666";
    public static final String STOP_COLOR = "--stop-color=#cccccc";
    public static final String CLASS_NAME = "processing.App";

    float angle;
    float jitter;


    // variable de la maquina de estado
    int estado = 0;

    // biblioteca SimplepenNI
    SimpleOpenNI kinect;

    //clases de la biblioteca minim
    Minim minim;
    //AudioOutput out;

    /// inicializando el arreglo de sonidos
    AudioPlayer[][] sonidosLiquidos;
    //int cantSonidos = col*row;

    //estos son los sonidos de efectos como la caminada

    int cantFx = 3;
    AudioSample[] fx;

    // arreglo de strings de los nombres de los archivos de audio
    String[][] tracks =
            {
                    {
                            "00_A1_liq13.mp3", "00_B1_oliendo_el_sonido_Matrioska01.mp3", "00_C1_liq18.mp3", "00_D1_liq8.mp3"
                    }
                    ,
                    {
                            "00_A2_liq3.mp3", "00_B2_liq5.mp3", "00_C2_oliendo_el_sonido_Matrioska00.mp3", "00_D2_liq10.mp3"
                    }
                    ,
                    {
                            "00_A3_oliendo_el_sonido_Matrioska03.mp3", "00_B3_oliendo_el_sonido_Matrioska02.mp3", "00_C3_liq7.mp3", "00_D4_liq14.mp3"
                    }
                    ,
                    {
                            "00_A4_liq11.mp3", "00_B4_liq16.mp3", "00_C4_liq9.mp3", "00_D4_liq19.mp3"
                    }
            };

    String[] tracksFx = {
            "al8.mp3", "al8.mp3", "al8.mp3"
    };

    // arreglo de botones liquida
    HotPoint[][] botLiq;

    int row = 4;
    int col = 4;


    ////contrones de camara

    float rotX = radians(180);
    float rotY = radians(0);


    // ALTURA DE LA RETICULA
    // lo manejamos con las letras 'U' e 'I'

    // elevacion inicial
    int elevacion = -500;

    // rango de aumento para ajustar la elevacion
    int aumentoElev = 10;


    //dimensiones del boton
    int tamano = 600;

    //distancia de la camara al inicio de la reticula

    // lo manejamos con las letras  'G' y 'H'
    int distRet = 1200;

    // pasos de juste de la reticula a liquida
    int distRetAumento = 20;


    // este es el control del zoom y se hace con "S" y "A"
    float s = 1;

    //resolucion de la nube de puntos "M" Y "N"
    int pasos = 7;

    // Se controla la profundidad de rastreo con "L" y "K"
    int profundidad = 3000;

    PImage portadilla;

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
        kinect.update();
        background(0);


        translate(width/2, height/2, -1000);
        rotateX(rotX);


        translate(0, 0, 1400);
        rotateY(rotY);

        translate(0, 0, s*-1000);
        scale(s);

        stroke(255);

        PVector[] depthPoints = kinect.depthMapRealWorld();

        switch(estado) {

            case 0:

                for (int i = 0; i < depthPoints.length; i+=pasos) {
                    PVector currentPoint = depthPoints[i];
                    if (currentPoint.z < profundidad)
                        point(currentPoint.x, currentPoint.y, currentPoint.z);
                    //vertex(currentPoint.x, currentPoint.y, currentPoint.z);
                }

                for (int i = 0; i < row; i++)
                {
                    for (int j = 0; j < col; j++)
                    {
                        sonidosLiquidos[i][j].mute();

                    }
                }

                break;

            case 1:


                // beginShape(POINTS);

                // esta es la parte que dibuja todo
                // la nube de puntos y los botones hotPoint
                for (int i = 0; i < depthPoints.length; i+=pasos)
                {
                    PVector currentPoint = depthPoints[i];
                    // esta parte dibuja los puntos de la nube de puntos
                    if (currentPoint.z < profundidad)
                    {

                        point(currentPoint.x, currentPoint.y, currentPoint.z);
                        //vertex(currentPoint.x, currentPoint.y, currentPoint.z);
                        // almacena el punto actual del arreglo de la nube de puntos


                        // dibuja los hotPoints en columnas y filas
                        for (int k = 0; k < row; k++)
                        {
                            for (int l = 0; l < col; l++)
                            {
                                botLiq[k][l].check(currentPoint);
                            }
                        }
                    }
                }
                // endShape();



                // esta parte revisa si el boton esta siendo activado, dispara el sonido
                for (int k = 0; k < row; k++)
                {
                    for (int l = 0; l < col; l++)
                    {
                        // en esta parte se activan los sonidos por su correspondiente posisicion
                        // aqui se puede anexar un solo sonido que pueda corresponder a una accion constante como caminar
                        if (botLiq[k][l].isHit())
                        {
                            //sonidosLiquidos[k][l].loop();
                            if (sonidosLiquidos[k][l].isMuted() )
                            {
                                sonidosLiquidos[k][l].unmute();
                            }
                            else
                            {
                                // simply call loop again to resume playing from where it was paused
                                sonidosLiquidos[k][l].mute();
                            }


                            fx[0].trigger();
                        }
                    }
                }

                // aqui se borran los puntos dibujados y se limpia la informacion del current point
                for (int k = 0; k < row; k++)
                {
                    for (int l = 0; l < col; l++)
                    {
                        botLiq[k][l].draw();
                        botLiq[k][l].clear();
                    }
                }


                break;


            // cualquier otra entrada solo sigue dibujando la nube de puntos
            default:

                for (int i = 0; i < depthPoints.length; i+=pasos) {
                    PVector currentPoint = depthPoints[i];
                    if (currentPoint.z < profundidad)
                        point(currentPoint.x, currentPoint.y, currentPoint.z);
                    //vertex(currentPoint.x, currentPoint.y, currentPoint.z);
                }


                break;

            //termina el switch
        }

        // draw the kinect cam
        kinect.drawCamFrustum();
    }

    public void settings() {
        size(640, 360);
    }

    class Hotpoint {
        PVector center;
        Color fillColor;
        Color strokeColor;
        int size;
        int pointsIncluded;
        int maxPoints;
        boolean wasJustHit;
        int threshold;

        Hotpoint(float centerX, float centerY, float centerZ, int boxSize) {

            center = new PVector(centerX, centerY, centerZ);
            size = boxSize;
            pointsIncluded = 0;
            maxPoints = 1000;
            threshold = 50;
            fillColor = strokeColor = new Color(random(255), random(255), random(255));

        }

        void setThreshold(int newThreshold) {
            threshold = newThreshold;
        }

        void setMaxPoints(int newMaxPoints) {
            maxPoints = newMaxPoints;

        }

        void setColor(float red, float green, float blue) {
            fillColor = strokeColor = new Color(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue());
        }

        boolean check(PVector point) {
            boolean result = false;

            if (point.x > center.x - size / 2 && point.x < center.x + size / 2) {
                if (point.y > center.y - size / 2 && point.y < center.y + size / 2) {
                    if (point.z > center.z - size / 2 && point.z < center.z + size / 2) {
                        result = true;
                        pointsIncluded++;
                    }
                }
            }
            return result;
        }
    }
}
