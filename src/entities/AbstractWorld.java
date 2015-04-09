package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorld implements world {
	
	protected List<Entity> world, orig;
	protected List<MovingEntity> moveworld, moveorig;
	protected List<GravityEntity> gravworld, gravorig;
	protected List<BoundEntity> boundworld, boundorig;
	protected List<ScoreEntity> scoreworld, scoreorig;
	protected List<ButtonEntity> buttonworld, buttonorig;
	protected int length;
	
	public AbstractWorld (List<Entity> world, List<MovingEntity> moveworld, List<GravityEntity> gravworld, List<BoundEntity> boundworld, List<ScoreEntity> scoreworld, List<ButtonEntity> buttonworld, int length){
		this.world = new ArrayList <Entity> (length);
		//this.world.addAll(world);
		this.orig = new ArrayList <Entity> (length);
		//this.orig.addAll(world);
		this.moveworld = new ArrayList <MovingEntity> (length);
		this.moveworld.addAll(moveworld);
		this.moveorig = new ArrayList <MovingEntity> (length);
		this.moveorig.addAll(moveworld);
		this.boundworld = new ArrayList <BoundEntity> (length);
		//this.boundworld.addAll(boundworld);
		this.boundorig = new ArrayList <BoundEntity> (length);
		//this.boundorig.addAll(boundworld);
		this.gravworld = new ArrayList <GravityEntity> (length);
		this.gravworld.addAll(gravworld);
		this.gravorig = new ArrayList<GravityEntity> (length);
		this.gravorig.addAll(gravworld);
		this.scoreworld = new ArrayList <ScoreEntity> (length);
		//this.scoreworld.addAll(scoreworld);
		this.scoreorig = new ArrayList <ScoreEntity> (length);
		//this.scoreorig.addAll(scoreworld);
		this.buttonworld = new ArrayList<ButtonEntity> (length);
		//this.buttonworld.addAll(buttonworld);
		this.buttonorig = new ArrayList<ButtonEntity> (length);
		//this.buttonorig.addAll(buttonworld);
		
	}

	@Override
	public void addEntity(Entity add) {
		world.add(add);

	}
	
	public void addmove(MovingEntity add) {
		world.add(add);
		moveworld.add(add);
	}
	public void addgrav(GravityEntity add) {
		world.add(add);
		moveworld.add(add);
		gravworld.add(add);
	}
	public void addscore(ScoreEntity add) {
		world.add(add);
		scoreworld.add(add);
	}
	public void addbound(BoundEntity add) {
		world.add(add);
		moveworld.add(add);
	}
	public void addbutton(ButtonEntity add) {
		world.add(add);
		buttonworld.add(add);
	}
	public Entity returnEntity(int get){
		return world.get(get);
	}
	public MovingEntity returnmove(int get){
		return moveworld.get(get);
	}
	public GravityEntity returngrav(int get){
		return gravworld.get(get);
	}
	public ScoreEntity returnscore(int get){
		return scoreworld.get(get);
	}
	public BoundEntity returnbound(int get){
		return boundworld.get(get);
	}
	public ButtonEntity returnbutton(int get){
		return buttonworld.get(get);
	}
	public void set (int etype, int num, int type, double set){
		if (etype == 1){
			if (type==1){
				this.world.get(num).setX(set);
			}
			if (type==2){
				this.world.get(num).setY(set);
			}
			if (type==3){
				this.world.get(num).setWidth(set);
			}
			if (type==4){
				this.world.get(num).setHeight(set);
			}
		}
		if (etype == 2){
			if (type==1){
				this.moveworld.get(num).setDX(set);
			}
			if (type==2){
				this.moveworld.get(num).setDY(set);
			}
		}
		if (etype == 3){
			if (type == 1){
				this.boundworld.get(num).setDistanceX(set);
			}
			if (type == 2){
				this.boundworld.get(num).setDistanceY(set);
			}
		}
		if (etype == 4){
			if (type == 1){
				this.scoreworld.get(num).setScore((int)set);
			}
		}
	}
	
	public void set (int etype, int num, int type, double[] set){
		if (etype == 1){
			if (type == 5){
				this.world.get(num).setLocation(set[0],set[1]);
			}
		}
	}
	public void set (int etype, int num, int type, String set){
		if (etype == 1){
			if (type ==6){
				this.world.get(num).setTexture(set);
			}
		}
	}

	@Override
	public void removeEntity(Entity remove) {
		world.remove(remove);

	}
	@Override
	public void removemove(MovingEntity remove){
		world.remove(remove);
		moveworld.remove(remove);
	}
	@Override
	public void removegrav(GravityEntity remove){
		world.remove(remove);
		moveworld.remove(remove);
		gravworld.remove(remove);
	}
	@Override
	public void removescore(ScoreEntity remove){
		world.remove(remove);
		scoreworld.remove(remove);
	}
	@Override
	public void removebound(BoundEntity remove){
		world.remove(remove);
		moveworld.remove(remove);
		boundworld.remove(remove);
	}
	@Override
	public void removebutton(ButtonEntity remove){
		world.remove(remove);
		buttonworld.remove(remove);
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<Entity> giveArrayList() {
		// TODO Auto-generated method stub
		return world;
	}
	public List<GravityEntity> giveArrayListG(){
		return gravworld;
	}
	public List<MovingEntity> giveArrayListM(){
		return moveworld;
	}
	public List<ScoreEntity> giveArrayListS(){
		return scoreworld;
	}
	public List<BoundEntity> giveArrayListBo(){
		return boundworld;
	}
	public List<ButtonEntity> giveArrayListBu(){
		return buttonworld;
	}
	
	@Override
	public void reset (){
		this.world = orig;
		moveworld = moveorig;
		gravworld = gravorig;
		boundworld = boundorig;
		buttonworld = buttonorig;
		scoreworld = scoreorig;
	}

}
