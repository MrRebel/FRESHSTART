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
import org.newdawn.slick.opengl.TextureImpl;

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
		int[][] locations= {
				{50,110,60,40},//top left
				{150,110,80,40},//top left mid
				{270,70,20,80},//middle vertical 
				{0,60,560,10},//top bar
				{330,110,80,40},//top right mid
				{450,110,60,40},//top right
				{0,60,10,200},//left top bar
				{550,60,10,200},//right top bar
				{50,190,60,20},// left top flat
				{450,190,60,20},// right top flat 
				{150,190,20,140},// left 90 t height
				{150,250,80,20},// left 90 t length
				{330,250,80,20},// right 90 t length
				{390,190,20,140},// right 90 t height
				{210,190,140,20},//top reg t length
				{270,190,20,80},//top reg t height
				{0,250,100,10},// left top jut
				{450,250,100,10},// right top jut
				{100,250,10,80},// left top connector
				{450,250,10,80},// right top connector
				{0,320,110,10},// left top inlet
				{450,320,110,10},//right top inlet
				{210,310,50,10},//left top box
				{300,310,50,10},//right top box
				{210,380,140,10},//box bottom
				{210,310,10,80},//box left
				{340,310,10,80},// box right
				{0,370,100,10},// bottom left inlet
				{450,370,110,10},//bottom right inlet
				{150,370,20,80},// left middle vertical
				{390,370,20,80},// right middle vertical
				{100,370,10,80},// left  bottom connector
				{450,370,10,80},// right bottom connector
				{0,440,100,10},// left bottom jut
				{450,440,110,10},// right bottom jut
				{270,430,20,80}, // bottom reg t height
				{210,430,140,20}, // bottom reg t length
				{150,490,80,20}, // bottom left horizontal
				{330,490,80,20},// bottom right horizontal
				{0,450,10,220},// bottom left side
				{550,450,10,220},// bottom left side
				{50,490,60,20},// left short inter length
				{450,490,60,20},// right short inter length
				{90,490,20,80},// left short inter height
				{450,490,20,80},// right short inter height
				{0,550,50,20}, // left short jut
				{510,550,50,20},// right short jut
				{150,550,20,60},// uneven t left height
				{390,550,20,60},// uneven t right height
				{210,550,140,20},// middle t length
				{270,550,20,80}, // middle t height
				{50,610,180,20},// uneven t left length
				{330,610,180,20},// uneven t right length
				{0,670,560,10}// bottom bar
		};
		int[][] points = {
				{25,85},
				{45,85},
				{65,85},
				{85,85},
				{105,85},
				{125,85},
				{145,85},
				{165,85},
				{185,85},
				{205,85},
				{225,85},
				{245,85},
				{305,85},
				{325,85},
				{345,85},
				{365,85},
				{385,85},
				{405,85},
				{425,85},
				{445,85},
				{465,85},
				{485,85},
				{505,85},
				{525,85},
				{25,105},
				{125,105},
				{245,105},
				{305,105},
				{425,105},
				{525,105},
				{25,125},
				{125,125},
				{245,125},
				{305,125},
				{425,125},
				{525,125},
				{25,145},
				{125,145},
				{245,145},
				{305,145},
				{425,145},
				{525,145},
				{25,165},
				{45,165},
				{65,165},
				{85,165},
				{105,165},
				{125,165},
				{145,165},
				{165,165},
				{185,165},
				{205,165},
				{225,165},
				{245,165},
				{265,165},
				{285,165},
				{305,165},
				{325,165},
				{345,165},
				{365,165},
				{385,165},
				{405,165},
				{425,165},
				{445,165},
				{465,165},
				{485,165},
				{505,165},
				{525,165},
				{25,185},
				{125,185},
				{185,185},
				{365,185},
				{425,185},
				{525,185},
				{25,205},
				{125,205},
				{185,205},
				{365,205},
				{425,205},
				{525,205},
				{25,225},
				{45,225},
				{65,225},
				{85,225},
				{105,225},
				{125,225},
				{185,225},
				{185,225},
				{205,225},
				{225,225},
				{245,225},
				{305,225},
				{325,225},
				{345,225},
				{365,225},
				{425,225},
				{445,225},
				{465,225},
				{485,225},
				{505,225},
				{525,225},
				{125,245},
				{425,245},
				{125,265},
				{425,265},
				{125,285},
				{425,285},
				{125,305},
				{425,305},
				{125,325},
				{425,325},
				{125,345},
				{425,345},
				{125,365},
				{425,365},
				{125,385},
				{425,385},
				{125,405},
				{425,405},
				{125,425},
				{425,425},
				{125,445},
				{425,445},
				{25,465},
				{45,465},
				{65,465},
				{85,465},
				{105,465},
				{125,465},
				{145,465},
				{165,465},
				{185,465},
				{205,465},
				{225,465},
				{245,465},
				{305,465},
				{325,465},
				{345,465},
				{365,465},
				{385,465},
				{405,465},
				{425,465},
				{445,465},
				{465,465},
				{485,465},
				{505,465},
				{525,465},
				{25,485},
				{125,485},
				{245,485},
				{305,485},
				{425,485},
				{525,485},
				{25,505},
				{125,505},
				{245,505},
				{305,505},
				{425,505},
				{525,505},
				{25,525},
				{45,525},
				{65,525},
				{125,525},
				{145,525},
				{165,525},
				{185,525},
				{205,525},
				{225,525},
				{245,525},
				{305,525},
				{325,525},
				{345,525},
				{365,525},
				{385,525},
				{405,525},
				{425,525},
				{485,525},
				{505,525},
				{525,525},
				{65,545},
				{125,545},
				{185,545},
				{365,545},
				{425,545},
				{485,545},
				{65,565},
				{125,565},
				{185,565},
				{365,565},
				{425,565},
				{485,565},
				{25,585},
				{45,585},
				{65,585},
				{85,585},
				{105,585},
				{125,585},
				{185,585},
				{205,585},
				{225,585},
				{245,585},
				{305,585},
				{325,585},
				{345,585},
				{365,585},
				{425,585},
				{445,585},
				{465,585},
				{485,585},
				{505,585},
				{525,585},
				{25,605},
				{245,605},
				{305,605},
				{525,605},
				{25,625},
				{245,625},
				{305,625},
				{525,625},
				{25,645},
				{45,645},
				{65,645},
				{85,645},
				{105,645},
				{125,645},
				{145,645},
				{165,645},
				{185,645},
				{205,645},
				{225,645},
				{245,645},
				{265,645},
				{285,645},
				{305,645},
				{325,645},
				{345,645},
				{365,645},
				{385,645},
				{405,645},
				{425,645},
				{445,645},
				{465,645},
				{485,645},
				{505,645},
				{525,645},
		};
		world = new World(world1,16);
		for (int[] f: locations){
			world.add(new Wall(f[0],f[1],f[2],f[3]));
		}
		for (int[] f: points){
			world.add(new Dot(f[0],f[1]));
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
		world.add(new FWall());
		world.add(new Pman(260,510,"pacmanmain1"));
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
			glClear(GL_COLOR_BUFFER_BIT);
			x++;
			//x=x/2;
			if (x>13){
				x=0;
			}
			for (Entity f: worldfinaluse){
				glColor3f(1f,1f,1f);
				if (f instanceof Pman){
					if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
						((MovingEntity)f).setDY(0);
						((MovingEntity)f).setDX(.1);
						pacmanAniMain=pacmanAniRight;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
						((MovingEntity)f).setDY(0);
						((MovingEntity)f).setDX(-.1);
						pacmanAniMain=pacmanAniLeft;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
						((MovingEntity)f).setDX(0);
						((MovingEntity)f).setDY(.1);
						pacmanAniMain=pacmanAniDown;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
						((MovingEntity)f).setDX(0);
						((MovingEntity)f).setDY(-.1);
						pacmanAniMain=pacmanAniUp;
					}
					f.setTexture(pacmanAniMain[x]);
					for (Entity g: worldfinaluse){
						if(g instanceof Dot){
							if(f.intersects(g)){
								world.remove(g);
							}
						}
					}
					for (Entity g: worldfinaluse){
						if(g instanceof Wall){
							if(f.intersects(g)){
								if(((MovingEntity)f).getDY() == 0){
									if(((MovingEntity)f).getDX() > 0){
										f.setX(((int)g.getX()-f.getWidth()));
									}
									if(((MovingEntity)f).getDX() < 0){
										f.setX(((int)g.getX()+g.getWidth()));
									}
								}
								if(((MovingEntity)f).getDX() == 0){
									if(((MovingEntity)f).getDY() > 0){
										f.setY(((int)g.getY()-f.getHeight()));
									}
									if(((MovingEntity)f).getDY() < 0){
										f.setY(((int)g.getY()+g.getHeight()));
									}
								}
							}
						}
					}
					//System.out.println(((MovingEntity)f).getDX());
					//System.out.println(((MovingEntity)f).getDY());
				}
				f.update();
				f.draw();
				//f.getTexture().bindNone();
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
		public Pman(double x, double y, String tex) {
			super(x, y, 40, 40, tex);
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
