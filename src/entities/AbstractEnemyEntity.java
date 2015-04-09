package entities;

public abstract class AbstractEnemyEntity extends AbstractMovingEntity implements EnemyEntity{

	public AbstractEnemyEntity(double x, double y, double width, double height, int id) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHealth(double health) {
		// TODO Auto-generated method stub

	}

	@Override
	public void takeDamage(double damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAttack(double attack) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDefense(double defense) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIQ(double IQ) {
		// TODO Auto-generated method stub

	}

	@Override
	public void powerUp(int PowerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveset(int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public double setId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getDefense() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getIQ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectAttack(int difficulty, int id, int[] allyIdList) {
		// TODO Auto-generated method stub
		return 0;
	}

}
