package entities;

public interface BoundEntity extends GravityEntity{
	public void setDistanceX(double distancex);
	public void setDistanceY(double distancey);
	public void setBound (Entity other);
	public void setIsBound();
	public boolean getIsBound();
	public double getDistanceX();
	public double getDistanceY();
	public Entity getBound ();
	
}
