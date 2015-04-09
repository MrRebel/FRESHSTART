package entities;

import org.lwjgl.Sys;

public abstract class AbstractMovingEntity extends AbstractEntity implements MovingEntity {
	
	protected double dx, dy;

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
	public AbstractMovingEntity(double x, double y, double width, double height, double dx, double dy, String key) {
		super(x, y, width, height, key);
		this.dx=dx;
		this.dy=dy;
	}
	
	public void update(int delta){
		this.x += delta * dx;
		this.y += delta * dy;
	}
	public void update(){
		int delta = getDelta();
		this.x += delta * dx;
		this.y += delta * dy;
	}
	
	public double getDX(){
		return dx;
	}
	public double getDY(){
		return dy;
	}
	public void setDX (double dx){
		this.dx = dx;
	}
	public void setDY(double dy){
		this.dy = dy;
	}
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
}