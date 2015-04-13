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
	public List<MovingEntity> world1 = new ArrayList <MovingEntity> (16);
	public List<GravityEntity> world2 = new ArrayList <GravityEntity> (16);
	public World world;
	public List<Entity> worldfinaluse = new ArrayList <Entity> (16);
	public List <GravityEntity> worldfinaluseg = new ArrayList <GravityEntity> (16);
	public Box ehb0ss = new Box(15,15,15,15);
	int x = 0;
	
	public WorldDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Hope");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		
		world1.add(new Box(30,30,30,30));
		world1.add(new Box (15,50,15,15));
		world2.add(new Grav(100,100,40,40));
		world = new World (null, world1, world2, null, null, null, 16);
		worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
		
		//Initialization code OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
			worldfinaluseg = new CopyOnWriteArrayList<GravityEntity>(world.giveArrayListG());
			int delta = getDelta();
			
	
			for (Entity f : worldfinaluse){
				f.update(delta);
				if (Mouse.isButtonDown(0) && x==0){
					world.addmove(new Box(15,15,15,15));
					world.returnmove(2).setDX(.1);
					x=1;
				}
				
				
				if (Mouse.isButtonDown(1) && x==1){
					world.removemove(world.returnmove(2));
					world.returnmove(2).setDX(.1);
					x=0;
				}
				if (Mouse.getX() >= f.getX() && Mouse.getX() <= f.getX()+f.getWidth() ){
					world.removeEntity(new Box(15,15,15,15));
					System.out.println(1);
				}
				f.draw();
				
			}
			
			Display.update();
			Display.sync(60);
			
		}
		Display.destroy();
		
	}
	
	public static class World extends AbstractWorld{

		public World(List<Entity> world, List<MovingEntity> moveworld, List<GravityEntity> gravworld, List<BoundEntity> boundworld, List<ScoreEntity> scoreworld, List<ButtonEntity>buttonworld, int length){
			super(world, moveworld, gravworld, boundworld, scoreworld, buttonworld, length);
		}
	}
	
	private static class Box extends AbstractMovingEntity{

		
		public Box(double x, double y, double width, double height) {
			super(x, y, width, height);
		}

		public void draw() {
			glLoadIdentity();
			glRectd(x, y, x + width, y + height);
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
