/**
 * @author William Christensen
 * @date 4/20/15
 * 
 * An Extension of Moving Entity that implements the 
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
		this.maxdy = maxdy;
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
	public void setGravC(double c){
		this.c = c;
	}
	public void setMaxDY(double maxdy){
		this.maxdy = maxdy;
	}
	public double getGravC(){
		return c;
	}
	public boolean getFirst(){
		return first;
	}
	public double getMaxDY(){
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
