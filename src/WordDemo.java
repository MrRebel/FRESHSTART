
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
		
		WordEntity box = new Box (15,15, "Hello World"); //changing box
		
		//Set up Window
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);        
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);
		glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		//code loop if the window is closed the loop will end
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			//code in loop
			//assures reset of all attributes
			//changes to attributes
			box.setLocation(0,0);
			box.setWidth(100);
			box.setHeight(100);
			box.setWord("Hello World ");
			if (Keyboard.isKeyDown(Keyboard.KEY_1)){
				box.setWord("I can also be a score watch 500");
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_2)){
				box.setX(150);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_3)){
				box.setY(150);
			}
			box.draw();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	//box object
	private static class Box extends AbstractWordEntity{ 
		
		public Box(double x, double y, String word) {
			super(x, y, word);
		}

		@Override
		//draw 
		public void draw() {
			font.drawString((int)x, (int)y, word, Color.yellow);
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
