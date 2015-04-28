package entities;


import java.util.List;

public interface world {
	public void add(Entity add);
	public Entity getEntity(int get);
	public void remove(Entity remove);
	public int getSize();
	public List<Entity> giveArrayList();
	public List<MovingEntity> giveArrayListM();
	public List<GravityEntity> giveArrayListG();
	public List<WordEntity> giveArrayListW();
	public List<BoundEntity> giveArrayListBo();
	public List<ButtonEntity> giveArrayListBu();
	public void reset();

}
