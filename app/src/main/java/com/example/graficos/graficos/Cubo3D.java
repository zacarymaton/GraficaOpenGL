package com.example.graficos.graficos;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;



    //Clase donde se establecen los parámetros para construir un cubo en 3 dimensiones.
    public class Cubo3D {


        //Clase donde se establecen los parámetros para construir un cubo en 3 dimensiones.

            //Se declara e inicializa una variable de tipo int, para posteriormente
            //recorrer el número de caras asignándole los colores definidos.
            int carasCubo = 6;
            //Declaramos un buffer de tipo float.
            private FloatBuffer fBuffer;

            //Array de tipo float donde se definen las coordenadas de cada uno de los
            //vértices que forman las diferentes caras del cubo. Un cubo se compone de
            //6 caras, y cada cara presenta 4 vértices con las coordenadas X, Y y Z.
            private float[] vertices = {
                    //Coordenadas cara delantera
                    -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frontal
                    1.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frontal
                    -1.0f, 1.0f, 1.0f,  // 2. izquierda-arriba-frontal
                    1.0f, 1.0f, 1.0f,  // 3. derecha-arriba-frontal


                    //coordenadas de la cara de atras
                    1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atras
                    -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atras
                    1.0f, 1.0f, -1.0f,  // 7. derecha-superior-atras
                    -1.0f, 1.0f, -1.0f,  // 5. izquierda-superior-atras

                    //coordenadas cara izquierda
                    -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atras
                    -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frontal
                    -1.0f, 1.0f, -1.0f,  // 5. izquierda-superior-atras
                    -1.0f, 1.0f, 1.0f,  // 2. izquierda-superior-frontal

                    //coordenadas cara derecha
                    1.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frontal
                    1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atras
                    1.0f, 1.0f, 1.0f,  // 3. derecha-superior-frontal
                    1.0f, 1.0f, -1.0f,  // 7. derecha-superior-atras

                    //coordenadas para cara superior
                    -1.0f, 1.0f, 1.0f,  // 2. izquierda-superior-frontal
                    1.0f, 1.0f, 1.0f,  // 3. derecha-superior-frontal
                    -1.0f, 1.0f, -1.0f,  // 5. izquierda-superior-atras
                    1.0f, 1.0f, -1.0f,  // 7. derecha-superior-atras

                    //coordenadas para cara inferior
                    -1.0f, -1.0f, -1.0f,  // 4. izquierda-abajo-atras
                    1.0f, -1.0f, -1.0f,  // 6. derecha-abajo-atras
                    -1.0f, -1.0f, 1.0f,  // 0. izquierda-abajo-frontal
                    1.0f, -1.0f, 1.0f,  // 1. derecha-abajo-frontal


            };

        // Array de tipo float donde se definen los colores de las 6 caras del cubo.
        //Formato de colores RGBA--> Rojo(R), Verde(V), Azul(B) y grado de transparencia u opacidad (Alpha).
        private float[][] colores = {
                {1.0f, 0.0f, 0.0f, 1.0f},  //Color Rojo
                {0.0f, 1.0f, 0.0f, 1.0f},//Color Verde
                {1.0f, 1.0f, 0.0f, 1.0f},//Color Amarillo
                {0.0f, 0.0f, 1.0f, 1.0f},  //Color Azul
                {1.0f, 0.5f, 0.0f, 1.0f},  //Color Naranja
                {1.0f, 0.0f, 1.0f, 1.0f}   //Color VIoleta
        };



        public Cubo3D(){
                ByteBuffer buffer=ByteBuffer.allocateDirect(vertices.length*4);
                buffer.order(ByteOrder.nativeOrder());
                //conversion a byte de los tipos float
                fBuffer=buffer.asFloatBuffer();
                //se copia los datos dentro del buffer
                fBuffer.put(vertices);
                fBuffer.position(0);
            }

            //Método donde se definen los parámetros para pintar el cubo.
            public void draw(GL10 gl) {

                //Define los polígonos frontal y trasero enfrentados.
                gl.glFrontFace(GL10.GL_CCW);
                gl.glEnable(GL10.GL_CULL_FACE);
                gl.glCullFace(GL10.GL_BACK);
                gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fBuffer);

                //Recorremos el número de caras, para asignarle el color a cada una de ellas.
                for (int cara = 0; cara < carasCubo; cara++) {
                   // gl.glClearColor(colores[cara][0], colores[cara][1], colores[cara][2], colores[cara][3]);
                    gl.glColor4f(colores[cara][0], colores[cara][1], colores[cara][2], colores[cara][3]);
                    gl.glDrawArrays(GL10.GL_LINE_LOOP, cara*4, 4);
                    //gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, cara*4, 4);
                }

                gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
                gl.glDisable(GL10.GL_CULL_FACE);

            }
            }







