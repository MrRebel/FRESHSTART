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

import entities.AbstractEntity;//import AbstactEntity for extension
import entities.Entity;//import Entity object

public class entityDemo {	
	public entityDemo(){
		//create a openGL instance in the thread
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Entity Demo");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		
		Entity box = new Box (15,15,100,100, "wood"); //changing box
		
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
			box.setLocation(15,15);
			box.setWidth(100);
			box.setHeight(100);
			box.setTexture("wood");
			if (Keyboard.isKeyDown(Keyboard.KEY_1)){
				box.setLocation(150,150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_2)){
				box.setX(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_3)){
				box.setY(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_4)){
				box.setWidth(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_5)){
				box.setHeight(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_6)){
				box.setTexture("texture");
			}
			
			box.draw();
			test.draw();
			
			System.out.println("Colliding? "+ box.intersects(test));
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	//box object
	private static class Box extends AbstractEntity{ 
		
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

		@Override
		public void update(int delta) {
			//left blank there is not update for this object
		}
	}
	
	public static void main(String[] args) {
		new entityDemo();

	}

}
