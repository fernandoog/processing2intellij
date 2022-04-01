package processing.sketchs;

import SimpleOpenNI.SimpleOpenNI;
import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Main Application Class
 */
public class VinculoSentido extends PApplet {

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
