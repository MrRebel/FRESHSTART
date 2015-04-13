package entities;

public interface GravityEntity extends MovingEntity{
	public void setGravC(double c);
	public void setMaxDY(double maxdy);
	public double getGravC();
	public boolean getFirst();
	public double Gravity (double firstdy);
	public double getMaxDY();
}
