package hopepac;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

import entities.AbstractGravityEntity;
import entities.AbstractMovingEntity;
import entities.AbstractWorld;
import entities.BoundEntity;
import entities.ButtonEntity;
import entities.Entity;
import entities.GravityEntity;
import entities.MovingEntity;
import entities.ScoreEntity;


public class WorldDemo {
	private long lastFrame;
	
		private long getTime(){
			return (Sys.getTime() * 1000) / Sys.getTimerResolution();
		}
	
		private int getDelta(){
			long currentTime=getTime();
			int delta = (int) (currentTime - lastFrame);
			lastFrame = currentTime;
			return delta;
		}
	
	
	public WorldDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Hope");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		List<Entity> world1 = new ArrayList <Entity> (16);
		List<GravityEntity> world2 = new ArrayList <GravityEntity> (16);
		World world;
		List<Entity> worldfinaluse = new ArrayList <Entity> (16);
		List <GravityEntity> worldfinaluseg = new ArrayList <GravityEntity> (16);
		Box ehb0ss = new Box(15,15,15,15,"wood");
		boolean x = false;
		
		world1.add(new Box(30,30,30,30,"wood"));
		world1.add(new Box (15,50,15,15, "wood"));
		//world2.add(new Grav(100,100,40,40));
		world = new World (world1, 16);
		worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
			worldfinaluseg = new CopyOnWriteArrayList<GravityEntity>(world.giveArrayListG());
			int delta = getDelta();
	
			for (Entity f : worldfinaluse){
				if (Mouse.isButtonDown(0) && !x){
					world.add(new Box(15,15,15,15,"wood"));
					x = !x;
				}
				
				
				if (Mouse.isButtonDown(1) && x){
					world.remove(world.getEntity(2));
					x = !x;
				}
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
	
	private static class Box extends AbstractMovingEntity{

		
		public Box(double x, double y, double width, double height, String key) {
			super(x, y, width, height, key);
			setTexture(key);
		}

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
	private static class Grav extends AbstractGravityEntity{

		
		public Grav(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		public void draw() {
			glLoadIdentity();
			glRectd(x, y, x + width, y + height);
		}
		
	}
	
	public static void main(String[] args){
		new WorldDemo();

	}


}
