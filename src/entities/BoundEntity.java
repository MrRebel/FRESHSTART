package entities;

public interface BoundEntity extends GravityEntity{
	public void setDistanceX(double distancex);
	public void setDistanceY(double distancey);
	public double getDistanceX();
	public double getDistanceY();
	public void setBound (GravityEntity other);
	public Entity getBound ();
	
}
