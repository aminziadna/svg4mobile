/* 
 * svg4mobile - A project to use SVG files in mobile devices
 * Copyright (C) 2010  Daniel Lahoz, Daniel Rivera, Álvaro Tanarro and Luis Torrrico
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.grub.svg4mobile;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;

/**
* @author Daniel Lahoz
* @author Daniel Rivera
* @author Álvaro Tanarro
* @author Luis Torrrico
* @version 0.1
*/
public class Svg4mobile extends Activity {
    /**
     * Called when the activity is first created.
     */
	private OpenGLRenderer renderer;
    private static final float ANGLE_DIFF = .1f;
	Menu myMenu = null;
	
	
    public static final int archivo = Menu.FIRST;
    public static final int objetos = Menu.FIRST+1;
    public static final int caracteristicas = Menu.FIRST+2;
    public static final int opciones = Menu.FIRST+3;
    public static final int ayuda = Menu.FIRST+4;
    public static final int salir = Menu.FIRST+5;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	Log.v("svg4mobile", "prueba");
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		GLSurfaceView view = new GLSurfaceView(this);
 		this.renderer = new OpenGLRenderer();
   		view.setRenderer(this.renderer);
   		setContentView(view);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      //CharSequence dialogString;
      switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_CENTER: {
          // Resetea la posición de la cámara
        	this.renderer.camReset();
          break;
        }        
        case KeyEvent.KEYCODE_DPAD_LEFT: {
          // Mueve la cámara hacia la derecha
        	this.renderer.camLeft();
          break;
        }
        case KeyEvent.KEYCODE_DPAD_RIGHT: {
          // Mueve la cámara hacia la izquierda
          this.renderer.camRight();
          break;
        }
        case KeyEvent.KEYCODE_DPAD_UP: {
          // Mueve la cámara hacia abajo
          //this.renderer.zoomOut();
        	this.renderer.camUp();
          break;
        }
        case KeyEvent.KEYCODE_DPAD_DOWN: {
        	// Mueve la cámara hacia arriba
        	//this.renderer.zoomIn();
        	this.renderer.camDown();
        	break;
        }
        case KeyEvent.KEYCODE_BACK: {
        	// Sale del programa
        	finish();
        }
      }
      return true;
    }
    
    public boolean onTouchEvent(MotionEvent event)
    {
            int startX=0;
            int startY=0;
            int endX;
            int endY;
            int resY;
            int resX;
            // float mAngle_x=0;
            // float mAngle_y=0;
            switch (event.getAction())
            {
                   
            case MotionEvent.ACTION_DOWN:
                       
                    startX= (int)event.getX();
                    startY= (int)event.getY();                         
                    return true;

            case MotionEvent.ACTION_MOVE:
                       
                    endX = (int)event.getX();
                    endY= (int)event.getY();
                    resY= endY-startY;
                    resX= endX-startX;
                    
                    /*if((endY-startY)<0)
                    {
                        resY= -(endY-startY);
                    }
                    else
                    {
                        resY= (endY-startY);
                    }
                   
                    if((endX -startX < 0))
                    {
                        resX= -(endX-startX);
                    }
                            else
                    {
                        resX = (endX-startX);
                    }*/
                   
                       
                    if(resX <  0)
                    {
                        this.renderer.camRight();
                    } else {
                    	this.renderer.camLeft();
                    }
                       
                       
                    if(resY<0)
                    {
                    	this.renderer.camUp();
                    } else {
                    	this.renderer.camDown();
                    }
                    Log.v("svg4mobile", "startX: " + startX + ", startY: " + startY);
                    Log.v("svg4mobile", "endX: " + endX + ", endY: " + endY);

                    Log.v("svg4mobile", "resX: " + resX + ", resY: " + resY);
                    //obj.rotateY(mAngle_y); 
                    //obj.rotateZ(mAngle_x);
                    //this.renderer.setY(resY/100);
                    //this.renderer.setX(resX/100);
                    return true;
                                                               
            }
                   
        return false;
    }

}