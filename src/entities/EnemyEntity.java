package entities;

public interface EnemyEntity extends MovingEntity{
	public void setId(int id);
	public void setHealth(double health);
	public void takeDamage(double damage);
	public void setAttack(double attack);
	public void setDefense(double defense);
	public void setSpeed(double speed);
	public void setIQ(double IQ);
	public void powerUp(int PowerId);
	public void moveset(int num);
	public double setId();
	public double getHealth();
	public double getAttack();
	public double getDefense();
	public double getSpeed();
	public double getIQ();
	public int selectAttack(int difficulty, int id, int[] allyIdList);
	
}
