import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2d;
import hopepac.WorldDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import entities.AbstractEntity;
import entities.AbstractMovingEntity;
import entities.AbstractWorld;
import entities.Entity;
import entities.world;


public class Pacman {
	public Pacman(){
		//create a openGL instance in the thread
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Word Demo");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		World world;
		List<Entity> world1 = new ArrayList <Entity> (16);
		List<Entity> worldfinaluse = new ArrayList <Entity> (16);
		world = new World(world1,16);
		world.add(new Wall(40,40,80,40));
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
			worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
			for (Entity f: worldfinaluse){
				f.draw();
			}
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	public static class World extends AbstractWorld{

		public World(List<Entity> world, int length){
			super(world, length);
		}
	}
	private static class Wall extends AbstractEntity{

		
		public Wall(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		public void draw() {
			glLoadIdentity();
			glColor3f(0f,0f,1f);
			glBegin(GL_QUADS);
				glVertex2d(x,y);
				glVertex2d(x+width,y);
				glVertex2d(x+width,y+height);
				glVertex2d(x,y+height);
			glEnd();
		}

		@Override
		public void update(int delta) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		public static void main(String[] args){
			new Pacman();

		}
		
	}
}
