import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import entities.AbstractEntity;
import entities.AbstractLump;
import entities.Entity;
import entities.GravityEntity;
import entities.MovingEntity;


public class lumpDemo {
	public lumpDemo(){
		try{
			Display.setDisplayMode(new DisplayMode(640,480));
			Display.setTitle("Entity Demo");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		List<Entity> level = new ArrayList<Entity>();
		AbstractLump lump = new Level();
		lump.add(new Box(10,10,10,10));
		lump.add(new Box(50,30,30,30));
		lump.add(new Box(200,30,50,60));
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		//code loop if the window is closed the loop will end
		while(!Display.isCloseRequested()){
			//Render
			glClear(GL_COLOR_BUFFER_BIT);
			Entity a;
			MovingEntity b;
			GravityEntity c;
			for(Entity g : lump.getArrayList()){
				if(!lump.get(0,Box.class).equals(g)){
					if(lump.get(0,Box.class).intersects(g)){
						lump.remove(g);
					}
				}
				g.draw();
			}
				
			if (Mouse.next()){
				a = lump.get(0, Box.class);
				a.setLocation(Mouse.getX(), 480-Mouse.getY());
			}
			if (Mouse.isButtonDown(0)){
				lump.add(new Box(Mouse.getX(), 480-Mouse.getY(),3,4));
				lump.get(0, Box.class).setX(500);
			}
			
			Display.update();
		}
		Display.destroy();
		System.exit(0);
	}
	
	public static void main(String[] args){
		new lumpDemo();
	}
	public class Level extends AbstractLump{
		public Level(){
		
		}
	}
	public class Box extends AbstractEntity{
		public Box(double x, double y, double width, double height){
			super(x, y, width, height);
		}

		@Override
		public void draw() {
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
	}
}
