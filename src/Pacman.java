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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.TextureImpl;

import entities.AbstractBoundEntity;
import entities.BoundEntity;
import entities.AbstractEntity;
import entities.AbstractMovingEntity;
import entities.MovingEntity;
import entities.AbstractWorld;
import entities.Entity;


public class Pacman{
	public Pacman(){
		//create a openGL instance in the thread
		try{
			Display.setDisplayMode(new DisplayMode(560,720));
			Display.setTitle("PACMAN");
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			
		}
		World world;
		int x = 0;
		List<Entity> world1 = new ArrayList <Entity> (16);
		List<Entity> worldfinaluse = new ArrayList <Entity> (16);
		world = new World(world1,16);
		for (int[] f: Positions.locations){
			world.add(new Wall(f[0],f[1],f[2],f[3]));
		}
		for (int[] f: Positions.points){
			world.add(new Dot(f[0],f[1]));
		}
		world.add(new FWall());
		for (int i = 0; i < Positions.turns.length; i++) {
			world.add(new FourWayBlock(Positions.turns[i][0],Positions.turns[i][1],Positions.directionsquares[i]));		
		}
		String[] pacmanAniMain;
		String[] pacmanAniRight = {
				"pacmanmain1","pacmanmain2","pacmanmain3","pacmanmain4","pacmanmain5","pacmanmain6","pacmanmain7","pacmanmain8","pacmanmain7","pacmanmain6","pacmanmain5","pacmanmain4","pacmanmain3","pacmanmain2"
		};
		String[] pacmanAniLeft = {
				"pacmanleft1","pacmanleft2","pacmanleft3","pacmanleft4","pacmanleft5","pacmanleft6","pacmanleft7","pacmanleft8","pacmanleft7","pacmanleft6","pacmanleft5","pacmanleft4","pacmanleft3","pacmanleft2"
		};
		String[] pacmanAniUp = {
				"pacmanup1","pacmanup2","pacmanup3","pacmanup4","pacmanup5","pacmanup6","pacmanup7","pacmanup8","pacmanup7","pacmanup6","pacmanup5","pacmanup4","pacmanup3","pacmanup2"
		};
		String[] pacmanAniDown = {
				"pacmandown1","pacmandown2","pacmandown3","pacmandown4","pacmandown5","pacmandown6","pacmandown7","pacmandown8","pacmandown7","pacmandown6","pacmandown5","pacmandown4","pacmandown3","pacmandown2"
		};
		world.add(new Pman(260,510,"pacmanmain1","left"));
		worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
		world.add(new Catch((MovingEntity)world.getEntity(worldfinaluse.size()-1)));
		world.add(new Clear());
		//for (Entity f: )
		//Set up Window
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 560, 720, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);        
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);
		glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		pacmanAniMain = pacmanAniLeft;
		//code loop if the window is closed the loop will end
		while(!Display.isCloseRequested()){
			worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());
			((BoundEntity)world.getEntity(worldfinaluse.size()-2)).setBound(world.getEntity(worldfinaluse.size()-3));
			glClear(GL_COLOR_BUFFER_BIT);
			x++;
			//x=x/2;
			if (x>13){
				x=0;
			}
			for (Entity f: worldfinaluse){
				glColor3f(1f,1f,1f);
				if (f instanceof Pman){
					f.setX(Math.round(f.getX()));
					f.setY(Math.round(f.getY()));
					if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
						((Pman)f).setChangeDirection("right");
					}
					else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
						((Pman)f).setChangeDirection("left");
					}
					else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
						((Pman)f).setChangeDirection("down");
					}
					else if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
						((Pman)f).setChangeDirection("up");
					}
					if ((((Pman)f).getChangeDirection().equals("right"))){
						if((((Pman)f).getDirection().equals("left"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDY(0);
							((MovingEntity)f).setDX(.1);
							f.setY(((Math.round(f.getY()))/10)*10);
							pacmanAniMain=pacmanAniRight;
						}else if ((((Pman)f).getDirection().equals("right"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX() == f.getX())&&(g.getY() == f.getY())){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("right")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											((MovingEntity)f).setDY(0);
											((MovingEntity)f).setDX(.1);
											f.setY(((Math.round(f.getY()))/10)*10);
											pacmanAniMain=pacmanAniRight;
										}
									}
								}
							}
						}
					}
					else if ((((Pman)f).getChangeDirection().equals("left"))){
						if((((Pman)f).getDirection().equals("right"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDY(0);
							((MovingEntity)f).setDX(-.1);
							f.setY(((Math.round(f.getY()))/10)*10);
							pacmanAniMain=pacmanAniLeft;
						}else if ((((Pman)f).getDirection().equals("left"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX() == f.getX())&&(g.getY() == f.getY())){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("left")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											((MovingEntity)f).setDY(0);
											((MovingEntity)f).setDX(-.1);
											f.setY(((Math.round(f.getY()))/10)*10);
											pacmanAniMain=pacmanAniLeft;
										}
									}
								}
							}
						}
					}
					if ((((Pman)f).getChangeDirection().equals("up"))){
						if((((Pman)f).getDirection().equals("down"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDX(0);
							((MovingEntity)f).setDY(-.1);
							f.setY(((Math.round(f.getY()))/10)*10);
							pacmanAniMain=pacmanAniUp;
						}else if ((((Pman)f).getDirection().equals("up"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX() == f.getX())&&(g.getY() == f.getY())){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("up")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											((MovingEntity)f).setDX(0);
											((MovingEntity)f).setDY(-.1);
											f.setY(((Math.round(f.getY()))/10)*10);
											pacmanAniMain=pacmanAniUp;
										}
									}
								}
							}
						}
					}
					else if ((((Pman)f).getChangeDirection().equals("down"))){
						if((((Pman)f).getDirection().equals("up"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							System.out.println("lol im there");
							((MovingEntity)f).setDX(0);
							((MovingEntity)f).setDY(.1);
							f.setY(((Math.round(f.getY()))/10)*10);
							pacmanAniMain=pacmanAniDown;
						}else if ((((Pman)f).getDirection().equals("down"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX() == f.getX())&&(g.getY() == f.getY())){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("down")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											((MovingEntity)f).setDX(0);
											((MovingEntity)f).setDY(.1);
											f.setY(((Math.round(f.getY()))/10)*10);
											pacmanAniMain=pacmanAniDown;
										}
									}
								}
							}
						}
					}
					f.setTexture(pacmanAniMain[x]);
					for (Entity g: worldfinaluse){
						if(g instanceof Wall){
							if(f.intersects(g)){
								System.out.println("lol");
								if(((MovingEntity)f).getDY() == 0){
									if(((MovingEntity)f).getDX() > 0){
										f.setX(((int)g.getX()-f.getWidth()));
										((MovingEntity)f).setDX(0);
									}
									if(((MovingEntity)f).getDX() < 0){
										f.setX(((int)g.getX()+g.getWidth()));
										((MovingEntity)f).setDX(0);
									}
								}
								if(((MovingEntity)f).getDX() == 0){
									if(((MovingEntity)f).getDY() > 0){
										f.setY(((int)g.getY()-f.getHeight()));
										((MovingEntity)f).setDY(0);
									}
									if(((MovingEntity)f).getDY() < 0){
										f.setY(((int)g.getY()+g.getHeight()));
										((MovingEntity)f).setDY(0);
									}
								}
							}
						}
					}
					//System.out.println(((MovingEntity)f).getDX());
					//System.out.println(((MovingEntity)f).getDY());
					//System.out.println(((MovingEntity)f).getDX());
					//System.out.println(((MovingEntity)f).getDY());
				}
				if (f instanceof Catch){
					for (Entity g: worldfinaluse){
						if(g instanceof Dot){
							if(f.intersects(g)){
								world.remove(g);
							}
						}
					}
				}
				//System.out.println(f);
				f.update();
				if(!(f instanceof Catch)){
					f.draw();
				}
				//f.getTexture().bindNone();
			}
			Display.update();
			Display.sync(100);
		}
		Display.destroy();
	}
	public static class World extends AbstractWorld{

		public World(List<Entity> world, int length){
			super(world, length);
		}
	}
	public static class Wall extends AbstractEntity{

		
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
	}
	public static class FWall extends AbstractEntity{

		
		public FWall() {
			super(260,311,40,8);
		}

		public void draw() {
			glLoadIdentity();
			glColor3f(1f,.5f,.5f);
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
	public static class FourWayBlock extends AbstractEntity{
		protected String[] directions;
		
		public FourWayBlock(double x, double y, String[] directions) {
			super(x,y,40,40,"invis1");
			this.directions = directions;
		}

		public void draw() {
			glLoadIdentity();
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void update() {
			// TODO Auto-generated method stub
			
		}
		public String[] getDirections(){
			return directions;
		}
	}
	public static class Dot extends AbstractEntity{	
		public Dot(double x, double y) {
			super(x, y, 10, 10);
		}

		public void draw() {
			glLoadIdentity();
			glColor3f(1f,.8f,.5f);
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
	
	public static class Clear extends AbstractEntity{	
		public Clear() {
			super(1000, 1000, 10, 10,"white1");
		}

		public void draw() {
			glLoadIdentity();
			tex.bind();
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
	public static class Pman extends AbstractMovingEntity{
		protected String direction;
		protected String changeDirection = "";
		public Pman(double x, double y, String tex, String direction) {
			super(x, y, 40, 40, tex);
			this.direction = direction;
		}

		@Override
		public void draw() {
			glLoadIdentity();
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
		public void setDirection(String direction){
			this.direction = direction;
		}
		public void setChangeDirection(String changeDirection){
			this.changeDirection = changeDirection;
		}
		public String getDirection(){
			return direction;
		}
		public String getChangeDirection(){
			return changeDirection;
		}
	}
	public static class Catch extends AbstractBoundEntity{
		public Catch(Entity bound) {
			super(270,520, 20, 20, 10, 10, bound);
		}

		@Override
		public void draw() {
			glLoadIdentity();
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
		public void setIsBound() {
			// TODO Auto-generated method stub
			
		}
		
	}
	public static class Ghost extends AbstractMovingEntity{
		public Ghost(double x, double y, double width, double height, String tex) {
			super(x, y, width, height, tex);
		}

		@Override
		public void draw() {
			glLoadIdentity();
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
			tex.bindNone();
		}
	}
	public static void main(String[] args){
		new Pacman();

	}
}
