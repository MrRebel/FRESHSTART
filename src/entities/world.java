package entities;


import java.util.List;

public interface world {
	public void addEntity(Entity add);
	public void addmove(MovingEntity add);
	public void addgrav(GravityEntity add);
	public void addscore(ScoreEntity add);
	public void addbound(BoundEntity add);
	public void addbutton(ButtonEntity add);
	public Entity returnEntity(int get);
	public MovingEntity returnmove(int get);
	public GravityEntity returngrav(int get);
	public ScoreEntity returnscore(int get);
	public BoundEntity returnbound(int get);
	public ButtonEntity returnbutton(int get);
	public void set (int etype, int num, int type, double set);
	public void removeEntity(Entity remove);
	public void removemove(MovingEntity remove);
	public void removegrav(GravityEntity remove);
	public void removescore(ScoreEntity remove);
	public void removebound(BoundEntity remove);
	public void removebutton(ButtonEntity remove);
	public int getSize();
	public List<Entity> giveArrayList();
	public List<MovingEntity> giveArrayListM();
	public List<GravityEntity> giveArrayListG();
	public List<ScoreEntity> giveArrayListS();
	public List<BoundEntity> giveArrayListBo();
	public List<ButtonEntity> giveArrayListBu();
	public void reset();

}
