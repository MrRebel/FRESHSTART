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
	
	public AbstractWorld (List<Entity> world, int length){
		this.world = new ArrayList <Entity> (length);
		this.world.addAll(world);
		this.orig = new ArrayList <Entity> (length);
		this.orig.addAll(world);
		this.moveworld = new ArrayList <MovingEntity> (length);
		this.moveorig = new ArrayList <MovingEntity> (length);
		this.gravworld = new ArrayList <GravityEntity> (length);
		this.gravorig = new ArrayList <GravityEntity> (length);
		this.scoreworld = new ArrayList <ScoreEntity> (length);
		this.scoreorig = new ArrayList <ScoreEntity> (length);
		for (Entity f : world){
			if (f instanceof MovingEntity){
				moveworld.add((MovingEntity) f);
				moveorig.add((MovingEntity) f);
			}
			if (f instanceof GravityEntity){
				gravworld.add((GravityEntity) f);
				gravorig.add((GravityEntity) f);
			}
			if (f instanceof BoundEntity){
				boundworld.add((BoundEntity) f);
				boundorig.add((BoundEntity) f);
			}
			if (f instanceof ButtonEntity){
				buttonworld.add((ButtonEntity) f);
				buttonorig.add((ButtonEntity) f);
			}
			if (f instanceof ScoreEntity){
				scoreworld.add((ScoreEntity) f);
				scoreorig.add((ScoreEntity) f);
			}
		}

		
	}
	public void add(Entity add){
		world.add(add);
		if (add instanceof MovingEntity){
			moveworld.add((MovingEntity) add);
		}
		if (add instanceof GravityEntity){
			gravworld.add((GravityEntity) add);
		}
		if (add instanceof BoundEntity){
			boundworld.add((BoundEntity) add);
		}
		if (add instanceof ButtonEntity){
			buttonworld.add((ButtonEntity) add);
		}
		if (add instanceof ScoreEntity){
			scoreworld.add((ScoreEntity) add);
		}
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
	public void remove(Entity remove) {
		world.remove(remove);
		if (remove instanceof MovingEntity){
			moveworld.remove((MovingEntity) remove);
		}
		if (remove instanceof GravityEntity){
			gravworld.remove((GravityEntity) remove);
		}
		if (remove instanceof BoundEntity){
			boundworld.remove((BoundEntity) remove);
		}
		if (remove instanceof ButtonEntity){
			buttonworld.remove((ButtonEntity) remove);
		}
		if (remove instanceof ScoreEntity){
			scoreworld.remove((ScoreEntity) remove);
		}
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
