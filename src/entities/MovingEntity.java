package entities;

public interface MovingEntity extends Entity {
	public void update();
	public void setDX(double dx);
	public void setDY(double dy);
	public double getDX();
	public double getDY();
}
