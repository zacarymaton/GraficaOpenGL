package com.example.graficos.graficos;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by USUARIO on 23/08/2019.
 */

public class DibujarForma implements Renderer {

    private Cubo3D cubo3D;
    public int ortogonal,perspectiva;
    public volatile float mAngulo;
    public volatile int rotacion;
    //Variables de tipo float para controlar el ángulo de rotación de las figuras representadas.
    public float anguloX, anguloY, anguloZ;
    public float acercarz=17;
    public  int az;
    public float aspect;
    public volatile float escalaX,escalaY,EscalaZ=1f;
    public volatile  float traslacionX,traslacionY,traslacionZ=0f;
    public float acercarx;
    public float acercary;
    public int ay=1;
    private Piramide3D piramide3D;
    //Se declaran e inicializan dos variables de tipo float para establecer los valores del ángulo y velocidad de giro del elemento representado.
    private static float angulo = 65;
    private static float velocidadGiroEjeX = -1.5f;

    public DibujarForma()
    {
        this.cubo3D = new Cubo3D();
        this.piramide3D=new Piramide3D();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        aspect= (float)width / (float)height;
        //definimos el marco
        //Método que fija la vista recibiendo como parámetros la esquina inferior izquierda del rectángulo de visualización, por defecto (0,0), y los parámetros de ancho y alto.
        gl.glViewport(0, 0, width, height);
        //Se indica que se trabajará con la matriz PROJECTION.
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //Se carga la identidad de la matriz.
        gl.glLoadIdentity();
        //Establece la matriz de proyección en perspectiva.
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        if(ortogonal==1){
            Log.i("ENTRO A ORTOGONAL","AHORA MISMO");
            GLU.gluOrtho2D(gl, 4.0f, -4.0f, 4.0f, -4.0f);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            GLU.gluLookAt(gl,0,0,acercarz,0,0,0,0,1,1);

        }else{
            if ((perspectiva==2)){
                Log.i("ENTRO A PERSPECTIVA","AHORA MISMO");
                GLU.gluPerspective(gl, 45.0f, aspect, 0.1f, 100.0f);
                gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
                GLU.gluLookAt(gl,0,0,acercarz,0,0,0,0,1,1);
            }
        }
       gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        //Limpia el buffer para preestablecer los valores de profundidad y color.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

        GLU.gluLookAt(gl,0,0,acercarz,0,0,0,0,1,1);

        if(ortogonal==1){
            Log.i("ENTRO A ORTOGONAL","AHORA MISMO");
            GLU.gluOrtho2D(gl, 15.0f, -15.0f, 15.0f, -15.0f);
            GLU.gluLookAt(gl,0,0,acercarz,0,0,0,0,1,1);


        }
        Log.i("ENTRO A PERSPECTIVA","AHORA MISMO");


switch (rotacion){
    case 1:
        gl.glRotatef(mAngulo,1.0f,0.0f,-0.0f);
        break;
    case 2:
        gl.glRotatef(mAngulo,0.0f,1.0f,0.0f);
        break;
    case 3:
        gl.glRotatef(mAngulo,0.0f,0.0f,1.0f);
        break;
        default:

}

        //Permite establecer el movimiento de translación indicando las coordenadas del eje X, Y y Z.
        gl.glTranslatef(traslacionX,traslacionY,traslacionZ);
        //Permite establecer el tamaño de la figura indicando las coordenadas del eje X, Y y Z.
        gl.glScalef(escalaX, escalaY, EscalaZ);
        //Invocamos el método del objeto cubo3D encargado de establecer los parámetros del gráfico a dibujar.

        cubo3D.draw(gl);

        angulo += velocidadGiroEjeX;



        //Invocamos el método del objeto Piramide3D encargado de establecer los parámetros del gráfico a dibujar.
        gl.glTranslatef(traslacionX-5.0f, traslacionY+0.0f, 0.0f);

        gl.glScalef(1.6f, 1.6f, 1.6f);
       /* gl.glRotatef(anguloX,0.0f,1.0f,0.0f);
        gl.glRotatef(anguloY,0.0f,1.0f,0.0f);
        gl.glRotatef(anguloZ,0.0f,1.0f,0.0f);*/
        piramide3D.draw(gl);


        gl.glTranslatef(traslacionX+6.0f, traslacionY+0.0f, traslacionZ+0.0f);
        //     gl.glTranslatef(-1.0f, 0.0f, -5.0f);
        gl.glScalef(1.0f, 2.2f, 2.3f);
        piramide3D.draw(gl);
        //Esfera





    }

    public float getAngulo(){
        return  this.mAngulo;
    }
    public void setAngulo(float angulo){
        this.mAngulo=angulo;
    }
    public void modificarAngulo(float anguloX, float anguloY, float anguloZ)
    {
        this.anguloX -= anguloX;
        this.anguloY -= anguloY;
        this.anguloZ -= anguloZ;
    }
    public void girarObjetos(MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE://Controla los movimientos por la pantalla.
                anguloX = event.getX();
                anguloY = event.getY();
                Log.d ("DEBUG","ACTION_MOVE");
                break;

            case MotionEvent.ACTION_DOWN://Controla la pulsación de la pantalla.
                modificarAngulo(anguloX - event.getX(), anguloY - event.getY(),0.0f);
                anguloX = event.getX();
                anguloY = event.getY();
                Log.d("DEBUG","ACTION_DOWN");
                break;

            case MotionEvent.ACTION_UP://Controla cuando dejas de pulsar la pantalla.
                modificarAngulo(anguloX + event.getX(), anguloY + event.getY(),0.0f);
                anguloX = event.getX();
                anguloY = event.getY();
                Log.d("DEBUG","ACTION_UP");
                break;

            default:
                break;
        }
    }

}
