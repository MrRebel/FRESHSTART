package entities;


import java.util.List;

public interface world {
	public void add(Entity add);
	public Entity returnEntity(int get);
	public MovingEntity returnmove(int get);
	public GravityEntity returngrav(int get);
	public ScoreEntity returnscore(int get);
	public BoundEntity returnbound(int get);
	public ButtonEntity returnbutton(int get);
	public void remove(Entity remove);
	public int getSize();
	public List<Entity> giveArrayList();
	public List<MovingEntity> giveArrayListM();
	public List<GravityEntity> giveArrayListG();
	public List<ScoreEntity> giveArrayListS();
	public List<BoundEntity> giveArrayListBo();
	public List<ButtonEntity> giveArrayListBu();
	public void reset();

}
