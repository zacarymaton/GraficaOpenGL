package com.example.graficos.graficos;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class OpengGLView extends GLSurfaceView{
    public DibujarForma escenario=new DibujarForma();
    public OpengGLView(Context context) {
        super(context);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        init();
    }

    public OpengGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void  init(){
        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setRenderer(escenario);
    }
    private final  float TOUCH_SCALE_FACTOR=180.0f/320;
    private  float previuseX;
    private  float previuseY;
    @Override
    public boolean onTouchEvent( MotionEvent e){

       /* if (e!=null){
            queueEvent(new Runnable() {
                @Override
                public void run() {
                    escenario.girarObjetos(e);
                }
            }); return true;
        }
        return super.onTouchEvent( e );*/
        float x=e.getX();
        float y=e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:
                float dx= x-previuseX;
                float dy= y-previuseY;
                if(y>getHeight()/2){
                    dx=dx*-1;
                }
                if(x<getWidth()/2){
                        dy=dy*-1;
                }
              //renderisamos la escena
                this.escenario.setAngulo(escenario.getAngulo()+((dx+dy))*TOUCH_SCALE_FACTOR);
                requestRender();
            break;



        }
        previuseX=x;
        previuseY=y;
        return  true;
    }


}
