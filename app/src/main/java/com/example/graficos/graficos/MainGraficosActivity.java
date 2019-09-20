package com.example.graficos.graficos;

import android.content.pm.ActivityInfo;
import android.opengl.GLU;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.microedition.khronos.opengles.GL10;

public class MainGraficosActivity extends AppCompatActivity implements View.OnClickListener  {


private OpengGLView opengGLView;
public Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_graficos);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        opengGLView=(OpengGLView)findViewById(R.id.openGLView);
        bt1=(Button)findViewById(R.id.btAcercar);
        bt2=(Button)findViewById(R.id.btAlejar);
        bt3=(Button)findViewById(R.id.btOrtogonal);
        bt4=(Button)findViewById(R.id.btPerspectiva);
        bt5=(Button)findViewById(R.id.btRZ);
        bt6=(Button)findViewById(R.id.btRX);
        bt7=(Button)findViewById(R.id.btRY);
        bt8=(Button)findViewById(R.id.btTamaño);
        bt9=(Button)findViewById(R.id.btTamaño2);
        bt10=(Button)findViewById(R.id.btTraslasion);
        bt11=(Button)findViewById(R.id.btTraslasion2);

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proyeccion Ortogonal",Toast.LENGTH_SHORT).show();
                opengGLView.requestRender();
                opengGLView.escenario.ortogonal=1;
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proyeccion Perspectiva",Toast.LENGTH_SHORT).show();
                opengGLView.requestRender();
                opengGLView.escenario.ortogonal=0;
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Rotacion en Z",Toast.LENGTH_SHORT).show();
                opengGLView.escenario.rotacion=3;
            }
        });
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Rotacion en X",Toast.LENGTH_SHORT).show();
                opengGLView.escenario.rotacion=1;

            }
        });
        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Rotacion en Y",Toast.LENGTH_SHORT).show();
                opengGLView.escenario.rotacion=2;
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Mayor escalacion",Toast.LENGTH_SHORT).show();
                opengGLView.escenario.escalaX+=1;
                opengGLView.escenario.escalaY+=1;
                opengGLView.escenario.EscalaZ+=1;
            }
        });
        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Menor escalacion",Toast.LENGTH_SHORT).show();
                opengGLView.escenario.escalaX-=1;
                opengGLView.escenario.escalaY-=1;
                opengGLView.escenario.EscalaZ-=1;
            }
        });
        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengGLView.escenario.traslacionX+=1;
                opengGLView.escenario.traslacionY+=1;
                opengGLView.escenario.traslacionZ+=1;
            }
        });
        bt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengGLView.escenario.traslacionX-=1;
                opengGLView.escenario.traslacionY-=1;
                opengGLView.escenario.traslacionZ-=1;
            }
        });
    }
    @Override
    protected  void onResume(){
        super.onResume();
        opengGLView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        opengGLView.onPause();
    }

    public void Asercar(View view) {
        Toast.makeText(this,"Acercar",Toast.LENGTH_LONG).show();
    opengGLView.escenario.az=3;
    opengGLView.escenario.acercarz-=2;

    }

    public void Alejar(View view) {
        Toast.makeText(this,"Alejar",Toast.LENGTH_LONG).show();
        opengGLView.escenario.acercarz+=2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btOrtogonal:
                Toast.makeText(this,"Proyeccion Ortogonal",Toast.LENGTH_LONG).show();
                opengGLView.requestRender();
                opengGLView.escenario.ortogonal=1;
                break;
            case R.id.btPerspectiva:
                Toast.makeText(this,"Proyeccion Perspectiva",Toast.LENGTH_LONG).show();
                opengGLView.requestRender();
                opengGLView.escenario.perspectiva=2;
                break;
                default:

        }
    }


}
