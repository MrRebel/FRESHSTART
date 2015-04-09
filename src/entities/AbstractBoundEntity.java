package entities;

public abstract class AbstractBoundEntity extends AbstractMovingEntity implements BoundEntity{

	protected MovingEntity other;
	protected double distancex, distancey;
	
	public AbstractBoundEntity(double x, double y, double width, double height, double distancex, double distancey) {
		super(x, y, width, height);
		this.distancex = distancex;
		this.distancey = distancey;
	}
	
	public void setDistanceX(double distancex){
		this.distancex = distancex;
	}
	public void setDistanceY(double distancey){
		this.distancey = distancey;
	}
	public double getDistanceX(){
		return distancex;
	}
	
	public double getDistanceY(){
		return distancey;
	}
	
	public void setBound (MovingEntity other){
		this.other = other;
		setDY(other.getDY());
		setDX(other.getDX());
		setY(other.getY()+getDistanceY());
		setX(other.getX()+getDistanceX());
	}
	public Entity getBound (){
		return other;
	}

}
