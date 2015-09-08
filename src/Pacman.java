/**
 * @author William Christensen
 * @date 6/16/25
 * 
 * This is the code to run the first level of pacman with opengl in java using slick 2d and lwjgl
 */
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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.Timer;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.TextureImpl;

import entities.AbstractBoundEntity;
import entities.AbstractWordEntity;
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
		world = new World(world1,16); // will contain all 
		for (int[] f: Positions.locations){
			world.add(new Wall(f[0],f[1],f[2],f[3]));
		}
		for (int[] f: Positions.points){
			world.add(new Dot(f[0],f[1]));
		}
		String[] savestate= {};
		int score = 0;
		Entity save = new FourWayBlock(10,10,savestate);//used to check moving out of square
		Entity save2 = new FourWayBlock(10,10,savestate);//used to check moving out of square
		Entity save3 = new FourWayBlock(10,10,savestate);//used to check moving out of square
		Entity save4 = new FourWayBlock(10,10,savestate);//used to check moving out of square
		Entity save5 = new FourWayBlock(10,10,savestate);//used to check moving out of square
		String[] fonts = {"Times New Roman"}; // for preloading
		int[] types = {Font.PLAIN}; // for preloading
		boolean check = true; 
		ActionListener taskPerformer = new Timers(); // timers for state switch
		Timer timer = new Timer(7000, taskPerformer); // first timer to start
		world.add(new Words(10,10,"p1 score " + score)); // words for score
		worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList()); // reloadworldfinaluse
		((Words)world.getEntity(worldfinaluse.size()-1)).setFontPreloaded(fonts, types); // preload fonts into score
		world.add(new Ghost(260,270,"blinky1","left","blinky","scatter"));
		world.add(new Ghost(260,270,"pinky1","left","pinky","scatter"));
		world.add(new Ghost(260,270,"inky1","left","inky","scatter"));
		world.add(new Ghost(260,270,"clyde1","left","clyde","scatter"));
		world.add(new FWall()); // false wall
		for (int i = 0; i < Positions.turns.length; i++) {
			world.add(new FourWayBlock(Positions.turns[i][0],Positions.turns[i][1],Positions.directionsquares[i]));	// creates all turn blocks	
		}
		String[] pacmanAniMain;
		String[] pacmanAniRight = { // pacman animation right
				"pacmanmain1","pacmanmain2","pacmanmain3","pacmanmain4","pacmanmain5","pacmanmain6","pacmanmain7","pacmanmain8","pacmanmain7","pacmanmain6","pacmanmain5","pacmanmain4","pacmanmain3","pacmanmain2"
		};
		String[] pacmanAniLeft = { // pacman animation left
				"pacmanleft1","pacmanleft2","pacmanleft3","pacmanleft4","pacmanleft5","pacmanleft6","pacmanleft7","pacmanleft8","pacmanleft7","pacmanleft6","pacmanleft5","pacmanleft4","pacmanleft3","pacmanleft2"
		};
		String[] pacmanAniUp = { // pacman animation up
				"pacmanup1","pacmanup2","pacmanup3","pacmanup4","pacmanup5","pacmanup6","pacmanup7","pacmanup8","pacmanup7","pacmanup6","pacmanup5","pacmanup4","pacmanup3","pacmanup2"
		};
		String[] pacmanAniDown = { // pacman animation down
				"pacmandown1","pacmandown2","pacmandown3","pacmandown4","pacmandown5","pacmandown6","pacmandown7","pacmandown8","pacmandown7","pacmandown6","pacmandown5","pacmandown4","pacmandown3","pacmandown2"
		};
		world.add(new Pman(260,510,"pacmanmain1",""));// pacman
		worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList()); // reload worldfinal use
		world.add(new Catch((MovingEntity)world.getEntity(worldfinaluse.size()-1))); // catch entity used to catch dots
		world.add(new Clear());// clearing of the draw space entity
		//for (Entity f: )
		//Set up Window
		//initialize opengl modes
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, 560, 720, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);        
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);
		glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		pacmanAniMain = pacmanAniLeft; // set initial pacman animation
		//code loop if the window is closed the loop will end
		while(!Display.isCloseRequested()){// game loop
			worldfinaluse = new CopyOnWriteArrayList<Entity>(world.giveArrayList());// recreates worldfinaluse every time the loop runs
			((BoundEntity)world.getEntity(worldfinaluse.size()-2)).setBound(world.getEntity(worldfinaluse.size()-3)); // binds catch everytime to pacman so they stay together
			glClear(GL_COLOR_BUFFER_BIT);// clears screen before loop
			x++;// for animation purposes
			if (x>13){
				x=0;
			}
			String temo = ((Timers) taskPerformer).getStatus(); //used to check timer status
			if (temo.equals("chase")&&check){ // checks status and changes timer to 
				timer.setDelay(7000);
				check = false;
			}else if (temo.equals("scatter")&&!check){ // checks status and changes timer
				timer.setDelay(30000);
				check = true;
			}
			for (Entity f: worldfinaluse){
				glColor3f(1f,1f,1f); // sets back color to white to avoid shading
				if (f instanceof Pman){ // main pacman loop
					//controls they don't change the direction they change the direction it will change
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
					for (Entity g: worldfinaluse){
						if (g instanceof Ghost){
							((Ghost)g).setStatus(temo);
							// blinky ghost AI
							if(((Ghost)g).getName().equals("blinky")){
								for(Entity h: worldfinaluse){
									if(h instanceof FourWayBlock){
										// will only run when ghost in proximity to turn block
										if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("scatter"))&&((Ghost)g).getCorner()){// if blinky is in the corner space
											
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), 27,0 ));	// run the direction decider
											if(!(((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection()))){// if it is not 90 degrees from the curent travel direction
												g.setX(h.getX());
												g.setY(h.getY());
											}
											save = h;// used to save what corner it was most recently at
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);// corner
										}else if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("chase"))&&((Ghost)g).getCorner()){
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), (int)(f.getX()+10)/20,(int)(f.getY()+10)/20));//tracking pacmans position
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){
												g.setX(h.getX());
												g.setY(h.getY());
											}
											save = h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());											
											((Ghost)g).setCorner(false);
										}else if(!((g.getX()<save.getX()+3 && g.getX()>save.getX()-3)&&(g.getY()<save.getY()+3 && g.getY()>save.getY()-3))){
											((Ghost)g).setCorner(true);
										}
									}
								}
							}
							if(((Ghost)g).getName().equals("pinky")){ // pinky loop
								int gy = ((int)(f.getY()+10)/20); // used for tracking as tracking uses Positions.board
								int gx = ((int)(f.getX()+10)/20);// used for tracking as tracking uses Positions.board
								if (((Pman)f).getDirection().equals("up")){
									gy -= 4;// both gy and gx were changed in the original due to an overflow error
									gx -= 4;
								}
								if (((Pman)f).getDirection().equals("left")){
									gx -= 4;
								}
								if (((Pman)f).getDirection().equals("right")){
									gx += 4;
								}
								if (((Pman)f).getDirection().equals("down")){
									gy += 4;
								}
								if(gx < 0){// to avoid out of bounds
									gx=0;
								}
								if(gx > 27){// to avoid out of bounds
									gx=27;
								}
								if(gy < 0){// to avoid out of bounds
									gy=0;
								}
								if(gy > 31){// to avoid out of bounds
									gy=31;
								}
								for(Entity h: worldfinaluse){
									if(h instanceof FourWayBlock){
										if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("scatter"))&&((Ghost)g).getCorner()){
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), 0, 0));// tracking 0,0
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){
												g.setX(h.getX());
												g.setY(h.getY());	
											}
											save2=h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);
										}else if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("chase"))&&((Ghost)g).getCorner()){
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), gx, gy));//tracking gx,gy
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){// will only pop location if it is turning for smoothest motion possible
												g.setX(h.getX());
												g.setY(h.getY());	
											}
											save2=h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);
										}else if(!((g.getX()<save2.getX()+3 && g.getX()>save2.getX()-3)&&(g.getY()<save2.getY()+3 && g.getY()>save2.getY()-3))){
											((Ghost)g).setCorner(true);
										}
									}
								}
							}
							if(((Ghost)g).getName().equals("inky")){// inky loop
								int gy = ((int)(f.getY()+10)/20);// used for tracking as tracking uses Positions.board
								int gx = ((int)(f.getX()+10)/20);// used for tracking as tracking uses Positions.board
								for (Entity i: worldfinaluse){
									if (i instanceof Ghost){
										if(((Ghost)i).getName().equals("blinky")){
											if (((Pman)f).getDirection().equals("up")){
												gy -= 2;// in orginial up ad to the left was check due to overflow
												gx -= 2;
											}
											if (((Pman)f).getDirection().equals("left")){
												gx -= 2;
											}
											if (((Pman)f).getDirection().equals("right")){
												gx += 2;
												}
											if (((Pman)f).getDirection().equals("down")){
												gy += 2;
											}
											gx += gx-((int)(i.getX()+10)/20);
											gy += gy-((int)(i.getY()+10)/20);
											if(gx < 0){// to avoid out of bounds
												gx=0;
											}
											if(gx > 27){// to avoid out of bounds
												gx=27;
											}
											if(gy < 0){// to avoid out of bounds
												gy=0;
											}
											if(gy > 31){// to avoid out of bounds
												gy=31;
											}
										}
									}
								}
								for(Entity h: worldfinaluse){
									if(h instanceof FourWayBlock){
										if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("scatter"))&&((Ghost)g).getCorner()){
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), 27, 31));
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){
												g.setX(h.getX());
												g.setY(h.getY());	
											}
											save3=h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);
										}else if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("chase"))&&((Ghost)g).getCorner()){
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), gx, gy));
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){
												g.setX(h.getX());
												g.setY(h.getY());	
											}
											save3=h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);
										}else if(!((g.getX()<save3.getX()+3 && g.getX()>save3.getX()-3)&&(g.getY()<save3.getY()+3 && g.getY()>save3.getY()-3))){
											((Ghost)g).setCorner(true);
										}
									}
								}
							}
							if(((Ghost)g).getName().equals("clyde")){
								//clyde loop
								int gy = ((int)(f.getY()+10)/20);
								int gx = ((int)(f.getX()+10)/20);
								for(Entity h: worldfinaluse){
									if(h instanceof FourWayBlock){
										if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("scatter"))&&((Ghost)g).getCorner()){
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), 0,31));
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){
												g.setX(h.getX());
												g.setY(h.getY());	
											}
											save4=h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);
										}
										else if(((g.getX()<h.getX()+3 && g.getX()>h.getX()-3)&&(g.getY()<h.getY()+3 && g.getY()>h.getY()-3))&&(((Ghost)g).getStatus().equals("chase"))&&((Ghost)g).getCorner()){
											if (Math.hypot(g.getX()-gx,g.getY()-gy)<8){// if it is within 8 places of pacman change tracking point to scatter point
												gx = 0;
												gy = 31;
											}
											if (directionCalculate.hyp(g.getX()-gx,g.getY()-gy)>8){// change back to pacman if outside of 8 =
												gy = ((int)(f.getY()+10)/20);
												gx = ((int)(f.getX()+10)/20);
											}
											((Ghost)g).setChangeDirection(directionCalculate.setChange((int)(h.getX()+10)/20, (int)(h.getY()+10)/20, ((Ghost)g).getDirection(), ((FourWayBlock)h).getDirections(), gx,gy));
											((Ghost)g).setCorner(false);
											if(!((Ghost)g).getChangeDirection().equals(((Ghost)g).getDirection())){
												g.setX(h.getX());
												g.setY(h.getY());	
											}
											save4=h;
											((Ghost)g).setDirection(((Ghost)g).getChangeDirection());
											((Ghost)g).setCorner(false);
										}else if(!((g.getX()<save4.getX()+3 && g.getX()>save4.getX()-3)&&(g.getY()<save4.getY()+3 && g.getY()>save4.getY()-3))){
											((Ghost)g).setCorner(true);
										}
									}
								}
							}
							if(((Ghost)g).getDirection().equals("left")){// set direction textures
								((MovingEntity)g).setDY(0);
								((MovingEntity)g).setDX(-.1);
								g.setTexture(((Ghost)g).getName() +"2");
							}
							else if(((Ghost)g).getDirection().equals("right")){
								((MovingEntity)g).setDY(0);
								((MovingEntity)g).setDX(.1);
								g.setTexture(((Ghost)g).getName() + "4");
							}
							else if(((Ghost)g).getDirection().equals("up")){
								((MovingEntity)g).setDY(-.1);
								((MovingEntity)g).setDX(0);
								g.setTexture(((Ghost)g).getName() + "3");
							}
							else if(((Ghost)g).getDirection().equals("down")){
								((MovingEntity)g).setDY(.1);
								((MovingEntity)g).setDX(0);
								g.setTexture(((Ghost)g).getName() + "1");
							}
							if((((Pman)f).getDirection().equals(""))){
								((MovingEntity)g).setDY(0);
								((MovingEntity)g).setDX(0);
								
							}
							if(g.getX()<-40){
								g.setX(560);
							}
							if(g.getX()>560){
								g.setX(-40);
							}
						}
					}
					if (((Pman)f).getChangeDirection().equals("right")){// here is where direction happens
						if((((Pman)f).getDirection().equals("left"))){ // checks current direction
							((Pman)f).setDirection(((Pman)f).getChangeDirection());// if it is opposite flip direction
							((MovingEntity)f).setDY(0);
							((MovingEntity)f).setDX(.1);
							f.setY(Math.round(f.getY()));// assures that y is not changed at all during the process note: not necessary
							pacmanAniMain=pacmanAniRight;// changes animation direction
						}else if (((Pman)f).getDirection().equals("")){// if current direction doesn't exist just go right
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDY(0);
							((MovingEntity)f).setDX(.1);
							f.setY(Math.round(f.getY()));
							pacmanAniMain=pacmanAniRight;
							timer.start(); // starts timer this case is only possible at the beggining of the round
						}
						else if (((Pman)f).getDirection().equals("right")){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX()-3 < f.getX() && g.getX()+3 > f.getX())&&(g.getY()-3 < f.getY() && g.getY()+3 > f.getY())){ // checks if on a four way block
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("right")){ // if the turn block contains the direction right in its directions
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											f.setX(g.getX());
											f.setY(g.getY());
											((MovingEntity)f).setDY(0);
											((MovingEntity)f).setDX(.1);
											save5 = g; // save 5 is used to stop getting stuck in the corners
											pacmanAniMain=pacmanAniRight;
											((Pman)f).setCorner(false);// sets check corner
										}
									}else if (!((save5.getX()-3 < f.getX() && save5.getX()+3 > f.getX())&&(save5.getY()-3 < f.getY() && save5.getY()+3 > f.getY()))){
										((Pman)f).setCorner(true); // if pacman is not in the save5 block reset corner check
									}
								}
							}
						}
					}
					else if (((Pman)f).getChangeDirection().equals("left")){// same as right except for left direction
						if((((Pman)f).getDirection().equals("right"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDY(0);
							((MovingEntity)f).setDX(-.1);
							pacmanAniMain=pacmanAniLeft;
						}else if (((Pman)f).getDirection().equals("")){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDY(0);
							((MovingEntity)f).setDX(-.1);
							pacmanAniMain=pacmanAniLeft;
							timer.start();
						}
						else if ((((Pman)f).getDirection().equals("left"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX()-3 < f.getX() && g.getX()+3 > f.getX())&&(g.getY()-3 < f.getY() && g.getY()+3 > f.getY())){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("left")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											f.setX(g.getX());
											f.setY(g.getY());
											((MovingEntity)f).setDY(0);
											((MovingEntity)f).setDX(-.1);
											save5 = g;
											pacmanAniMain=pacmanAniLeft;
											((Pman)f).setCorner(false);
										}
									}else if (!((save5.getX()-3 < f.getX() && save5.getX()+3 > f.getX())&&(save5.getY()-3 < f.getY() && save5.getY()+3 > f.getY()))){
										((Pman)f).setCorner(true);
									}
								}
							}
						}
					}
					if ((((Pman)f).getChangeDirection().equals("up"))){ // same as right and left except for up
						if((((Pman)f).getDirection().equals("down"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDX(0);
							((MovingEntity)f).setDY(-.1);
							pacmanAniMain=pacmanAniUp;
						}else if ((((Pman)f).getDirection().equals("up"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX()-3 < f.getX() && g.getX()+3 > f.getX())&&(g.getY()-3 < f.getY() && g.getY()+3 > f.getY())){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("up")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											f.setX(g.getX());
											f.setY(g.getY());
											((MovingEntity)f).setDX(0);
											((MovingEntity)f).setDY(-.1);
											save5 = g;
											pacmanAniMain=pacmanAniUp;
											((Pman)f).setCorner(false);
										}
									}else if (!((save5.getX()-3 < f.getX() && save5.getX()+3 > f.getX())&&(save5.getY()-3 < f.getY() && save5.getY()+3 > f.getY()))){
										((Pman)f).setCorner(true);
									}
								}
							}
						}
					}
					else if ((((Pman)f).getChangeDirection().equals("down"))){// same as up except down
						if((((Pman)f).getDirection().equals("up"))){
							((Pman)f).setDirection(((Pman)f).getChangeDirection());
							((MovingEntity)f).setDX(0);
							((MovingEntity)f).setDY(.1);
							pacmanAniMain=pacmanAniDown;
						}else if ((((Pman)f).getDirection().equals("down"))){
							// intentionally blank
						}else{
							for(Entity g: worldfinaluse){
								if(g instanceof FourWayBlock){
									if((g.getX()-3 < f.getX() && g.getX()+3 > f.getX())&&(g.getY()-3 < f.getY() && g.getY()+3 > f.getY())&&((Pman)f).getCorner()){
										if (Arrays.asList(((FourWayBlock)g).getDirections()).contains("down")){
											((Pman)f).setDirection(((Pman)f).getChangeDirection());
											f.setX(g.getX());
											f.setY(g.getY());
											((MovingEntity)f).setDX(0);
											((MovingEntity)f).setDY(.1);
											save5 = g;
											pacmanAniMain=pacmanAniDown;
											((Pman)f).setCorner(false);
										}
									}else if (!((save5.getX()-3 < f.getX() && save5.getX()+3 > f.getX())&&(save5.getY()-3 < f.getY() && save5.getY()+3 > f.getY()))){
										((Pman)f).setCorner(true);
									}
								}
							}
						}
					}
					f.setTexture(pacmanAniMain[x]);// sets Pacman texture in the loop
					if(f.getX()<-40){// warp check used for tube
						
						f.setX(560);
					}
					if(f.getX()>560){
						f.setX(-40);
					}
					for (Entity g: worldfinaluse){// used for collision detection with walls
						if(g instanceof Wall){
							if(f.intersects(g)){
								if(((MovingEntity)f).getDY() == 0){// if pacman is moving horizontally
									if(((MovingEntity)f).getDX() > 0){// if pacman is moving right
										f.setX(((int)g.getX()-f.getWidth()));
										((MovingEntity)f).setDX(0);
									}
									if(((MovingEntity)f).getDX() < 0){// if pacman is moving left
										f.setX(((int)g.getX()+g.getWidth()));
										((MovingEntity)f).setDX(0);
									}
								}
								if(((MovingEntity)f).getDX() == 0){// if pacman is moving vertiacally
									if(((MovingEntity)f).getDY() > 0){// if pacman is moving down
										f.setY(((int)g.getY()-f.getHeight()));
										((MovingEntity)f).setDY(0);
									}
									if(((MovingEntity)f).getDY() < 0){// if pacman is moving up
										f.setY(((int)g.getY()+g.getHeight()));
										((MovingEntity)f).setDY(0);
									}
								}
							}
						}
					}
				}
				if (f instanceof Catch){
					for (Entity g: worldfinaluse){
						if(g instanceof Dot){// checks dot intersection
							if(f.intersects(g)){
								world.remove(g);// removes the Dot from the world
								score += 10;
							}
						}
					}
				}
				for (Entity g: worldfinaluse){
					if (g instanceof Words){
						((Words)g).setWord("p1 score "+ score);// resets the score seen in the top left
					}
				}
				f.update();// updates all objects
				if(!(f instanceof Catch)){
					f.draw(); //draws all objects except catch
				}
			}
			Display.update();// updates display
			Display.sync(100);// limits display to 100 fps
		}
		Display.destroy();// if x is press will close the window
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
			super(260,311,40,8);// always the same 
		}

		public void draw() {
			glLoadIdentity();
			glColor3f(1f,.5f,.5f);// draws a pale pink
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
			super(x,y,40,40,"invis1");// invis1 is an invisible texture
			this.directions = directions; // saves directions
		}

		public void draw() {
			glLoadIdentity();
			tex.bind();// binds texture
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
			super(x, y, 10, 10);// width and height always the same position can be changed
		}

		public void draw() {
			glLoadIdentity();
			glColor3f(1f,.8f,.5f);// kind orangey color
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
			tex.bind();// bindes basic white for clearing
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
		protected String direction;// direction
		protected String changeDirection = "";// direction that pacman will change to 
		protected boolean corner;//  used for corner cecking so pacman doesn't get stuck
		public Pman(double x, double y, String tex, String direction) {
			super(x, y, 40, 40, tex);
			this.direction = direction;
		}

		@Override
		public void draw() {
			glLoadIdentity();
			tex.bind();// texture bound
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
		public void setCorner(boolean corner){
			this.corner = corner;
		}
		public String getDirection(){
			return direction;
		}
		public String getChangeDirection(){
			return changeDirection;
		}
		public boolean getCorner(){
			return corner;
		}
	}
	public static class Catch extends AbstractBoundEntity{// binds to pacman to check dot intersection
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
		protected String direction;
		protected String changeDirection = "left";
		protected String name = "";
		protected String status = "scatter";// status of ghost
		protected boolean corner;
		public Ghost(double x, double y, String tex, String direction, String name, String status) {
			super(x, y, 40, 40, tex);
			this.direction = direction;
			this.name = name;
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
		public void setDirection(String direction){
			this.direction = direction;
		}
		public void setChangeDirection(String changeDirection){
			this.changeDirection = changeDirection;
		}
		public void setStatus(String status){
			this.status = status;
		}
		public void setCorner(boolean corner){
			this.corner = corner;
		}
		public String getDirection(){
			return direction;
		}
		public String getChangeDirection(){
			return changeDirection;
		}
		public String getName(){
			return name;
		}
		public String getStatus(){
			return status;
		}
		public boolean getCorner(){
			return corner;
		}
	}
	public static class Words extends AbstractWordEntity{
		public Words(double x, double y, String word){
			super(x,y,word);
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
	public class Timers implements ActionListener{// timer for ghost status
		protected String status = "scatter";
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(status.equals("scatter")){
				status = "chase";
			}
			else if(status.equals("chase")){
				status = "scatter";
			}
		}
		public String getStatus(){
			return status;
		}
		
	}
	public static void main(String[] args){
		new Pacman();

	}
}
