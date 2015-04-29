
import static org.lwjgl.opengl.GL11.*; //Import GL11 for 2d graphics

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;//import use of keyboard
import org.lwjgl.opengl.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import entities.AbstractWordEntity;
import entities.WordEntity;


public class WordDemo {	
	public WordDemo(){
		//create a openGL instance in the thread
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Word Demo");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		
		WordEntity box = new Box (15,15,100,100, "Times New Roman"); //changing box
		
		WordEntity test = new Box (200,200,100,100, "Times New Roman"); //reference box
		//Set up Window
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);        
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);
		//glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		//code loop if the window is closed the loop will end
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			//code in loop
			//assures reset of all attributes
			//changes to attributes
			/*
			if (Keyboard.isKeyDown(Keyboard.KEY_1)){
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_2)){
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_3)){
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_4)){
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_5)){
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_6)){
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_7)){
				box.setTexture("texture");
			}
			*/
			box.draw();
			
			System.out.println("Colliding? "+ box.intersects(test));
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	//box object
	private static class Box extends AbstractWordEntity{ 
		
		public Box(double x, double y, double width, double height, String fontName) {
			super(x, y, width, height, fontName);
		}

		@Override
		//draw 
		public void draw() {
			Color.white.bind();
			font.drawString(0, 0, "Hello World", Color.yellow);
		}

		@Override
		public void update(int delta) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static void main(String[] args) {
		new WordDemo();

	}

}
