/**
 * @author William
 * @date 3/9/15
 * 
 * This program is a demo of the basic abilities of the entity using the basic setting different properties to one of the square and showing a reference square to show the changes
 * 
 */

import static org.lwjgl.opengl.GL11.*; //Import GL11 for 2d graphics

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;//import use of keyboard
import org.lwjgl.opengl.*;

import entities.AbstractGravityEntity;
import entities.Entity;
import entities.GravityEntity;


public class gravityDemo {	
	public gravityDemo(){
		//create a openGL instance in the thread
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Gravity Demo");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		
		
		GravityEntity box = new Box (15,15,100,100, "wood"); //changing box
		Entity test = new Box (200,200,100,100, "wood"); //reference box
		//Set up Window
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		//code loop if the window is closed the loop will end
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			//code in loop
			//assures reset of all attributes
			box.setDX(0);
			box.setDY(box.Gravity(box.getDY()));;
			box.setWidth(100);
			box.setHeight(100);
			box.setTexture("wood");
			//changes to attributes
			if (Keyboard.isKeyDown(Keyboard.KEY_1)){
				box.setDX(.1);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_2)){
				box.setDY(.1);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_3)){
				box.setDX(-.1);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_4)){
				box.setDY(-.1);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_5)){
				box.setWidth(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_6)){
				box.setHeight(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_7)){
				box.setTexture("texture");
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_8)){
				box.setGravC(.01); //changes gravity constant used for acceleration
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_9)){
				box.setGravC(.05); //changes gravity constant used for acceleration
			}
			if (box.getY() >= 480-box.getHeight() && box.getDY()>0){ //tests leaving bottom of the screen will bounce off the bottom unless controlled
				box.setY(480-box.getHeight());
				if (box.getDY()<.25){
					box.setDY(0);
				}else{
					box.setDY(-(box.getDY()/1.5));
				}
			}
			//uses auto update feature
			box.update();
			/*
			 * uncomment following section for equal move-rate for each frame no matter frame rate();
			 */
			//box.update(<integer>);
			
			System.out.println("Colliding? "+ box.intersects(test)); //tests collision
			
			box.draw();
			test.draw();
			
			Display.update();
		}
		Display.destroy();
	}
	
	//box object
	private static class Box extends AbstractGravityEntity{ 
		
		public Box(double x, double y, double width, double height,String key) {
			super(x, y, width, height, key);
			setTexture(key);
			
		}

		@Override
		//draw 
		public void draw() {
			tex.bind();
			
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2d(x,y);
				glTexCoord2f(1,0);
				glVertex2d(x+width,y);
				glTexCoord2f(1, 1);
				glVertex2d(x+width,y+height);
				glTexCoord2f(0, 1);
				glVertex2d(x,y+height);
			glEnd();
		}
	}
	
	public static void main(String[] args) {
		new gravityDemo();

	}

}

