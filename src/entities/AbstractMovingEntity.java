/**
 * @author William Christensen
 * @date 4/20/15
 * 
 * A class implementing the MovingEntity Interface that extends Abstract entity mainly allowing 
 * ability to set a delta y and x using with an option using an internal or external clock
 * to determine the actual space in between each movement.
 */
package entities;

import org.lwjgl.Sys;

public abstract class AbstractMovingEntity extends AbstractEntity implements MovingEntity {
	
	protected double dx, dy; //dx and dy value instantiation 
	//simplest form of MovingEntity
	public AbstractMovingEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
		this.dx=0;
		this.dy=0;
	} 
	public AbstractMovingEntity(double x, double y, double width, double height, double dx, double dy) {
		super(x, y, width, height);
		this.dx=dx;
		this.dy=dy;
	}
	public AbstractMovingEntity(double x, double y, double width, double height, String key) {
		super(x, y, width, height, key);
		this.dx=0;
		this.dy=0;
	}
	//Textured implementation with a settable sy by dx from start (most robust option)
	public AbstractMovingEntity(double x, double y, double width, double height, double dx, double dy, String key) {
		super(x, y, width, height, key);
		this.dx=dx;
		this.dy=dy;
	}
	
	public void update(int delta){ // external clock
		this.x += delta * dx;
		this.y += delta * dy;
	}
	public void update(){ // internal clock
		int delta = getDelta();
		this.x += delta * dx;
		this.y += delta * dy;
	}
	
	public double getDX(){ // returns dx
		return dx;
	}
	public double getDY(){ //returns dy
		return dy;
	}
	public void setDX (double dx){ // sets dx
		this.dx = dx;
	}
	public void setDY(double dy){ // sets dy
		this.dy = dy;
	}
	private long lastFrame;// lastframe variable instantiated
	
	private long getTime(){ // gets time for internal clock calculation
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private int getDelta(){ // calculation for internal clock
		long currentTime=getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = currentTime;
		return delta;
	}
}