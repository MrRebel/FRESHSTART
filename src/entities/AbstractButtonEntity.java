/**
 * @author William Christensen
 * @date 9/22/15
 * 
 * An AbstractBUttonEntity implementing ButtonEntity and extending Abstract Entity used 
 * solely for checking intersection with mouse or cursor for easy button click check.
 */
package entities;

public abstract class AbstractButtonEntity extends AbstractEntity implements ButtonEntity {
	//simple untextured implementation (less common)
	public AbstractButtonEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	//robust textured implementation (more common)
	public AbstractButtonEntity(double x, double y, double width, double height, String key) {
		super(x, y, width, height, key);
	}
	
	public boolean inbounds (int mousex, int mousey){ //method for taking in two points and checking the values with in the entity
		return (mousex > x && mousex < x + width && mousey > y && mousey < y + height);
	}
}
