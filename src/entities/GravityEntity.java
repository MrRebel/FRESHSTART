package entities;

public interface GravityEntity extends MovingEntity{
	public void setGravC(double c);
	public double getGravC();
	public boolean getFirst();
	public double Gravity (double firstdy);
}
