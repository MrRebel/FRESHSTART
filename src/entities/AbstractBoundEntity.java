package entities;

public abstract class AbstractBoundEntity extends AbstractMovingEntity implements BoundEntity{

	protected Entity other;
	protected double distancex, distancey;
	protected boolean bound = true;
	
	public AbstractBoundEntity(double x, double y, double width, double height, double distancex, double distancey) {
		super(x, y, width, height);
		this.distancex = distancex;
		this.distancey = distancey;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, String key, double distancex, double distancey) {
		super(x, y, width, height, key);
		this.distancex = distancex;
		this.distancey = distancey;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, double distancex, double distancey, boolean bound) {
		super(x, y, width, height);
		this.distancex = distancex;
		this.distancey = distancey;
		this.bound = bound;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, String key, double distancex, double distancey, boolean bound) {
		super(x, y, width, height, key);
		this.distancex = distancex;
		this.distancey = distancey;
		this.bound = bound;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, double dx, double dy, double distancex, double distancey) {
		super(x, y, width, height, dx, dy);
		this.distancex = distancex;
		this.distancey = distancey;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, double dx, double dy, String key, double distancex, double distancey) {
		super(x, y, width, height, dx, dy, key);
		this.distancex = distancex;
		this.distancey = distancey;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, double dx, double dy, double distancex, double distancey, boolean bound) {
		super(x, y, width, height, dx, dy);
		this.distancex = distancex;
		this.distancey = distancey;
		this.bound = bound;
	}
	public AbstractBoundEntity(double x, double y, double width, double height, double dx, double dy, String key, double distancex, double distancey, boolean bound) {
		super(x, y, width, height, dx, dy, key);
		this.distancex = distancex;
		this.distancey = distancey;
		this.bound = bound;
	}
	public void setDistanceX(double distancex){
		this.distancex = distancex;
	}
	public void setDistanceY(double distancey){
		this.distancey = distancey;
	}
	
	public void setBound (Entity other){
		this.other = other;
		if (bound){
			setY(other.getY()+getDistanceY());
			setX(other.getX()+getDistanceX());
		}
	}
	public void setIsBound(boolean bound){
		this.bound = bound;
	}
	public boolean getIsBound(){
		return bound;
	}
	public double getDistanceX(){
		return distancex;
	}
	
	public double getDistanceY(){
		return distancey;
	}

	public Entity getBound (){
		return other;
	}

}
