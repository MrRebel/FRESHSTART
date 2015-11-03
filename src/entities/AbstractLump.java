package entities;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AbstractLump implements Lump {
	List<Entity> lump = new CopyOnWriteArrayList<Entity>();
	public AbstractLump(){

	}

	public void add(Entity e) {
		// TODO Auto-generated method stub
		lump.add(e);
	}
	
	public <T extends Entity> T get(Object o, Class<T> type){
		// TODO Auto-generated method stub
		if (lump.contains(o)){
			return type.cast(o);
		}else{
			throw new IllegalArgumentException("this object is not there!");
		}
	}
	
	public <T extends Entity> T get(int i, Class<T> type){
		// TODO Auto-generated method stub
		if (lump.size()>i){
			return type.cast(lump.get(i));
		}else{
			throw new IllegalArgumentException("this object is not there!");
		}
	}

	public void remove(Entity e) {
		// TODO Auto-generated method stub
		lump.remove(e);
	}


	public List<Entity> getArrayList() {
		return lump;
	}


	public void reset() {
		// TODO Auto-generated method stub

	}

}
