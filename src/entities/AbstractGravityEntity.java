package entities;
public abstract class AbstractGravityEntity extends AbstractMovingEntity implements GravityEntity {
	protected double c = .05;
	protected double maxdy = 5;
	boolean first = true;
	public AbstractGravityEntity(double x, double y, double width, double height) {
		super(x, y, width, height);
		//setDY(Gravity(getDY()));
	}
	public AbstractGravityEntity(double x, double y, double width, double height, String key) {
		super(x, y, width, height, key);
		//setDY(Gravity(getDY()));
	}
	public AbstractGravityEntity(double x, double y, double width, double height, double dx, double dy) {
		super(x, y, width, height, dx, dy);
		//setDY(Gravity(getDY()));
	}
	public AbstractGravityEntity(double x, double y, double width, double height, double dx, double dy,  String key) {
		super(x, y, width, height, dx, dy, key);
		//setDY(Gravity(getDY()));
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
