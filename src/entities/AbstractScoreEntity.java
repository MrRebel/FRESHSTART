package entities;

public abstract class AbstractScoreEntity extends AbstractEntity implements ScoreEntity{

	protected int score;
	public AbstractScoreEntity(double x, double y, double width, double height, int score) {
		super(x, y, width, height);
		this.score = score;
	}
	public AbstractScoreEntity(double x, double y, double width, double height, String key,int score) {
		super(x, y, width, height, key);
		this.score = score;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
}
