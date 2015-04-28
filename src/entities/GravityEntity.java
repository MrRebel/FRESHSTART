/**
 * @author William Christensen
 * @date 4/28/15
 * 
 * An extension of the MovingEntity Interface making an object able to be 
 * affected by gravity
 */
package entities;

public interface GravityEntity extends MovingEntity{
	public void setGravC(double c);
	public void setMaxDY(double maxdy);
	public double getGravC();
	public boolean getFirst();
	public double Gravity (double firstdy);
	public double getMaxDY();
}
