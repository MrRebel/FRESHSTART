/**
 * @author William Christensen
 * @date 4/20/15
 * 
 * An Extension of MovingEntity that implements the use of 
 * a constant acceleration in the Y to simulate Gravity.
 */
package entities;
public abstract class AbstractGravityEntity extends AbstractMovingEntity implements GravityEntity {
	protected double c = .05;
	protected double maxdy = 5;
	boolean first = true;
	//simplest version of Gravity Entity
	public AbstractGravityEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	public AbstractGravityEntity(double x, double y, double width, double height, String key) {
		super(x, y, width, height, key);
	}
	public AbstractGravityEntity(double x, double y, double width, double height, double dx, double dy) {
		super(x, y, width, height, dx, dy);
	}
	public AbstractGravityEntity(double x, double y, double width, double height, double dx, double dy,  String key) {
		super(x, y, width, height, dx, dy, key);
	}
	public AbstractGravityEntity(double x, double y, double width, double height, double maxdy) {
		super(x, y, width, height);
		this.maxdy = maxdy;//sets maxdy in object to given maxdy
	}
	public AbstractGravityEntity(double x, double y, double width, double height, String key, double maxdy) {
		super(x, y, width, height, key);
		this.maxdy = maxdy;
	}
	public AbstractGravityEntity(double x, double y, double width, double height, double dx, double dy, double maxdy) {
		super(x, y, width, height, dx, dy);
		this.maxdy = maxdy;
	}
	// most robust version of GravityEntity
	public AbstractGravityEntity(double x, double y, double width, double height, double dx, double dy,  String key, double maxdy) {
		super(x, y, width, height, dx, dy, key);
		this.maxdy = maxdy;
	}
	public void setGravC(double c){//set the acceleration constant
		this.c = c;
	}
	public void setMaxDY(double maxdy){//set the max dy value
		this.maxdy = maxdy;
	}
	public double getGravC(){//returns c value
		return c;
	}
	public boolean getFirst(){// returns if it is the first time it is being used
		return first;
	}
	public double getMaxDY(){// returns maxdy value
		return maxdy;
	}
	
	public double Gravity (double firstdy){
		while (first){
			first = false;
			return firstdy;
		}
		firstdy+=c;
		if (firstdy>maxdy){ 
			firstdy=maxdy;
			return firstdy;
		}
		return firstdy;
	}
}
