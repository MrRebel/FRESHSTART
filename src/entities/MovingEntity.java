/**
 * @author William Christensen
 * @date 4/28/14
 * 
 * An interface extending Entity making an entity useful for movement.
 * This code is derived from code by Oskar Veerhoek
 * the source code code can be found at http://thecodinguniverse.com/lwjgl-entities/ and the youtube video https://www.youtube.com/watch?v=-LwBWysU-FY
 */
package entities;

public interface MovingEntity extends Entity {
	public void update();
	public void setDX(double dx);
	public void setDY(double dy);
	public double getDX();
	public double getDY();
	public int getDelta();
}
