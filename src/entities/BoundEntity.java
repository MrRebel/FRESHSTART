/**
 * @author William Christensen 
 * @date 4/28/15
 * 
 * An extension of the MovingEntity interface that allows one entity to be connected to
 * another at a certain distance.
 */
package entities;

public interface BoundEntity extends MovingEntity{
	public void setDistanceX(double distancex);
	public void setDistanceY(double distancey);
	public void setBound (Entity other);
	public void setIsBound();
	public boolean getIsBound();
	public double getDistanceX();
	public double getDistanceY();
	public Entity getBound ();
	
}
