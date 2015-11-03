package entities;

import java.util.List;

public interface Lump {
	public void add(Entity e);
	public void remove(Entity e);
	public <T extends Entity> T get(Object o, Class<T> type);
	public <T extends Entity> T get(int i , Class<T> type);
	public List<Entity> getArrayList();
	public void reset();
}
